package me.neb.orca.protocol.packet.server.login;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.server.ServerPacket;

import java.util.UUID;

public record LoginFinishedPacket(UUID uuid, String username) implements ServerPacket {
    public static final ProtoBufCodec<LoginFinishedPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public LoginFinishedPacket read(ProtoBuf buf) {
            UUID uuid = buf.read(ProtoBufCodec.UUID);
            String username = buf.read(STRING);

            // Skip properties for now
            int propertiesLength = buf.read(VAR_INT);
            buf.advance(propertiesLength);

            return new LoginFinishedPacket(uuid, username);
        }

        @Override
        public void write(ProtoBuf buf, LoginFinishedPacket packet) {
            buf.write(UUID, packet.uuid());
            buf.write(STRING, packet.username());

            // Skip properties for now
            buf.write(VAR_INT, 0);
        }
    };
}
