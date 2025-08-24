package me.neb.orca.nbt;

import me.neb.orca.nbt.io.impl.TagStreamReader;
import me.neb.orca.nbt.io.impl.TagStringWriter;
import me.neb.orca.nbt.tag.CompoundTag;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class NbtTest {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\ben\\Documents\\Development\\Java\\orca\\nbttest\\bigtest.nbt");
        GZIPInputStream gzip = new GZIPInputStream(file);

        TagStreamReader reader = new TagStreamReader();

        CompoundTag.Named tag = reader.readNamed(new DataInputStream(gzip));

        System.out.println(tag);

        gzip.close();
        file.close();

        CompoundTag ct = Tag.compound()
                .put("key", "value")
                .put("key 2", "value 1", "value 2", "value 3")
                .build();

        String test = TagStringWriter.stringify(ct);
        System.out.println(test);
    }
}
