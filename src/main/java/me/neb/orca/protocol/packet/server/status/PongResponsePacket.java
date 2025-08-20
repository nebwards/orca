package me.neb.orca.protocol.packet.server.status;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.server.ServerPacket;

public record PongResponsePacket(long payload) implements ServerPacket {
    public static final ProtoBufCodec<PongResponsePacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public PongResponsePacket read(ProtoBuf buf) {
            return new PongResponsePacket(buf.read(LONG));
        }

        @Override
        public void write(ProtoBuf buf, PongResponsePacket packet) {
            buf.write(LONG, packet.payload());
        }
    };
}
