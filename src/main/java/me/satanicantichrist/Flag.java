package me.satanicantichrist;

public class Flag {
    private final boolean haveValue;
    private final String nameLong;
    private final char nameShort;
    private Object value;
    private boolean inArgv = false;
    private String description = "";

    public Flag(boolean haveValue, String nameLong, char nameShort) {
        this.haveValue = haveValue;
        this.nameLong = nameLong;
        this.nameShort = nameShort;
    }

    public Flag(boolean haveValue, String nameLong, char nameShort, String description) {
        this.haveValue = haveValue;
        this.nameLong = nameLong;
        this.nameShort = nameShort;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isInArgv() {
        return inArgv;
    }

    public void setInArgv(boolean inArgv) {
        this.inArgv = inArgv;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isHaveValue() {
        return haveValue;
    }

    public String getNameLong() {
        return nameLong;
    }

    public char getNameShort() {
        return nameShort;
    }

    public Object getValue() {
        return value;
    }
}
