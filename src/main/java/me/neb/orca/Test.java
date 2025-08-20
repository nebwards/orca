package me.neb.orca;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.server.status.StatusResponsePacket;

import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args) {
        StatusResponsePacket response = new StatusResponsePacket("{\n" +
                "    \"version\": {\n" +
                "        \"name\": \"1.21.8\",\n" +
                "        \"protocol\": 772\n" +
                "    },\n" +
                "    \"description\": {\"type\":\"text\",\"text\":\"hello\",\"color\":\"#55FFFF\",\"bold\":true,\"italic\":true,\"underlined\":true},\n" +
                "    \"enforcesSecureChat\": false\n" +
                "}");

        ProtoBuf buf = new ProtoBuf(ByteBuffer.allocateDirect(1024));

        buf.writePacket(1, StatusResponsePacket.CODEC, response);

        buf.unwrap().flip();
        System.out.println(buf.read(ProtoBufCodec.VAR_INT));
        System.out.println(buf.read(ProtoBufCodec.VAR_INT));
        System.out.println(buf.read(ProtoBufCodec.STRING));
    }
}
