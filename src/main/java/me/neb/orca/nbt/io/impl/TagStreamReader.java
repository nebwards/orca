package me.neb.orca.nbt.io.impl;

import me.neb.orca.nbt.Tag;
import me.neb.orca.nbt.io.TagReader;
import me.neb.orca.nbt.tag.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagStreamReader implements TagReader<DataInputStream> {
    @Override
    public CompoundTag read(DataInputStream in) {
        Tag.Type type = readType(in);

        if (type != Tag.Type.COMPOUND) {
            throw new RuntimeException("First tag is not a CompoundTag");
        }

        Tag tag = readTag(in, type);

        if (!(tag instanceof CompoundTag compoundTag)) {
            throw new RuntimeException("Tag is not a CompoundTag");
        }

        return compoundTag;
    }

    @Override
    public CompoundTag.Named readNamed(DataInputStream in) {
        Tag.Type type = readType(in);

        if (type != Tag.Type.COMPOUND) {
            throw new RuntimeException("First tag is not a CompoundTag");
        }

        String name;
        try {
            name = in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read first tag name");
        }

        Tag tag = readTag(in, type);

        if (!(tag instanceof CompoundTag compoundTag)) {
            throw new RuntimeException("Tag is not a CompoundTag");
        }

        return new CompoundTag.Named(name, compoundTag);
    }

    private Tag.Type readType(DataInputStream in) {
        int typeId;
        try {
            typeId = in.readByte();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read type ID");
        }
        return Tag.Type.fromInt(typeId);
    }

    private Tag readTag(DataInputStream in, Tag.Type type) {
        switch (type) {
            case BYTE -> {
                try {
                    return new ByteTag(in.readByte());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read byte");
                }
            }
            case SHORT -> {
                try {
                    return new ShortTag(in.readShort());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read short");
                }
            }
            case INT -> {
                try {
                    return new IntTag(in.readInt());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read int");
                }
            }
            case LONG -> {
                try {
                    return new LongTag(in.readLong());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read long");
                }
            }
            case FLOAT -> {
                try {
                    return new FloatTag(in.readFloat());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read float");
                }
            }
            case DOUBLE -> {
                try {
                    return new DoubleTag(in.readDouble());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read double");
                }
            }
            case BYTE_ARRAY -> {
                try {
                    int length = in.readInt();
                    byte[] arr = new byte[length];
                    in.readFully(arr);
                    return new ByteArrayTag(arr);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read byte array");
                }
            }
            case STRING -> {
                try {
                    return new StringTag(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read string");
                }
            }
            case LIST -> {
                try {
                    Tag.Type childType = readType(in);
                    int length = in.readInt();
                    List<Tag> items = new ArrayList<>(length);
                    for (int i = 0; i < length; i++) {
                        items.add(readTag(in, childType));
                    }
                    return new ListTag(items);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read list");
                }
            }
            case COMPOUND -> {
                try {
                    Map<String, Tag> map = new HashMap<>();
                    Tag.Type childType = readType(in);

                    while (childType != Tag.Type.END) {
                        String name = in.readUTF();
                        Tag tag = readTag(in, childType);
                        map.put(name, tag);
                        childType = Tag.Type.fromInt(in.readByte());
                    }
                    return new CompoundTag(map);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read compound");
                }
            }
            case INT_ARRAY -> {
                try {
                    int length = in.readInt();
                    int[] arr = new int[length];
                    for (int i = 0; i < length; i++) {
                        arr[i] = in.readInt();
                    }
                    return new IntArrayTag(arr);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read int array");
                }
            }
            case LONG_ARRAY -> {
                try {
                    int length = in.readInt();
                    long[] arr = new long[length];
                    for (int i = 0; i < length; i++) {
                        arr[i] = in.readLong();
                    }
                    return new LongArrayTag(arr);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to read long array");
                }
            }
            default -> throw new RuntimeException("Unknown tag with id " + type.id());
        }
    }
}
