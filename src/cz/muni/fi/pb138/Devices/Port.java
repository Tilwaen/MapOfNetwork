package cz.muni.fi.pb138.Devices;

/**
 * Class representing port.
 * 
 * @author Kristýna Leknerová
 * @author Petr Beran
 */
public class Port {
    protected PortType type;
    protected Device deviceA;
    protected Device deviceB;
    
    /**
     *
     * @param portType  Port type, either ethernet or wifi.
     * @param deviceA   First of the connected devices.
     * @param deviceB   Second of the connected device.
     */
    
    public Port(PortType portType, Device deviceA, Device deviceB){
        this.type = portType;
        this.deviceA = deviceA;
        this.deviceB = deviceB;
    }

    public PortType getType() {
        return type;
    }

    public Device getDeviceA() {
        return deviceA;
    }

    public Device getDeviceB() {
        return deviceB;
    }

}
