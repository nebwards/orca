package me.neb.orca.server;

import me.neb.orca.protocol.VarLenEncoding;
import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.protocol.packet.server.ServerPacket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadServer {
    private static final String ADDRESS = "0.0.0.0";
    private static final int PORT = 25565;
    private static final int BUFFER_SIZE = 8192;

    public void start() throws IOException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); ServerSocketChannel socket = ServerSocketChannel.open()) {

            socket.bind(new InetSocketAddress(ADDRESS, PORT));
            System.out.println("Server started on port " + PORT);

            while (true) {
                Connection connection = new Connection(socket.accept());
                executor.execute(() -> handleConnection(connection));
            }
        }
    }

    private void handleConnection(Connection connection) {
        System.out.println("Client connected");

        ByteBuffer readBuffer = ByteBuffer.allocate(BUFFER_SIZE);

        try (SocketChannel channel = connection.channel()) {
            try {
                while (channel.isOpen()) {
                    // --------------------------------- READ ---------------------------------
                    int bytesRead = channel.read(readBuffer);
                    if (bytesRead == -1) {
                        System.out.println("[read_loop] Disconnect: (EOF)");
                        break;
                    }

                    readBuffer.flip();

                    while (readBuffer.hasRemaining()) {
                        System.out.println("\n\n[read] starting packet read, state: " + connection.state());

                        int packetLength = VarLenEncoding.readVarInt(readBuffer);
                        ByteBuffer payloadBuffer = readBuffer.slice(readBuffer.position(), packetLength);
                        readBuffer.position(readBuffer.position() + packetLength);

                        System.out.println("[read] Packet length: " + packetLength);

                        ProtoBuf buf = new ProtoBuf(payloadBuffer);
                        int packetId = buf.read(ProtoBufCodec.VAR_INT);

                        System.out.println("[read] id: " + packetId);

                        Packet packet = connection.protocol().construct(packetId, buf);

                        System.out.println("[Packet] " + packet);

                        connection.handler().handle(packet, connection);
                    }

                    readBuffer.compact();

                    // --------------------------------- WRITE ---------------------------------
                    ServerPacket packet;
                    while ((packet = connection.packetQueue().poll()) != null) {
                        System.out.println("Sending packet: " + packet);
                        ProtoBuf buf = new ProtoBuf(ByteBuffer.allocateDirect(BUFFER_SIZE));
                        var packetInfo = connection.serverProtocol().packetInfo(packet.getClass());
                        buf.writePacket(packetInfo.id(), packetInfo.codec(), packet);

                        buf.unwrap().flip();

                        while (buf.unwrap().hasRemaining()) {
                            int count = channel.write(buf.unwrap());
                            if (count == -1) {
                                System.out.println("[write_loop] Disconnect: (EOF)");
                                break;
                            }
                            System.out.println("[write_loop] wrote " + count + " bytes for packet: " + packet.getClass().getSimpleName());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("[write_loop] Disconnect: " + e.getMessage());
            }
        } catch (IOException ignore) {
        }
    }
}
