package me.neb.orca.protocol.packet.client.configuration;

import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;

public class FinishConfigurationPacket implements Packet {
    public static final ProtoBufCodec<FinishConfigurationPacket> CODEC = ProtoBufCodec.packet((_) -> new FinishConfigurationPacket(), (_, _) -> {});
}
