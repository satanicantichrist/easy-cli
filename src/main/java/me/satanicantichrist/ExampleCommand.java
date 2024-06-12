package me.satanicantichrist;

public class ExampleCommand extends Command {
    public ExampleCommand() {
        super("example", "Example Command for testing and showing how tu use this library.");
        addFlag(new Flag(true, "testFlag", 't', "Description"));
        addFlag(new Flag(false, "testFlag2", 'r', "Description 2"));
    }

    @Override
    public void onRun() {
        System.out.println(getFlag("testFlag").getValue());
    }
}
