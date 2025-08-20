package me.neb.orca.component.text;

import me.neb.orca.component.Component;
import me.neb.orca.component.codec.ComponentCodec;

import java.util.ArrayList;
import java.util.List;

public record TextComponent(String text, TextStyle style, List<Component> children) implements Component {
    public boolean isJustText() {
        return style.isEmpty() && children.isEmpty();
    }

    @Override
    public List<Component> children() {
        return children;
    }

    public String stringify() {
        return ComponentCodec.SNBT.fromComponent(this);
    }

    public static class Builder {
        private final StringBuilder sb = new StringBuilder();
        private final TextStyle style = new TextStyle();
        private final List<Component> children = new ArrayList<>();

        public Builder(String text) {
            sb.append(text);
        }

        public Builder color(TextColor color) {
            style.color(color);
            return this;
        }

        public Builder color(String hex) {
            style.color(new TextColor(hex));
            return this;
        }

//        public Builder gradient(String hexFrom, String hexTo) {
//            int size = sb.length();
//            if (size < 1) {
//                return this;
//            }
//
//            String[] colors = TextColor.hexLinearGrad(hexFrom, hexTo, size);
//            String text = sb.toString();
//
//            Builder builder = new Builder(String.valueOf(text.charAt(0))).color(colors[0]);
//            for (int i = 1; i < size; i++) {
//                builder.append(new Builder(String.valueOf(text.charAt(i))).color(colors[Math.min(i, colors.length - 1)]));
//            }
//
//            return builder;
//        }

        public Builder bold(boolean bold) {
            style.bold(TextStyleState.from(bold));
            return this;
        }

        public Builder bold() {
            return bold(true);
        }

        public Builder italic(boolean italic) {
            style.italic(TextStyleState.from(italic));
            return this;
        }

        public Builder italic() {
            return italic(true);
        }

        public Builder underlined(boolean underlined) {
            style.underlined(TextStyleState.from(underlined));
            return this;
        }

        public Builder underlined() {
            return underlined(true);
        }

        public Builder strikethrough(boolean strikethrough) {
            style.strikethrough(TextStyleState.from(strikethrough));
            return this;
        }

        public Builder strikethrough() {
            return strikethrough(true);
        }

        public Builder obfuscated(boolean obfuscated) {
            style.obfuscated(TextStyleState.from(obfuscated));
            return this;
        }

        public Builder obfuscated() {
            return obfuscated(true);
        }

        public Builder append(String text) {
            sb.append(text);
            return this;
        }

        public Builder append(Component component) {
            children.add(component);
            return this;
        }

        public Builder append(Builder builder) {
            return append(builder.build());
        }

        public TextComponent build() {
            return new TextComponent(sb.toString(), style, children);
        }
    }
}
