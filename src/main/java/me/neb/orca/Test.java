package me.neb.orca;

import me.neb.orca.component.Component;
import me.neb.orca.component.codec.SNBTComponentCodec;
import me.neb.orca.component.text.TextColor;
import me.neb.orca.component.text.TextComponent;

public class Test {
    public static void main(String[] args) {
        TextComponent text = Component.text("hell\"o sir").append(" ok").underlined().color(TextColor.RED).append(Component.text("no way dude")).build();
        System.out.println(text.stringify());
    }
}
