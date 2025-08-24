package me.neb.orca.nbt.io;

import me.neb.orca.nbt.tag.CompoundTag;

public interface TagReader<T> {
    CompoundTag read(T in);

    CompoundTag.Named readNamed(T in);
}
