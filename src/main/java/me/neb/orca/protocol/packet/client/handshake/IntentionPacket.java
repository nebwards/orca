package me.neb.orca.protocol.packet.client.handshake;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

public record IntentionPacket(int version, String address, int port, int intent) implements Packet {
    public static final ProtoBufCodec<IntentionPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public IntentionPacket read(ProtoBuf buf) {
            return new IntentionPacket(buf.read(VAR_INT), buf.read(STRING), buf.read(UNSIGNED_SHORT), buf.read(VAR_INT));
        }

        @Override
        public void write(ProtoBuf buf, IntentionPacket packet) {
            buf.write(VAR_INT, packet.version());
            buf.write(STRING, packet.address());
            buf.write(UNSIGNED_SHORT, packet.port());
            buf.write(VAR_INT, packet.intent());
        }
    };
}
