package me.neb.orca.nbt.tag;

import me.neb.orca.nbt.Tag;

import java.util.*;
import java.util.stream.Collectors;

public record CompoundTag(Map<String, Tag> tags) implements Tag {
    public record Named(String name, CompoundTag tag) {
    }

    public static class Builder {
        private final Map<String, Tag> map = new HashMap<>();

        public Builder put(String name, byte value) {
            map.put(name, new ByteTag(value));
            return this;
        }

        public Builder put(String name, short value) {
            map.put(name, new ShortTag(value));
            return this;
        }

        public Builder put(String name, int value) {
            map.put(name, new IntTag(value));
            return this;
        }

        public Builder put(String name, long value) {
            map.put(name, new LongTag(value));
            return this;
        }

        public Builder put(String name, float value) {
            map.put(name, new FloatTag(value));
            return this;
        }

        public Builder put(String name, double value) {
            map.put(name, new DoubleTag(value));
            return this;
        }

        public Builder put(String name, byte... values) {
            map.put(name, new ByteArrayTag(values));
            return this;
        }

        public Builder put(String name, String value) {
            map.put(name, new StringTag(value));
            return this;
        }

        public Builder put(String name, List<String> values) {
            List<Tag> tags = values.stream().map(StringTag::new).collect(Collectors.toList());
            map.put(name, new ListTag(tags));
            return this;
        }

        public Builder put(String name, String... values) {
            return put(name, Arrays.asList(values));
        }

        public CompoundTag build() {
            return new CompoundTag(map);
        }

        public CompoundTag.Named name(String name) {
            return new CompoundTag.Named(name, build());
        }
    }
}
