package me.satanicantichrist;

public class ExampleBaseCommand extends Command {
    public ExampleBaseCommand() {
        super("Easy-CLI", "Testing Base command");
        addFlag(new Flag(false, "version", 'v', "version"));
    }

    @Override
    public void onRun() {
        if (getFlag("version").isInArgv()) {
            System.out.println("version is 1.1.1.1");
        }
    }
}
