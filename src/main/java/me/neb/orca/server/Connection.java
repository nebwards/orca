package me.neb.orca.server;

import me.neb.orca.handler.HandshakePacketHandler;
import me.neb.orca.handler.PacketHandler;
import me.neb.orca.protocol.Protocol;
import me.neb.orca.protocol.ServerProtocol;
import me.neb.orca.protocol.packet.server.ServerPacket;

import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Connection {
    private final SocketChannel channel;

    private final Queue<ServerPacket> packetQueue = new ConcurrentLinkedQueue<>();

    private ConnectionState state = ConnectionState.HANDSHAKE;
    private Protocol protocol = Protocol.HANDSHAKE;
    private ServerProtocol serverProtocol = ServerProtocol.HANDSHAKE;
    private PacketHandler handler = HandshakePacketHandler.HANDLER;

    public Connection(SocketChannel channel) {
        this.channel = channel;
    }

    public void sendPacket(ServerPacket packet) {
        this.packetQueue.offer(packet);
    }

    public SocketChannel channel() {
        return channel;
    }

    public ConnectionState state() {
        return state;
    }

    public void state(ConnectionState state) {
        this.state = state;
    }

    public Protocol protocol() {
        return protocol;
    }

    public void protocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ServerProtocol serverProtocol() {
        return serverProtocol;
    }

    public void serverProtocol(ServerProtocol serverProtocol) {
        this.serverProtocol = serverProtocol;
    }

    public PacketHandler handler() {
        return handler;
    }

    public void handler(PacketHandler handler) {
        this.handler = handler;
    }

    public Queue<ServerPacket> packetQueue() {
        return packetQueue;
    }
}
