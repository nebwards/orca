package me.neb.orca.protocol;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.protocol.packet.client.configuration.ClientInformationPacket;
import me.neb.orca.protocol.packet.client.configuration.CustomPayloadPacket;
import me.neb.orca.protocol.packet.client.configuration.FinishConfigurationPacket;
import me.neb.orca.protocol.packet.client.handshake.IntentionPacket;
import me.neb.orca.protocol.packet.client.login.HelloPacket;
import me.neb.orca.protocol.packet.client.login.LoginAcknowledgedPacket;
import me.neb.orca.protocol.packet.client.status.PingRequestPacket;
import me.neb.orca.protocol.packet.client.status.StatusRequestPacket;

import java.util.LinkedHashMap;
import java.util.Map;

public class Protocol {
    public static final Protocol HANDSHAKE = new Builder()
            .packet(IntentionPacket.CODEC)
            .build();

    public static final Protocol STATUS = new Builder()
            .packet(StatusRequestPacket.CODEC)
            .packet(PingRequestPacket.CODEC)
            .build();

    public static final Protocol LOGIN = new Builder()
            .packet(HelloPacket.CODEC)
            .packet(0x03, LoginAcknowledgedPacket.CODEC)
            .build();

    public static final Protocol CONFIGURATION = new Builder()
            .packet(ClientInformationPacket.CODEC)
            .packet(0x02, CustomPayloadPacket.CODEC)
            .packet(0x03, FinishConfigurationPacket.CODEC)
            .build();

    private final Map<Integer, ProtoBufCodec<? extends Packet>> packetCodecs;

    private Protocol(Map<Integer, ProtoBufCodec<? extends Packet>> packetCodecs) {
        this.packetCodecs = Map.copyOf(packetCodecs);
    }

    public Packet construct(int id, ProtoBuf buf) {
        var codec = packetCodecs.get(id);
        if (codec == null) {
            throw new UnsupportedOperationException("Unknown packet id: " + id);
        }
        return codec.read(buf);
    }

    private static class Builder {
        private final Map<Integer, ProtoBufCodec<? extends Packet>> registry = new LinkedHashMap<>();
        private int nextId = 0;

        public <P extends Packet> Builder packet(ProtoBufCodec<P> codec) {
            registry.put(nextId++, codec);
            return this;
        }

        public <P extends Packet> Builder packet(int id, ProtoBufCodec<P> codec) {
            registry.put(id, codec);
            return this;
        }

        public Protocol build() {
            return new Protocol(registry);
        }
    }
}
