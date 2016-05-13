package Devices;

import java.lang.reflect.Constructor;

/**
 * Represents any device in the network.
 * 
 * @param did Required, device ID
 * @param deviceType Required, device type
 * @param address Required, device address
 * @param name Optional, device name
 * 
 * @author Kristýna Leknerová
 */
public abstract class Device {
    private String did;             // required
    private DeviceType deviceType;  // required
    private String address;         // required
    
    private String name;            // optional
    
    /*** Constructor ***/
    protected <T extends Device> Device(DeviceBuilder deviceBuilder) {
        this.did = deviceBuilder.did;
        this.deviceType = deviceBuilder.deviceType;
        this.address = deviceBuilder.address;
        this.name = deviceBuilder.name;
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
    
    public String getName() {
        return name;
    }
    
    /**
     * Builder for the class Device.
     * One does not simply add multiple optional parameters.
     * 
     * More about this specific pattern used is to be found here: 
     * https://leozgp.wordpress.com/2014/03/06/studying-builder-pattern-with-inheritance/
     */    
    public static abstract class DeviceBuilder<T extends DeviceBuilder<T>> {
        protected abstract T getThis();
        private final String did;
        private final DeviceType deviceType;
        private String address;
        private String name;
        
        // Required parameters passed in constructor
        protected DeviceBuilder(String did, String address, DeviceType deviceType) {
            this.did = did;
            this.address = address;
            this.deviceType = deviceType;
        }
        
        public T name(String name) {
            this.name = name;
            return getThis();
        }
    }
}
