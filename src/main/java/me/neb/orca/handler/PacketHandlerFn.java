package me.neb.orca.handler;

import me.neb.orca.protocol.packet.Packet;
import me.neb.orca.server.Connection;

@FunctionalInterface
public interface PacketHandlerFn<P extends Packet> {
    void handle(P packet, Connection connection);
}
