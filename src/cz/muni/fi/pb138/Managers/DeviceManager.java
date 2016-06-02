package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import java.util.List;

/**
 *
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public interface DeviceManager {
 
    /**
     * Create device without specifying the device did.
     * Device did is then randomly generated.
     * @param deviceType Type of device.
     * @param address Address of device. Required parameter, must be in correct MAC address format.
     * @param numberOfPorts Maximal number of ports. Must be positive number.
     * @param arrayOfPorts Array of ports. Optional parameter.
     * @param name Device name. Optional parameter.
     */
    public void createDevice(DeviceType deviceType, String address, int numberOfPorts, List<Port> arrayOfPorts, String name);
    
    void createDevice(Device device);
 
    void deleteDevice(Device device);
 
    void updateDevice(Device device);
 
    List<Device> listAllDevices();
 
    List<Device> listAllComputers();
 
    List<Device> listAllHubs();
 
    List<Device> listAllRouters();
 
    List<Device> listAllSwitches();
 
    Device findDeviceById(Long id);
    
    Device findDeviceByAddress(String address);
    
    int findEmptyPort(Device device);
    
    boolean isPortEmpty(Device device, int numberInArrayOfPorts);
}
