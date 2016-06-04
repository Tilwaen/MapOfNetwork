package cz.muni.fi.pb138.Devices;

import cz.muni.fi.pb138.Managers.PortManagerImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Represents any device in the network.
 *
 * @version 1.6.2016
 * @author Kristýna Leknerová
 */
public class Device {

    private Long did;             // required
    private DeviceType deviceType;  // required
    private String address;         // required
    private int numberOfPorts;      // required
    
    private List<Port> arrayOfPorts;// optional; has fixed size (CAN use get/set, CAN'T use add/remove)
    private String name;            // optional

    /**
     * Constructor for Device class.
     * Private, only the Builder may access it.
     * Device class must always be constructed via its Builder.
     *
     * @param did           Required, device ID
     * @param deviceType    Required, device type
     * @param address       Required, device address
     * @param numberOfPorts Required, maximal number of ports
     * @param arrayOfPorts  Optional, array of device ports
     * @param name          Optional, device name.
     */
    private Device(Long did, DeviceType deviceType, String address,
            int numberOfPorts, List<Port> arrayOfPorts, String name) {

        this.did = did;
        this.deviceType = deviceType;
        this.address = address;
        this.numberOfPorts = numberOfPorts;
        this.name = name;
        
        if (arrayOfPorts == null) {
            Port[] array = new Port[numberOfPorts];
            Arrays.fill(array, null);
            this.arrayOfPorts = Arrays.asList(array);
        }
        else {
            this.arrayOfPorts = arrayOfPorts;
        }
    }

    /*** Getters ***/
    
    public Long getDid() {
        return did;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getAddress() {
        return address;
    }
    
    public int getNumberOfPorts(){
        return numberOfPorts;
    }

    /**
     * Gets arrayList of ethernet ports.
     * @return Mutable array of ports.
     */
    public List<Port> getArrayOfPorts() {
        // In case of need for immutable array, use deep copy (or list)
        return arrayOfPorts;
    }

    public String getName() {
        return name;
    }

    /*** Setters ***/
    
    /**
     * Sets new maximal number of ethernet ports.
     * If it's lower than the current number of connections,
     * crops all connections with port numbers over this number.
     * @param numberOfPorts New maximal number of ethernet ports
     */
    public void setNumberOfPorts(int numberOfPorts) {
        // TODO: GET THIS PORT MANAGER AS SomeClass.GetPortManager(), DON'T CREATE A NEW ONE
        PortManagerImpl portManager = new PortManagerImpl();
            
        // Delete all connections over the new port number
        for (int i = numberOfPorts; i < arrayOfPorts.size(); i++) {
            if (arrayOfPorts.get(i) != null) {
                portManager.deletePort(arrayOfPorts.get(i));
            }
        }
        
        // Remap the array. Set original values as far as possible,
        // but if the new array is larger, set remaining fields as null.
        Port[] portArray = new Port[numberOfPorts];
        
        for (int i = 0; i < numberOfPorts; i++) {
            portArray[i] = (i < arrayOfPorts.size()) ? arrayOfPorts.get(i) : null;
        }

        arrayOfPorts = Arrays.asList(portArray);
        this.numberOfPorts = numberOfPorts;
    }
    
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    /*** Methods ***/
    
    /**
     * Checks whether the device is a switch or not.
     * Switch can be further divided into these subtypes: switch12, switch24, switch48.
     * @return True is the device is a switch, false if not.
     */
    public boolean isSwitch() {
        return deviceType == DeviceType.SWITCH12 || deviceType == DeviceType.SWITCH24 || deviceType == DeviceType.SWITCH48;
    }

    /*** Equals and hascode ***/
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Device other = (Device) obj;
        if (!Objects.equals(this.did, other.did)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.did);
        hash = 53 * hash + Objects.hashCode(this.address);
        return hash;
    }

    /*** Builder ***/
    
    public static class Builder {

        private Long did;                     // required
        private DeviceType deviceType;          // required
        private String address;                 // required
        private int numberOfPorts;              // required
        private List<Port> arrayOfPorts;        // optional
        private String name;                    // optional

        public Builder(Long did, DeviceType deviceType, String address, int numberOfEthernetPorts) {
            if (did == null) {
                throw new IllegalArgumentException("Did is null");
            }
            if (address == null) {
                throw new IllegalArgumentException("Address is null");
            }
            if (numberOfEthernetPorts < 0) {
                throw new IllegalArgumentException("Negative number of maximum ports");
            }
            
            String addressRegex = "(([0-9A-Fa-f]{2}[-:]){5}[0-9A-Fa-f]{2})|(([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4})";
            
            if (!address.matches(addressRegex)) {
                throw new IllegalArgumentException("Address format is incorrect. "
                        + "Device must have a correct MAC address (ie. 01:02:03:04:ab:cd or 01-02-03-04-ab-cd)");
            }
            
            this.did = did;
            this.deviceType = deviceType;
            this.address = address;
            this.numberOfPorts = numberOfEthernetPorts;
        }

        /**
         * Adds specified arrayOfPorts as an attribute to the Device.
         *
         * @param arrayOfPorts Array of Ports. Its length can't be greater than numberOfPorts.
         * @return
         */
        public Builder arrayOfPorts(List<Port> arrayOfPorts) {
            
            // Don't add anything if the parameter is null
            if (arrayOfPorts == null) {
                return this;
            }
            
            if (arrayOfPorts.size() > numberOfPorts) {
                throw new IllegalArgumentException("Actual number of ports is "
                        + "higher than allowed maximal number of ports");
            }

            this.arrayOfPorts = arrayOfPorts;
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
