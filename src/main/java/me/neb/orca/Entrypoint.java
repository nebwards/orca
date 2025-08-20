package me.neb.orca;

import me.neb.orca.server.VirtualThreadServer;

public class Entrypoint {
    private final VirtualThreadServer server = new VirtualThreadServer();

    public static void main(String[] args) throws Exception {
        Entrypoint entrypoint = new Entrypoint();
        entrypoint.server.start();
    }
}
