package me.neb.orca.temp;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;

import java.util.EnumSet;
import java.util.Set;

public class BitMaskEnum {
    public static <E extends Enum<E> & BitFlag> Set<E> fromBitmask(Class<E> enumClass, int mask) {
        EnumSet<E> set = EnumSet.noneOf(enumClass);
        for (E constant : enumClass.getEnumConstants()) {
            if ((mask & constant.getBit()) != 0) {
                set.add(constant);
            }
        }
        return set;
    }

    public static <E extends Enum<E> & BitFlag> int toBitmask(Set<E> flags) {
        int mask = 0;
        for (E flag : flags) {
            mask |= flag.getBit();
        }
        return mask;
    }

    public static <E extends Enum<E> & BitFlag> ProtoBufCodec<Set<E>> unsignedByteCodec(Class<E> enumClass) {
        return new ProtoBufCodec<>() {
            @Override
            public Set<E> read(ProtoBuf buf) {
                int mask = buf.read(UNSIGNED_BYTE);
                return fromBitmask(enumClass, mask);
            }

            @Override
            public void write(ProtoBuf buf, Set<E> value) {
                int mask = toBitmask(value);
                buf.write(UNSIGNED_BYTE, mask);
            }
        };
    }

    public interface BitFlag {
        int getBit();
    }
}
