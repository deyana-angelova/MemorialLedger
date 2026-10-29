package com.example.MemorialLedger.Order;

public enum Status {
    Ordered("Ordered"),
    Machine_Processed("Machine Processed"),
    Polished("Polished"),
    Installed("Installed");

    private final String displayName;

    Status(String displayName){
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
