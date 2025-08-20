package me.neb.orca.protocol.packet.client.configuration;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.temp.Identifier;

public record CustomPayloadPacket(Identifier id, byte[] payload) implements Packet {
    public static final ProtoBufCodec<CustomPayloadPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public CustomPayloadPacket read(ProtoBuf buf) {
            Identifier id = buf.read(Identifier.CODEC);
            int length = buf.read(VAR_INT);
            byte[] payload = buf.read(ProtoBufCodec.byteArray(length));
            return new CustomPayloadPacket(id, payload);
        }

        @Override
        public void write(ProtoBuf buf, CustomPayloadPacket packet) {
            int length = packet.payload().length;
            buf.write(Identifier.CODEC, packet.id());
            buf.write(VAR_INT, length);
            buf.write(ProtoBufCodec.byteArray(length), packet.payload());
        }
    };
}
