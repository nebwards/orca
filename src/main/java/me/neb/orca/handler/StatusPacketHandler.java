package me.neb.orca.handler;

import me.neb.orca.protocol.packet.client.status.PingRequestPacket;
import me.neb.orca.protocol.packet.client.status.StatusRequestPacket;
import me.neb.orca.protocol.packet.server.status.PongResponsePacket;
import me.neb.orca.protocol.packet.server.status.StatusResponsePacket;

public class StatusPacketHandler {
    public static StatusPacketHandler INSTANCE = new StatusPacketHandler();
    public static PacketHandler HANDLER = new PacketHandler.Builder().handle(StatusRequestPacket.class, INSTANCE.handleStatusRequest).handle(PingRequestPacket.class, INSTANCE.handlePingRequest).build();

    private final PacketHandlerFn<StatusRequestPacket> handleStatusRequest = (packet, connection) -> {
        System.out.println("[handle] status request");

        StatusResponsePacket response = new StatusResponsePacket("{\n" +
                "    \"version\": {\n" +
                "        \"name\": \"1.21.8\",\n" +
                "        \"protocol\": 772\n" +
                "    },\n" +
                "    \"description\": {\"text\":\"\",\"extra\":[{\"text\":\"o\",\"color\":\"#5FE2C5\"},{\"text\":\"r\",\"color\":\"#5DDCC7\"},{\"text\":\"c\",\"color\":\"#5BD6C9\"},{\"text\":\"a\",\"color\":\"#58D0CB\"},{\"text\":\" \"},{\"text\":\"(\",\"color\":\"#54C3CE\"},{\"text\":\"1\",\"color\":\"#52BDD0\"},{\"text\":\".\",\"color\":\"#4FB7D2\"},{\"text\":\"2\",\"color\":\"#4DB1D4\"},{\"text\":\"1\",\"color\":\"#4BABD6\"},{\"text\":\".\",\"color\":\"#49A4D7\"},{\"text\":\"8\",\"color\":\"#469ED9\"},{\"text\":\")\",\"color\":\"#4498DB\"}]},\n" +
                "    \"enforcesSecureChat\": false\n" +
                "}");

        connection.sendPacket(response);
    };

    private final PacketHandlerFn<PingRequestPacket> handlePingRequest = (packet, connection) -> {
        System.out.println("[handle] ping");
        connection.sendPacket(new PongResponsePacket(packet.payload()));
    };
}
