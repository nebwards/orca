package me.neb.orca.protocol.buffer;

import java.nio.ByteBuffer;

public class ProtoBuf {
    private final ByteBuffer buffer;

    public ProtoBuf(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public ByteBuffer unwrap() {
        return buffer;
    }

    public <T> T read(ProtoBufCodec<T> codec) {
        return codec.read(this);
    }

    public <T> void write(ProtoBufCodec<T> codec, T value) {
        codec.write(this, value);
    }

    public <T> void writePacket(int id, ProtoBufCodec<T> codec, T value) {
        int start = buffer.position();
        buffer.position(start + 3);

        write(ProtoBufCodec.VAR_INT, id); // write packet id
        write(codec, value); // write packet

        int end = buffer.position();
        int length = end - (start + 3);

        buffer.position(start);
        write(ProtoBufCodec.PACKET_LENGTH, length); // write packet length

        buffer.position(end);
    }

    public void advance(int length) {
        buffer.position(buffer.position() + length);
    }
}
