package cz.muni.fi.pb138.Devices;

/**
 * Class representing port.
 *
 * @author Kristýna Leknerová
 * @author Petr Beran
 */
public class Port {

    protected Device deviceA;
    protected Device deviceB;

    /**
     *
     * @param deviceA First of the connected devices.
     * @param deviceB Second of the connected device.
     */
    public Port(Device deviceA, Device deviceB) {
        this.deviceA = deviceA;
        this.deviceB = deviceB;
    }

    public Device getDeviceA() {
        return deviceA;
    }

    public Device getDeviceB() {
        return deviceB;
    }

}
