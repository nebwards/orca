package me.neb.orca.component.codec;

import me.neb.orca.component.Component;
import me.neb.orca.component.text.TextComponent;
import me.neb.orca.component.text.TextStyle;
import me.neb.orca.component.text.TextStyleState;

import java.util.List;

public class SNBTComponentCodec implements ComponentCodec<String> {
    protected SNBTComponentCodec() {
    }

    @Override
    public String fromComponent(Component component) {
        if (!(component instanceof TextComponent(String text, TextStyle style, List<Component> children))) {
            throw new UnsupportedOperationException();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{text:").append(wrapQuotes(escapeString(text)));

        if (!style.color().isEmpty()) {
            sb.append(",color:").append(wrapQuotes(escapeString(style.color().hex())));
        }

        appendTextStyleState(sb, "bold", style.bold());
        appendTextStyleState(sb, "italic", style.italic());
        appendTextStyleState(sb, "underlined", style.underlined());
        appendTextStyleState(sb, "strikethrough", style.strikethrough());
        appendTextStyleState(sb, "obfuscated", style.obfuscated());

        if (!children.isEmpty()) {
            sb.append(",extra:[");
            for (int i = 0; i < children.size(); i++) {
                sb.append(fromComponent(children.get(i)));
                if (i < children.size() - 1) {
                    sb.append(",");
                }
            }
            sb.append("]");
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public Component toComponent(String value) {
        throw new UnsupportedOperationException();
    }

    private void appendTextStyleState(StringBuilder sb, String key, TextStyleState state) {
        if (state == TextStyleState.UNSET) {
            return;
        }
        sb.append(",").append(key).append(":");
        if (state == TextStyleState.TRUE) {
            sb.append("true");
            return;
        }
        sb.append("false");
    }

    private String wrapQuotes(String input) {
        return "\"" + input + "\"";
    }

    private String escapeString(String input) {
        return input
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
