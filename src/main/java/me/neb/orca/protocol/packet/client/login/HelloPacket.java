package me.neb.orca.protocol.packet.client.login;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

import java.util.UUID;

public record HelloPacket(String username, UUID uuid) implements Packet {
    public static final ProtoBufCodec<HelloPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public HelloPacket read(ProtoBuf buf) {
            return new HelloPacket(buf.read(STRING), buf.read(UUID));
        }

        @Override
        public void write(ProtoBuf buf, HelloPacket packet) {
            buf.write(STRING, packet.username());
            buf.write(UUID, packet.uuid());
        }
    };
}
