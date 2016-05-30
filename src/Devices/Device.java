package Devices;

import java.lang.reflect.Constructor;
import java.util.Collections;

/**
 * Represents any device in the network.
 * 
 * @param did           Required, device ID
 * @param deviceType    Required, device type
 * @param address       Required, device address
 * @param numberOfPorts Required, maximal number of ports
 * @param arrayOfPorts  Optional, array of device ports
 * @param name          Optional, device name. Its setter is exposed.
 * 
 * @author Kristýna Leknerová
 */
public class Device {
    private String did;             // required
    private DeviceType deviceType;  // required
    private String address;         // required
    private int numberOfPorts;      // required

    private final Port[] arrayOfPorts;      // optional
    private String name;                    // optional
    
    /*** Constructor ***/
    private Device(String did, DeviceType deviceType, String address, int numberOfPorts, Port[] arrayOfPorts, String name) {
        this.did = did;
        this.deviceType = deviceType;
        this.address = address;
        this.numberOfPorts = numberOfPorts;
        this.arrayOfPorts = arrayOfPorts;
        this.name = name;
    }
    
    /*** Getters ***/
    public String getDid() {
        return did;
    }
    
    public DeviceType getDeviceType() {
        return deviceType;
    }
    
    public String getAddress() {
        return address;
    }
    
    /**
     * 
     * @return Mutable array of ports.
     */
    public Port[] getArrayOfPorts() {
        // In case of need for immutable array, use deep copy (or list)
        return arrayOfPorts;
    }
    
    public String getName() {
        return name;
    }
    
    /*** Setters ***/
    
    public void setName(String name) {
        this.name = name;
    }
    
    /*** Builder ***/
    
    public static class Builder {
        private String did;             // required
        private DeviceType deviceType;  // required
        private String address;         // required
        private int numberOfPorts;      // required
        private Port[] arrayOfPorts;    // optional
        private String name;            // optional
        
        public Builder(String did, DeviceType deviceType, String address, int numberOfPorts) {
            this.did = did;
            this.deviceType = deviceType;
            this.address = address;
            this.numberOfPorts = numberOfPorts;
        }
        
        /**
         * Adds specified arrayOfPorts as an attribute to the Device.
         * @param arrayOfPorts  Array of Ports. Its length can't be greater than numberOfPorts.
         * @return 
         */
        public Builder arrayOfPorts(Port[] arrayOfPorts) {
            if (arrayOfPorts.length > numberOfPorts) {
                throw new IllegalArgumentException("Actual number of ports is higher than allowed maximal number of ports");
            }
            
            this.arrayOfPorts = arrayOfPorts;
            return this;
        }
        
        public Builder arrayOfPorts() {
            this.arrayOfPorts = new Port[numberOfPorts];
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Device build() {
            return new Device(did, deviceType, address, numberOfPorts, arrayOfPorts, name);
        }
    }
}
