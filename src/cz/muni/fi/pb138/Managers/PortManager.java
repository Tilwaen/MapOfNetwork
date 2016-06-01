package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Devices.PortType;
import java.util.ArrayList;

/**
 *
 * @author Petr Beran
 */
public interface PortManager {
 
    void createEthernetPort(Port port);
    
    void createPortFromXML(Port port, int numberOfPortInDeviceA, int numberOfPortInDeviceB);
 
    void deletePort(Port port);
 
    void updatePort(Port port, PortType portType);
 
    ArrayList<Port> listAllEthernetPortsOfDevice(Device device);
 
    //ArrayList<Port> listAllWifiPortsOfDevice(Device device);
 
}