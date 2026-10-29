package com.example.MemorialLedger.Tombstone;

public enum Material {
    TURKISH_MARBLE("Turkish Marble"),
    MURA_MARBLE("Mura Marble"),
    BLACK_GRANITE("Black Granite"),
    GRAY_GRANITE("Gray Granite"),
    RED_GRANITE("Red Granite");

    private final String displayName;

    Material(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
