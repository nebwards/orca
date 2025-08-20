package me.neb.orca.component.text;

public class TextStyle {
    private TextColor color = TextColor.EMPTY;
    private TextStyleState bold = TextStyleState.UNSET;
    private TextStyleState italic = TextStyleState.UNSET;
    private TextStyleState underlined = TextStyleState.UNSET;
    private TextStyleState strikethrough = TextStyleState.UNSET;
    private TextStyleState obfuscated = TextStyleState.UNSET;

    public boolean isEmpty() {
        return color.isEmpty() && bold.isEmpty() && italic.isEmpty() && underlined.isEmpty() && strikethrough.isEmpty() && obfuscated.isEmpty();
    }

    public TextColor color() {
        return color;
    }

    public void color(TextColor color) {
        this.color = color;
    }

    public TextStyleState bold() {
        return bold;
    }

    public void bold(TextStyleState bold) {
        this.bold = bold;
    }

    public TextStyleState italic() {
        return italic;
    }

    public void italic(TextStyleState italic) {
        this.italic = italic;
    }

    public TextStyleState underlined() {
        return underlined;
    }

    public void underlined(TextStyleState underlined) {
        this.underlined = underlined;
    }

    public TextStyleState strikethrough() {
        return strikethrough;
    }

    public void strikethrough(TextStyleState strikethrough) {
        this.strikethrough = strikethrough;
    }

    public TextStyleState obfuscated() {
        return obfuscated;
    }

    public void obfuscated(TextStyleState obfuscated) {
        this.obfuscated = obfuscated;
    }
}
