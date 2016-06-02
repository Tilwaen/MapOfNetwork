package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import java.util.List;

/**
 *
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public interface DeviceManager {
 
    void createDevice(Device device);
 
    void deleteDevice(Device device);
 
    void updateDevice(Device device);
 
    List<Device> listAllDevices();
 
    List<Device> listAllComputers();
 
    List<Device> listAllHubs();
 
    List<Device> listAllRouters();
 
    List<Device> listAllSwitches();
 
    Device findDeviceById(Long id);
    
    Device findDeviceByAddress(String adress);
    
    int findEmptyPort(Device device);
    
    boolean isPortEmpty(Device device, int numberInArrayOfPorts);
}
