package me.neb.orca.protocol.packet.client.configuration;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.protocol.types.DisplayedSkinParts;

import java.util.Set;

public record ClientInformationPacket(String locale, byte viewDistance, int chatMode, boolean chatColors, Set<DisplayedSkinParts> displayedSkinParts, int mainHand, boolean enableTextFiltering, boolean allowServerListings, int particleStats) implements Packet {
    public static final ProtoBufCodec<ClientInformationPacket> CODEC = new ProtoBufCodec<>() {
        @Override
        public ClientInformationPacket read(ProtoBuf buf) {
            return new ClientInformationPacket(buf.read(STRING), buf.read(BYTE), buf.read(VAR_INT), buf.read(BOOLEAN), buf.read(DisplayedSkinParts.CODEC), buf.read(VAR_INT), buf.read(BOOLEAN), buf.read(BOOLEAN), buf.read(VAR_INT));
        }

        @Override
        public void write(ProtoBuf buf, ClientInformationPacket packet) {
            buf.write(STRING, packet.locale());
            buf.write(BYTE, packet.viewDistance());
            buf.write(VAR_INT, packet.chatMode());
            buf.write(BOOLEAN, packet.chatColors());
            buf.write(DisplayedSkinParts.CODEC, packet.displayedSkinParts());
            buf.write(VAR_INT, packet.mainHand());
            buf.write(BOOLEAN, packet.enableTextFiltering());
            buf.write(BOOLEAN, packet.allowServerListings());
            buf.write(VAR_INT, packet.particleStats());
        }
    };
}
