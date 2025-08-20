package me.neb.orca.handler;

import me.neb.orca.component.Component;
import me.neb.orca.component.text.TextColor;
import me.neb.orca.component.text.TextComponent;
import me.neb.orca.protocol.packet.client.status.PingRequestPacket;
import me.neb.orca.protocol.packet.client.status.StatusRequestPacket;
import me.neb.orca.protocol.packet.server.status.PongResponsePacket;
import me.neb.orca.protocol.packet.server.status.StatusResponsePacket;

public class StatusPacketHandler {
    public static StatusPacketHandler INSTANCE = new StatusPacketHandler();
    public static PacketHandler HANDLER = new PacketHandler.Builder().handle(StatusRequestPacket.class, INSTANCE.handleStatusRequest).handle(PingRequestPacket.class, INSTANCE.handlePingRequest).build();

    private final PacketHandlerFn<StatusRequestPacket> handleStatusRequest = (packet, connection) -> {
        System.out.println("[handle] status request");

        TextComponent description = Component.text("orca 1.21.8").color(TextColor.CSS_LIGHTSTEELBLUE)
                .append(" ")
                .append(Component.text("beta").color(TextColor.CSS_TURQUOISE).bold())
                .build();

        StatusResponsePacket response = new StatusResponsePacket(String.format("""
                {
                    "version": {
                        "name": "1.21.8",
                        "protocol": 772
                    },
                    "description": %s,
                    "enforcesSecureChat": false
                }""", description.stringify()));

        connection.sendPacket(response);
    };

    private final PacketHandlerFn<PingRequestPacket> handlePingRequest = (packet, connection) -> {
        System.out.println("[handle] ping");
        connection.sendPacket(new PongResponsePacket(packet.payload()));
    };
}
