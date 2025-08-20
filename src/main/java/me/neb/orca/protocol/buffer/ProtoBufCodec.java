package me.neb.orca.protocol.buffer;

import me.neb.orca.protocol.VarLenEncoding;
import me.neb.orca.protocol.packet.Packet;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ProtoBufCodec<T> {
    ProtoBufCodec<Boolean> BOOLEAN = new ProtoBufCodec<>() {
        @Override
        public Boolean read(ProtoBuf buf) {
            return buf.unwrap().get() != 0;
        }

        @Override
        public void write(ProtoBuf buf, Boolean value) {
            buf.unwrap().put((byte) (value ? 1 : 0));
        }
    };

    ProtoBufCodec<Byte> BYTE = new ProtoBufCodec<>() {
        @Override
        public Byte read(ProtoBuf buf) {
            return buf.unwrap().get();
        }

        @Override
        public void write(ProtoBuf buf, Byte value) {
            buf.unwrap().put(value);
        }
    };

    ProtoBufCodec<Integer> UNSIGNED_BYTE = new ProtoBufCodec<>() {
        @Override
        public Integer read(ProtoBuf buf) {
            return buf.unwrap().get() & 0xFF;
        }

        @Override
        public void write(ProtoBuf buf, Integer value) {
            buf.unwrap().put((byte) (value & 0xFF));
        }
    };

    ProtoBufCodec<Short> SHORT = new ProtoBufCodec<>() {
        @Override
        public Short read(ProtoBuf buf) {
            return buf.unwrap().getShort();
        }

        @Override
        public void write(ProtoBuf buf, Short value) {
            buf.unwrap().putShort(value);
        }
    };

    ProtoBufCodec<Integer> UNSIGNED_SHORT = new ProtoBufCodec<>() {
        @Override
        public Integer read(ProtoBuf buf) {
            return buf.unwrap().getShort() & 0xFFFF;
        }

        @Override
        public void write(ProtoBuf buf, Integer value) {
            buf.unwrap().putShort((short) (value & 0xFFFF));
        }
    };

    ProtoBufCodec<Integer> INT = new ProtoBufCodec<>() {
        @Override
        public Integer read(ProtoBuf buf) {
            return buf.unwrap().getInt();
        }

        @Override
        public void write(ProtoBuf buf, Integer value) {
            buf.unwrap().putInt(value);
        }
    };

    ProtoBufCodec<Long> LONG = new ProtoBufCodec<>() {
        @Override
        public Long read(ProtoBuf buf) {
            return buf.unwrap().getLong();
        }

        @Override
        public void write(ProtoBuf buf, Long value) {
            buf.unwrap().putLong(value);
        }
    };

    ProtoBufCodec<Float> FLOAT = new ProtoBufCodec<>() {
        @Override
        public Float read(ProtoBuf buf) {
            return buf.unwrap().getFloat();
        }

        @Override
        public void write(ProtoBuf buf, Float value) {
            buf.unwrap().putFloat(value);
        }
    };

    ProtoBufCodec<Double> DOUBLE = new ProtoBufCodec<>() {
        @Override
        public Double read(ProtoBuf buf) {
            return buf.unwrap().getDouble();
        }

        @Override
        public void write(ProtoBuf buf, Double value) {
            buf.unwrap().putDouble(value);
        }
    };

    ProtoBufCodec<Integer> VAR_INT = new ProtoBufCodec<>() {
        @Override
        public Integer read(ProtoBuf buf) {
            return VarLenEncoding.readVarInt(buf.unwrap());
        }

        @Override
        public void write(ProtoBuf buf, Integer value) {
            VarLenEncoding.writeVarInt(buf.unwrap(), value);
        }
    };

    ProtoBufCodec<String> STRING = new ProtoBufCodec<>() {
        @Override
        public String read(ProtoBuf buf) {
            int length = buf.read(VAR_INT);
            byte[] bytes = new byte[length];
            buf.unwrap().get(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        }

        @Override
        public void write(ProtoBuf buf, String value) {
            int length = value.length();
            buf.write(VAR_INT, length);
            buf.unwrap().put(value.getBytes(StandardCharsets.UTF_8));
        }
    };

    ProtoBufCodec<Integer> PACKET_LENGTH = new ProtoBufCodec<>() {
        @Override
        public Integer read(ProtoBuf buf) {
            return buf.read(VAR_INT);
        }

        @Override
        public void write(ProtoBuf buf, Integer value) {
            buf.write(BYTE, (byte) (value & 0x7F | 0x80));
            buf.write(BYTE, (byte) ((value >>> 7) & 0x7F | 0x80));
            buf.write(BYTE, (byte) (value >>> 14));
        }
    };

    ProtoBufCodec<UUID> UUID = new ProtoBufCodec<UUID>() {
        @Override
        public UUID read(ProtoBuf buf) {
            long mostSignificantBits = buf.unwrap().getLong();
            long leastSignificantBits = buf.unwrap().getLong();
            return new UUID(mostSignificantBits, leastSignificantBits);
        }

        @Override
        public void write(ProtoBuf buf, UUID value) {
            buf.unwrap().putLong(value.getMostSignificantBits());
            buf.unwrap().putLong(value.getLeastSignificantBits());
        }
    };

    static ProtoBufCodec<byte[]> byteArray(int length) {
        return new ProtoBufCodec<>() {
            @Override
            public byte[] read(ProtoBuf buf) {
                byte[] bytes = new byte[length];
                buf.unwrap().get(bytes);
                return bytes;
            }

            @Override
            public void write(ProtoBuf buf, byte[] value) {
                buf.unwrap().put(value);
            }
        };
    }

    T read(ProtoBuf buf);

    void write(ProtoBuf buf, T value);

    static <P extends Packet> ProtoBufCodec<P> packet(Function<ProtoBuf, P> read, BiConsumer<ProtoBuf, P> write) {
        return new ProtoBufCodec<>() {
            @Override
            public P read(ProtoBuf buf) {
                return read.apply(buf);
            }

            @Override
            public void write(ProtoBuf buf, P value) {
                write.accept(buf, value);
            }
        };
    }
}
