package me.neb.orca.handler;

import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.server.Connection;

import java.util.LinkedHashMap;
import java.util.Map;

public class PacketHandler {
    private final Map<Class<? extends Packet>, PacketHandlerFn<? extends Packet>> handlers;

    private PacketHandler(Map<Class<? extends Packet>, PacketHandlerFn<? extends Packet>> handlers) {
        this.handlers = Map.copyOf(handlers);
    }

    @SuppressWarnings("unchecked")
    public void handle(Packet packet, Connection connection) {
        var handler = (PacketHandlerFn<Packet>) handlers.get(packet.getClass());
        if (handler == null) {
            System.out.println("[warn] No handler found for " + packet.getClass());
            return;
        }
        handler.handle(packet, connection);
    }

    public static class Builder {
        private final Map<Class<? extends Packet>, PacketHandlerFn<? extends Packet>> registry = new LinkedHashMap<>();

        public <P extends Packet> Builder handle(Class<P> type, PacketHandlerFn<P> handler) {
            registry.put(type, handler);
            return this;
        }

        public PacketHandler build() {
            return new PacketHandler(registry);
        }
    }
}
