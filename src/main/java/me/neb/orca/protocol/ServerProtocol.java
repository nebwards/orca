package me.neb.orca.protocol;

import me.neb.orca.protocol.buffer.ProtoBufCodec;
import me.neb.orca.protocol.packet.server.ServerPacket;
import me.neb.orca.protocol.packet.server.login.LoginFinishedPacket;
import me.neb.orca.protocol.packet.server.status.PongResponsePacket;
import me.neb.orca.protocol.packet.server.status.StatusResponsePacket;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerProtocol {
    public static final ServerProtocol HANDSHAKE = new ServerProtocol(Collections.emptyMap());

    public static final ServerProtocol STATUS = new Builder()
            .packet(StatusResponsePacket.class, StatusResponsePacket.CODEC)
            .packet(PongResponsePacket.class, PongResponsePacket.CODEC)
            .build();

    public static final ServerProtocol LOGIN = new Builder()
            .packet(0x02, LoginFinishedPacket.class, LoginFinishedPacket.CODEC)
            .build();

    public static final ServerProtocol CONFIGURATION = new ServerProtocol(Collections.emptyMap());

    private final Map<Class<? extends ServerPacket>, PacketInfo<? extends ServerPacket>> packetMap;

    private ServerProtocol(Map<Class<? extends ServerPacket>, PacketInfo<? extends ServerPacket>> packetMap) {
        this.packetMap = Map.copyOf(packetMap);
    }

    @SuppressWarnings("unchecked")
    public PacketInfo<ServerPacket> packetInfo(Class<? extends ServerPacket> clazz) {
        var info = packetMap.get(clazz);
        if (info == null) {
            throw new UnsupportedOperationException("Unknown server packet class: " + clazz.getName());
        }
        return (PacketInfo<ServerPacket>) info;
    }

    public record PacketInfo<P extends ServerPacket>(int id, ProtoBufCodec<P> codec) {
    }

    private static class Builder {
        private final Map<Class<? extends ServerPacket>, PacketInfo<? extends ServerPacket>> registry = new LinkedHashMap<>();
        private int nextId = 0;

        public <P extends ServerPacket> Builder packet(Class<P> clazz, ProtoBufCodec<P> codec) {
            registry.put(clazz, new PacketInfo<>(nextId++, codec));
            return this;
        }

        public <P extends ServerPacket> Builder packet(int id, Class<P> clazz, ProtoBufCodec<P> codec) {
            registry.put(clazz, new PacketInfo<>(id, codec));
            return this;
        }

        public ServerProtocol build() {
            return new ServerProtocol(registry);
        }
    }
}
