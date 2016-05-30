package Managers;

import Devices.Device;
import Devices.Port;

/**
 *
 * @author Petr Beran
 */
public interface PortManager {
 
    void createPort();
 
    void releasePort(Port port);
 
    void releasePortById(Long id);
 
    void deletePort(Port id);
 
    void deletePortById(Long id);
 
    void updatePort(Port port);
 
    void listAllPortsOfDevice(Device device);
 
    void listAllAvailablePortsOfDevice(Device device);
 
    Port findPortById(Long id);
 
}