package me.satanicantichrist;

import java.util.ArrayList;
import java.util.Objects;

public class EasyCli {
    private static final ArrayList<Command> commands = new ArrayList<>();

    public static void addCommand(Command command) {
        commands.add(command);
    }

    public static void run(String[] argv) {
        for (Command command : commands) {
            if (!command.getName().equalsIgnoreCase(argv[0])) continue;
            for (int x = 1; x < argv.length; x++) {
                String arg = argv[x];
                if (!isFlag(arg)) {
                    System.out.println("Unexpected argument: " + arg);
                    return;
                }
                Flag flag = getFlag(command, arg);
                if (flag == null) {
                    System.out.println("Unexpected flag: " + arg);
                    return;
                }
                if (flag.isHaveValue()) {
                    if (argv.length <= x + 1) {
                        System.out.println("Missing value for flag: " + arg);
                        return;
                    }
                    if (isFlag(argv[x + 1])) {
                        System.out.println("Missing value for flag: " + arg);
                        return;
                    }

                    flag.setValue(argv[x + 1]);

                    x++;
                }
                flag.setInArgv(true);
            }
            command.onRun();
        }
    }

    private static Flag getFlag(Command command, String arg) {
        for (Flag flag : command.getFlags()) {
            if (Objects.equals(flag.getNameLong(), arg.replace("--", "")) || String.valueOf(flag.getNameShort()).equals(arg.replace("-", ""))) {
                return flag;
            }
        }
        return null;
    }

    private static boolean isFlag(String s) {
        return s.startsWith("-") || s.startsWith("--");
    }

    public static void main(String[] args) {
        run(args);
    }
}