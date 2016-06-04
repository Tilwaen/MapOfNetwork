package cz.muni.fi.pb138.Devices;

/**
 *
 * Enum for device type.
 * 
 * @author Kristýna Leknerová
 * @author Petr Beran
 */
public enum DeviceType {
    COMPUTER(0),
    HUB(1),
    MODEM(2),
    ROUTER(3),
    SWITCH12(4),
    SWITCH24(5),
    SWITCH48(6);
    
    private final int value;
    
    private DeviceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

