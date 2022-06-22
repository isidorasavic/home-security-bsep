package com.ftn.MyHousebackend.model.enums;

public enum DeviceType {
    DOOR ("door_front"), LIGHT ("lightbulb"), THERMOSTAT ("thermostat"), HOME_APPLIANCE ("kitchen");

    private final String name;

    private DeviceType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String getName() {
        return this.name;
    }
}
