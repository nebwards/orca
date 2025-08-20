package me.neb.orca.handler;

import me.neb.orca.protocol.Protocol;
import me.neb.orca.protocol.ServerProtocol;
import me.neb.orca.protocol.packet.client.login.LoginAcknowledgedPacket;
import me.neb.orca.protocol.packet.client.login.HelloPacket;
import me.neb.orca.protocol.packet.server.login.LoginFinishedPacket;
import me.neb.orca.server.ConnectionState;

public class LoginPacketHandler {
    public static LoginPacketHandler INSTANCE = new LoginPacketHandler();
    public static PacketHandler HANDLER = new PacketHandler.Builder().handle(HelloPacket.class, INSTANCE.handleHello).handle(LoginAcknowledgedPacket.class, INSTANCE.handleLoginAcknowledged).build();

    private final PacketHandlerFn<HelloPacket> handleHello = (packet, connection) -> {
        System.out.println("[handle] login start");

        LoginFinishedPacket finished = new LoginFinishedPacket(packet.uuid(), packet.username());
        connection.sendPacket(finished);
    };

    private final PacketHandlerFn<LoginAcknowledgedPacket> handleLoginAcknowledged = (packet, connection) -> {
        System.out.println("[handle] login acknowledged");

        // Switch to configuration
        System.out.println("[state] state change to config");
        connection.state(ConnectionState.CONFIGURATION);
        connection.protocol(Protocol.CONFIGURATION);
        connection.serverProtocol(ServerProtocol.CONFIGURATION);
        connection.handler(ConfigPacketHandler.HANDLER);
    };
}
