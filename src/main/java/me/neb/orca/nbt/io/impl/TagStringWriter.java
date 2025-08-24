package me.neb.orca.nbt.io.impl;

import me.neb.orca.nbt.Tag;
import me.neb.orca.nbt.io.TagWriter;
import me.neb.orca.nbt.tag.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TagStringWriter implements TagWriter<StringBuilder> {
    public static final TagStringWriter INSTANCE = new TagStringWriter();

    private static final String BYTE_END = "b";
    private static final String SHORT_END = "s";
    private static final String INT_END = "i";
    private static final String LONG_END = "l";
    private static final String FLOAT_END = "f";
    private static final String DOUBLE_END = "d";
    private static final String LIST_OPEN = "[";
    private static final String LIST_CLOSE = "]";
    private static final String LIST_DELIMITER = ",";
    private static final String BYTE_ARRAY_KEY = "B";
    private static final String INT_ARRAY_KEY = "I";
    private static final String LONG_ARRAY_KEY = "L";
    private static final String ARRAY_KEY_SEPARATOR = ";";
    private static final String COMPOUND_OPEN = "{";
    private static final String COMPOUND_CLOSE = "}";
    private static final String COMPOUND_DELIMITER = ",";

    public static String stringify(CompoundTag tag) {
        StringBuilder out = new StringBuilder();
        INSTANCE.write(out, tag);
        return out.toString();
    }

    public static String stringify(CompoundTag.Named tag) {
        StringBuilder out = new StringBuilder();
        INSTANCE.writeNamed(out, tag);
        return out.toString();
    }

    @Override
    public void write(StringBuilder out, CompoundTag tag) {
        appendTagValue(out, tag);
    }

    @Override
    public void writeNamed(StringBuilder out, CompoundTag.Named tag) {
        out.append(COMPOUND_OPEN).append(escapeString(tag.name())).append(COMPOUND_DELIMITER);
        appendKeyValue(out, tag.name(), tag.tag());
        out.append(COMPOUND_CLOSE);
    }

    private void appendKeyValue(StringBuilder out, String key, Tag tag) {
        out.append(escapeString(key)).append(":");
        appendTagValue(out, tag);
    }

    private void appendTagValue(StringBuilder out, Tag tag) {
        if (tag instanceof ByteTag(byte value)) {
            out.append(String.valueOf(value)).append(BYTE_END);
            return;
        }

        if (tag instanceof ShortTag(short value)) {
            out.append(String.valueOf(value)).append(SHORT_END);
            return;
        }

        if (tag instanceof IntTag(int value)) {
            out.append(value).append(INT_END);
            return;
        }

        if (tag instanceof LongTag(long value)) {
            out.append(value).append(LONG_END);
            return;
        }

        if (tag instanceof FloatTag(float value)) {
            out.append(value).append(FLOAT_END);
            return;
        }

        if (tag instanceof DoubleTag(double value)) {
            out.append(value).append(DOUBLE_END);
            return;
        }

        if (tag instanceof StringTag(String value)) {
            out.append(wrapQuotes(escapeString(value)));
            return;
        }

        if (tag instanceof ListTag(List<Tag> tags)) {
            out.append(LIST_OPEN);
            for (int i = 0; i < tags.size(); i++) {
                appendTagValue(out, tags.get(i));
                if (i < tags.size() - 1) {
                    out.append(LIST_DELIMITER);
                }
            }
            out.append(LIST_CLOSE);
            return;
        }

        if (tag instanceof CompoundTag(Map<String, Tag> tags)) {
            out.append(COMPOUND_OPEN);
            Iterator<Map.Entry<String, Tag>> iterator = tags.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Tag> entry = iterator.next();
                appendKeyValue(out, entry.getKey(), entry.getValue());
                if (iterator.hasNext()) {
                    out.append(COMPOUND_DELIMITER);
                }
            }
            out.append(COMPOUND_CLOSE);
            return;
        }

        if (tag instanceof ByteArrayTag(byte[] array)) {
            out.append(LIST_OPEN).append(BYTE_ARRAY_KEY).append(ARRAY_KEY_SEPARATOR);
            for (int i = 0; i < array.length; i++) {
                out.append(String.valueOf(array[i])).append(BYTE_END);
                if (i < array.length - 1) {
                    out.append(LIST_DELIMITER);
                }
            }
            out.append(LIST_CLOSE);
            return;
        }

        if (tag instanceof IntArrayTag(int[] array)) {
            Number[] boxed = Arrays.stream(array).boxed().toArray(Number[]::new);
            appendNumericArray(out, boxed, INT_ARRAY_KEY, INT_END);
        }

        if (tag instanceof LongArrayTag(long[] array)) {
            Number[] boxed = Arrays.stream(array).boxed().toArray(Number[]::new);
            appendNumericArray(out, boxed, LONG_ARRAY_KEY, LONG_END);
        }
    }

    private void appendNumericArray(StringBuilder out, Number[] array, String key, String end) {
        out.append(LIST_OPEN).append(key).append(ARRAY_KEY_SEPARATOR);
        for (int i = 0; i < array.length; i++) {
            out.append(array[i]).append(end);
            if (i < array.length - 1) {
                out.append(LIST_DELIMITER);
            }
        }
        out.append(LIST_CLOSE);
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
