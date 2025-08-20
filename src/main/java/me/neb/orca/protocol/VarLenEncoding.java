package me.neb.orca.protocol;

import java.nio.ByteBuffer;

public class VarLenEncoding {
    private static final int SEGMENT_BITS = 0x7F;
    private static final int CONTINUE_BIT = 0x80;

    public static int readVarInt(ByteBuffer buffer) {
        int value = 0;
        int position = 0;

        for (int i = 0; i < 5; i++) {
            byte currentByte = buffer.get();
            value |= (currentByte & SEGMENT_BITS) << position;

            if ((currentByte & CONTINUE_BIT) == 0) {
                return value;
            }

            position += 7;
        }

        throw new RuntimeException("VarInt is too big (read)");
    }

    public static void writeVarInt(ByteBuffer buffer, int value) {
        for (int i = 0; i < 5; i++) {
            int nextByte = value & SEGMENT_BITS;
            value >>>= 7;

            if (value == 0) {
                buffer.put((byte) nextByte);
                return;
            }

            buffer.put((byte) (nextByte | CONTINUE_BIT));
        }

        throw new RuntimeException("VarInt is too big (write)");
    }
}
