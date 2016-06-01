package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.Port;
import java.util.List;

/**
 *
 * @author Petr Beran
 */
public interface PortManager {
 
    void createPort(Port port);
    
    void createPortFromXML(Port port, int numberOfPortInDeviceA, int numberOfPortInDeviceB);
 
    void deletePort(Port port);
 
    List<Port> listAllPortsOfDevice(Device device);
 
}