package me.neb.orca.component;

import me.neb.orca.component.text.TextComponent;

import java.util.List;

public interface Component {
    List<Component> children();

    static TextComponent.Builder text(String text) {
        return new TextComponent.Builder(text);
    }

    static TextComponent ofText(String text) {
        return text(text).build();
    }
}
