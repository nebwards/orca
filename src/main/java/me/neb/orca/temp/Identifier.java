package me.neb.orca.temp;

import me.neb.orca.protocol.buffer.ProtoBuf;
import me.neb.orca.protocol.buffer.ProtoBufCodec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Identifier(String namespace, String path) {
    public static String DEFAULT_NAMESPACE = "minecraft";

    public static final ProtoBufCodec<Identifier> CODEC = new ProtoBufCodec<Identifier>() {
        @Override
        public Identifier read(ProtoBuf buf) {
            return parse(buf.read(STRING));
        }

        @Override
        public void write(ProtoBuf buf, Identifier value) {
            buf.write(STRING, value.toString());
        }
    };

    public static Identifier of(String path) {
        return new Identifier(DEFAULT_NAMESPACE, path);
    }

    public static Identifier parse(String string) throws IllegalArgumentException {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Invalid identifier: input cannot be null or empty");
        }

        // Optional namespace: ([a-z0-9._-]+):
        // Value: ([a-z0-9._/-]+)
        String pattern = "^(?:([a-z0-9._-]+):)?([a-z0-9._/-]+)$";

        Pattern regex = java.util.regex.Pattern.compile(pattern);
        Matcher matcher = regex.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid identifier format: " + string);
        }

        String namespace = matcher.group(1);
        String value = matcher.group(2);

        if (namespace == null) {
            namespace = DEFAULT_NAMESPACE;
        }

        return new Identifier(namespace, value);
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }
}
