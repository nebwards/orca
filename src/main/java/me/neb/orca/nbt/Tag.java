package me.neb.orca.nbt;

import me.neb.orca.nbt.tag.CompoundTag;

public interface Tag {
    static CompoundTag.Builder compound() {
        return new CompoundTag.Builder();
    }

    enum Type {
        END(0),
        BYTE(1),
        SHORT(2),
        INT(3),
        LONG(4),
        FLOAT(5),
        DOUBLE(6),
        BYTE_ARRAY(7),
        STRING(8),
        LIST(9),
        COMPOUND(10),
        INT_ARRAY(11),
        LONG_ARRAY(12);

        private final int id;

        Type(int id) {
            this.id = id;
        }

        public static Type fromInt(int id) {
            for (Type t : values()) {
                if (t.id == id) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Unknown tag with id " + id);
        }

        public int id() {
            return id;
        }
    }
}
