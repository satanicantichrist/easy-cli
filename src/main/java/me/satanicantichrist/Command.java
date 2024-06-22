package me.satanicantichrist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Command {
    private final String name;
    private final List<Flag> flags = new ArrayList<>();
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
        addFlag(new Flag(false, "help", 'h', "Prints this help text."));
    }

    public String getDescription() {
        return description;
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

    public String getHelp() {
        StringBuilder helpBuilder = new StringBuilder();
        helpBuilder.append("Help for Command ").append(this.name).append("\n");
        helpBuilder.append(this.description).append("\n");
        helpBuilder.append("Flags: ").append("\n");
        for (Flag flag : flags) {
            helpBuilder.append("    [--").append(flag.getNameLong()).append(" / -").append(flag.getNameShort()).append("] - ").append(flag.getDescription()).append("\n");
        }
        return helpBuilder.toString();
    }

    public void preRun() {
        if (getFlag("help").isInArgv()) {
            System.out.println(getHelp());
            return;
        }
        onRun();
    }

    public void onRun() {
    }
}
