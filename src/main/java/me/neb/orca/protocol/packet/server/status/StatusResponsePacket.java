package me.neb.orca.protocol.packet.server.status;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.server.ServerPacket;

public record StatusResponsePacket(String json) implements ServerPacket {
    public static ProtoBufCodec<StatusResponsePacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public StatusResponsePacket read(ProtoBuf buf) {
            return new StatusResponsePacket(buf.read(STRING));
        }

        @Override
        public void write(ProtoBuf buf, StatusResponsePacket packet) {
            buf.write(STRING, packet.json());
        }
    };
}
