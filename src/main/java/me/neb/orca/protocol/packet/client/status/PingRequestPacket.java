package me.neb.orca.protocol.packet.client.status;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

public record PingRequestPacket(long payload) implements Packet {
    public static final ProtoBufCodec<PingRequestPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public PingRequestPacket read(ProtoBuf buf) {
            return new PingRequestPacket(buf.read(LONG));
        }

        @Override
        public void write(ProtoBuf buf, PingRequestPacket packet) {
            buf.write(LONG, packet.payload());
        }
    };
}
