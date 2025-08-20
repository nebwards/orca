package me.neb.orca.protocol.packet.client.login;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

public class LoginAcknowledgedPacket implements Packet {
    public static final ProtoBufCodec<LoginAcknowledgedPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public LoginAcknowledgedPacket read(ProtoBuf buf) {
            return new LoginAcknowledgedPacket();
        }

        @Override
        public void write(ProtoBuf buf, LoginAcknowledgedPacket packet) {
        }
    };
}
