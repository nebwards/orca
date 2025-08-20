package me.neb.orca.handler;

import me.neb.orca.protocol.Protocol;
import me.neb.orca.protocol.ServerProtocol;
import me.neb.orca.protocol.packet.client.handshake.IntentionPacket;
import me.neb.orca.server.ConnectionState;

public class HandshakePacketHandler {
    public static HandshakePacketHandler INSTANCE = new HandshakePacketHandler();
    public static PacketHandler HANDLER = new PacketHandler.Builder().handle(IntentionPacket.class, INSTANCE.handleIntention).build();

    private final PacketHandlerFn<IntentionPacket> handleIntention = (packet, connection) -> {
        switch (packet.intent()) {
            case 1 -> {
                System.out.println("[state] Switch to status");
                connection.state(ConnectionState.STATUS);
                connection.protocol(Protocol.STATUS);
                connection.serverProtocol(ServerProtocol.STATUS);
                connection.handler(StatusPacketHandler.HANDLER);
            }
            case 2 -> {
                System.out.println("[state] Switch to login");
                connection.state(ConnectionState.LOGIN);
                connection.protocol(Protocol.LOGIN);
                connection.serverProtocol(ServerProtocol.LOGIN);
                connection.handler(LoginPacketHandler.HANDLER);
            }
            default -> throw new IllegalStateException("Unknown packet intent: " + packet.intent());
        }
    };
}
