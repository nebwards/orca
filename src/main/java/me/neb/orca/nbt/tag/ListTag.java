package me.neb.orca.nbt.tag;

import me.neb.orca.nbt.Tag;

import java.util.List;

public record ListTag(List<Tag> items) implements Tag {
}
