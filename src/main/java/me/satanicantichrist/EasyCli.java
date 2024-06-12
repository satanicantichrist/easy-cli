package me.satanicantichrist;

import java.util.ArrayList;
import java.util.Objects;

public class EasyCli {
    private static final ArrayList<Command> commands = new ArrayList<>();
    private static Command baseCommand;

    public static void setBaseCommand(Command command) {
        baseCommand = command;
    }

    public static Command getBaseCommand() {
        return baseCommand;
    }

    public static void addCommand(Command command) {
        commands.add(command);
    }

    public static void evaluateFlags(Command command, String[] argv) {
        for (int x = 0; x < argv.length; x++) {
            if (argv.length > 1) x++;
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
    }

    public static void run(String[] argv) {
        //Base command stuff:
        if (isFlag(argv[0])) {
            if (getBaseCommand() == null) {
                System.out.println("No default command!");
                return;
            }
            evaluateFlags(getBaseCommand(), argv);
            getBaseCommand().preRun();
            return;
        }
        for (Command command : commands) {
            if (!command.getName().equalsIgnoreCase(argv[0])) continue;
            evaluateFlags(command, argv);
            command.preRun();
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
        //addCommand(new ExampleCommand());
        //setBaseCommand(new ExampleBaseCommand());
        run(args);
    }
}