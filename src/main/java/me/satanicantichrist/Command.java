package me.satanicantichrist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Command {
    private final String name;
    private final List<Flag> flags = new ArrayList<>();

    public Command(String name) {
        this.name = name;
    }

    public void addFlag(Flag flag) {
        this.flags.add(flag);
    }

    public String getName() {
        return this.name;
    }

    public List<Flag> getFlags() {
        return this.flags;
    }

    public Flag getFlag(String name) {
        for (Flag flag : flags) {
            if (Objects.equals(flag.getNameLong(), name)) return flag;
        }
        return null;
    }

    public void onRun() {
    }
}
