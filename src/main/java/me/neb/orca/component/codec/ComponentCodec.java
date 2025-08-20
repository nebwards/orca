package me.neb.orca.component.codec;

import me.neb.orca.component.Component;

public interface ComponentCodec<T> {
    ComponentCodec<String> SNBT = new SNBTComponentCodec();

    T fromComponent(Component component);

    Component toComponent(final T value);
}
