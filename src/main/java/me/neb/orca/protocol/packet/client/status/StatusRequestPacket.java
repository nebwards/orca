package me.neb.orca.protocol.packet.client.status;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

public class StatusRequestPacket implements Packet {
    public static ProtoBufCodec<StatusRequestPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public StatusRequestPacket read(ProtoBuf buf) {
            return new StatusRequestPacket();
        }

        @Override
        public void write(ProtoBuf buf, StatusRequestPacket packet) {
        }
    };
}
