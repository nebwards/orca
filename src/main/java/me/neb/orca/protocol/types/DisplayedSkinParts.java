package me.neb.orca.protocol.types;

import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.temp.BitMaskEnum;

import java.util.Set;

public enum DisplayedSkinParts implements BitMaskEnum.BitFlag {
    CAPE(0x01), JACKET(0x02), LEFT_SLEEVE(0x04), RIGHT_SLEEVE(0x08), LEFT_PANTS_LEG(0x10), RIGHT_PANTS_LEG(0x20), HAT(0x40);

    public static ProtoBufCodec<Set<DisplayedSkinParts>> CODEC = BitMaskEnum.unsignedByteCodec(DisplayedSkinParts.class);

    private final int bit;

    DisplayedSkinParts(int bit) {
        this.bit = bit;
    }

    @Override
    public int getBit() {
        return bit;
    }
}
