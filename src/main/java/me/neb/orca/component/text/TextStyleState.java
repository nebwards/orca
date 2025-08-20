package me.neb.orca.component.text;

public enum TextStyleState {
    UNSET,
    TRUE,
    FALSE;

    public static TextStyleState from(boolean bool) {
        return bool ? TRUE : FALSE;
    }

    public boolean isEmpty() {
        return this == UNSET;
    }
}
