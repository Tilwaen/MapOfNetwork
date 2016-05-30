package cz.muni.fi.pb138.Devices;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents any device in the network.
 *
 * @author Kristýna Leknerová
 * @author Petr Beran
 */
public class Device {

    private String did;             // required
    private DeviceType deviceType;  // required
    private String address;         // required
    private int numberOfEthernetPorts;      // required
    private int numberOfWifiPorts; // required
    
    private final ArrayList<Port> arrayOfEthernetPorts;      // optional
    private final ArrayList<Port> arrayOfWifiPorts; // optional
    private String name;                    // optional

    /**
     *
     * Constructor for Device class
     *
     * @param did           Required, device ID
     * @param deviceType    Required, device type
     * @param address       Required, device address
     * @param numberOfEthernetPorts Required, maximal number of ports
     * @param arrayOfEthernetPorts  Optional, array of device ports
     * @param name          Optional, device name. Its setter is exposed.
     */
    private Device(String did, DeviceType deviceType, String address,
            int numberOfEthernetPorts, ArrayList<Port> arrayOfEthernetPorts, 
            int numberOfWifiPorts, ArrayList<Port> arrayOfWifiPorts, String name) {

        this.did = did;
        this.deviceType = deviceType;
        this.address = address;
        this.numberOfEthernetPorts = numberOfEthernetPorts;
        this.arrayOfEthernetPorts = arrayOfEthernetPorts;
        this.numberOfWifiPorts = numberOfWifiPorts;
        this.arrayOfWifiPorts = arrayOfWifiPorts;
        this.name = name;
    }

    /**
     * * Getters **
     */
    public String getDid() {
        return did;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getAddress() {
        return address;
    }
    
    public int getNumberOfEthernetPorts(){
        return numberOfEthernetPorts;
    }
    public int getNumberOfWifiPorts(){
        return numberOfWifiPorts;
    }

    /**
     *
     * @return Mutable array of ports.
     */
    public ArrayList<Port> getArrayOfEthernetPorts() {
        // In case of need for immutable array, use deep copy (or list)
        return arrayOfEthernetPorts;
    }
    public ArrayList<Port> getArrayOfWifiPorts() {
        // In case of need for immutable array, use deep copy (or list)
        return arrayOfWifiPorts;
    }

    public String getName() {
        return name;
    }

    /**
     * * Setters **
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * * Builder **
     */
    public static class Builder {

        private String did;                     // required
        private DeviceType deviceType;          // required
        private String address;                 // required
        private int numberOfEthernetPorts;              // required
        private int numberOfWifiPorts;              // required
        private ArrayList<Port> arrayOfEthernetPorts;   // optional
        private ArrayList<Port> arrayOfWifiPorts;   // optional
        private String name;                    // optional

        
        
        public Builder(String did, DeviceType deviceType, String address, int numberOfEthernetPorts) {
            this(did, deviceType, address, numberOfEthernetPorts, 0);
        }
        public Builder(String did, DeviceType deviceType, String address, int numberOfEthernetPorts, int numberOfWifiPorts) {
            this.did = did;
            this.deviceType = deviceType;
            this.address = address;
            this.numberOfEthernetPorts = numberOfEthernetPorts;
            this.numberOfWifiPorts = numberOfWifiPorts;
        }

        /**
         * Adds specified arrayOfEthernetPorts as an attribute to the Device.
         *
         * @param arrayOfEthernetPorts Array of Ports. Its length can't be greater than
 numberOfEthernetPorts.
         * @return
         */
        public Builder arrayOfEthernetPorts(ArrayList<Port> arrayOfEthernetPorts) {
            if (arrayOfEthernetPorts.size() > numberOfEthernetPorts) {
                throw new IllegalArgumentException("Actual number of ports is "
                        + "higher than allowed maximal number of ports");
            }

            this.arrayOfEthernetPorts = arrayOfEthernetPorts;
            return this;
        }

        public Builder arrayOfEthernetPorts() {
            ArrayList<Port> arrayOfPorts = new ArrayList();
            for (int i = 0; i < numberOfEthernetPorts; i++){
                arrayOfPorts.add(null);
            }
            this.arrayOfEthernetPorts = arrayOfPorts;
            return this;
        }
        public Builder arrayOfWifiPorts(ArrayList<Port> arrayOfWifiPorts) {
            if (arrayOfWifiPorts.size() > 65535) {
                throw new IllegalArgumentException("Actual number of ports is "
                        + "higher than allowed maximal number of ports");
            }

            this.arrayOfWifiPorts = arrayOfWifiPorts;
            return this;
        }

        public Builder arrayOfWifiPorts() {
            ArrayList<Port> arrayOfPorts = new ArrayList();
            for (int i = 0; i < numberOfWifiPorts; i++){
                arrayOfPorts.add(null);
            }
            this.arrayOfWifiPorts = arrayOfPorts;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Device build() {
            return new Device(did, deviceType, address, numberOfEthernetPorts, arrayOfEthernetPorts, numberOfWifiPorts, arrayOfWifiPorts, name);
        }
    }
}
