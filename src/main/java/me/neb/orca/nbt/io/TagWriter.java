package me.neb.orca.nbt.io;

import me.neb.orca.nbt.tag.CompoundTag;

public interface TagWriter<T> {
    void write(T out, CompoundTag tag);

    void writeNamed(T out, CompoundTag.Named tag);
}
