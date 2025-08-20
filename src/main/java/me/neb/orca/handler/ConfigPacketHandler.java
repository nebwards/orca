package me.neb.orca.handler;

import me.neb.orca.protocol.packet.client.configuration.ClientInformationPacket;
import me.neb.orca.protocol.packet.client.configuration.CustomPayloadPacket;
import me.neb.orca.temp.Identifier;

import java.nio.charset.StandardCharsets;

public class ConfigPacketHandler {
    public static ConfigPacketHandler INSTANCE = new ConfigPacketHandler();
    public static PacketHandler HANDLER = new PacketHandler.Builder().handle(ClientInformationPacket.class, INSTANCE.handleClientInfo).handle(CustomPayloadPacket.class, INSTANCE.handleCustomPayload).build();

    private final PacketHandlerFn<ClientInformationPacket> handleClientInfo = (packet, connection) -> {
        System.out.println("[handle] client info");
    };

    private final PacketHandlerFn<CustomPayloadPacket> handleCustomPayload = (packet, connection) -> {
        System.out.println("[handle] plugin message");
        Identifier identifier = packet.id();
        System.out.println("payload id: " + identifier.toString());

        byte[] payload = packet.payload();

        try {
            String s = new String(payload, StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (Exception ignore) {
            System.out.println("Payload was not string encodable");
        }
    };

}
