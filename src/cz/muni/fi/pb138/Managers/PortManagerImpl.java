package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Devices.PortType;
import cz.muni.fi.pb138.Main.ListOfDevices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 30.5.2016
 * @author Petr Beran
 */
public class PortManagerImpl implements PortManager {

    private ListOfDevices devices;
    private DeviceManagerImpl deviceManager = new DeviceManagerImpl();

    @Override
    public void createEthernetPort(Port port) {
        validatePort(port);
        Device DeviceA = port.getDeviceA();
        Device DeviceB = port.getDeviceB();
        try {
            if (port.getType() == PortType.ETHERNET) {
                List<Port> deviceAEthernetPorts = DeviceA.getArrayOfEthernetPorts();
                deviceAEthernetPorts.set(deviceManager.findEmptyPort(DeviceA), port);

                List<Port> deviceBEthernetPorts = DeviceB.getArrayOfEthernetPorts();
                deviceBEthernetPorts.set(deviceManager.findEmptyPort(DeviceB), port);
            }
            /*if (port.getType() == PortType.WIFI) {
                ArrayList<Port> deviceAWifiPorts = DeviceA.getArrayOfWifiPorts();
                deviceAWifiPorts.set(deviceManager.findEmptyPort(DeviceA), port);

                ArrayList<Port> deviceBWifiPorts = DeviceB.getArrayOfWifiPorts();
                deviceBWifiPorts.set(deviceManager.findEmptyPort(DeviceB), port);
            }*/
        } catch (UnsupportedOperationException e) {

        }
    }

    @Override
    public void createPortFromXML(Port port, int numberOfPortInDeviceA, int numberOfPortInDeviceB) {
        validatePort(port);
        Device DeviceA = port.getDeviceA();
        Device DeviceB = port.getDeviceB();
        try {
            if (port.getType() == PortType.ETHERNET) {
                deviceManager.isPortEmpty(port.getDeviceA(), numberOfPortInDeviceA);
                deviceManager.isPortEmpty(port.getDeviceB(), numberOfPortInDeviceB);

                List<Port> deviceAEthernetPorts = DeviceA.getArrayOfEthernetPorts();
                deviceAEthernetPorts.set(numberOfPortInDeviceA, port);

                List<Port> deviceBEthernetPorts = DeviceB.getArrayOfEthernetPorts();
                deviceBEthernetPorts.set(numberOfPortInDeviceB, port);
            }
            /*if (port.getType() == PortType.WIFI) {
                deviceManager.isPortEmpty(port.getDeviceA(), numberOfPortInDeviceA);
                deviceManager.isPortEmpty(port.getDeviceB(), numberOfPortInDeviceB);

                ArrayList<Port> deviceAWifiPorts = DeviceA.getArrayOfWifiPorts();
                deviceAWifiPorts.set(numberOfPortInDeviceA, port);

                ArrayList<Port> deviceBWifiPorts = DeviceB.getArrayOfWifiPorts();
                deviceBWifiPorts.set(numberOfPortInDeviceB, port);
            }*/

        } catch (UnsupportedOperationException | IllegalArgumentException e) {

        }
    }

    @Override
    public void deletePort(Port port) {
        if (port == null) {
            throw new IllegalArgumentException("Port is null!");
        }
        if (port.getType() == PortType.ETHERNET) {
            port.getDeviceA().getArrayOfEthernetPorts().set(port.getDeviceA().getArrayOfEthernetPorts().indexOf(port), null);
            port.getDeviceB().getArrayOfEthernetPorts().set(port.getDeviceB().getArrayOfEthernetPorts().indexOf(port), null);
            port = null;
        }
        /*if (port.getType() == PortType.WIFI) {
            port.getDeviceA().getArrayOfWifiPorts().remove(port);
            port.getDeviceB().getArrayOfWifiPorts().remove(port);
            port = null;
        }*/
    }

    @Override
    public void updatePort(Port port, PortType portType) {
        validatePort(port);
        
        if (port.getType() == portType){
            throw new IllegalArgumentException("Port type is the same");
        }
        
        if (port.getType() == PortType.ETHERNET){
            port.getDeviceA().getArrayOfEthernetPorts().set(port.getDeviceA().getArrayOfEthernetPorts().indexOf(port), null);
            port.getDeviceB().getArrayOfEthernetPorts().set(port.getDeviceB().getArrayOfEthernetPorts().indexOf(port), null);
            //port.getDeviceA().getArrayOfWifiPorts().add(port);
            //port.getDeviceB().getArrayOfWifiPorts().add(port);
        }
        
        /*if (port.getType() == PortType.WIFI){
            port.getDeviceA().getArrayOfEthernetPorts().set(deviceManager.findEmptyPort(port.getDeviceA()), port);
            port.getDeviceB().getArrayOfEthernetPorts().set(deviceManager.findEmptyPort(port.getDeviceB()), port);
            port.getDeviceA().getArrayOfWifiPorts().remove(port);
            port.getDeviceB().getArrayOfWifiPorts().remove(port);
        }*/
    }

    @Override
    public ArrayList<Port> listAllEthernetPortsOfDevice(Device device) {
        ArrayList<Port> listOfPorts = new ArrayList<>();
        for (Port port : device.getArrayOfEthernetPorts()) {
            listOfPorts.add(port);
        }
        return listOfPorts;
    }

    /*@Override
    public ArrayList<Port> listAllWifiPortsOfDevice(Device device) {
        ArrayList<Port> listOfPorts = new ArrayList<>();
        for (Port port : device.getArrayOfWifiPorts()) {
            listOfPorts.add(port);
        }
        return listOfPorts;
    }*/

    public void validatePort(Port port) {
        if (port.getType() == null) {
            throw new IllegalArgumentException("Port type is null!");
        }
        if (!Arrays.asList(PortType.values()).contains(port.getType())) {
            throw new IllegalArgumentException("Invalid type of port!");
        }
        if (port.getDeviceA() == null) {
            throw new IllegalArgumentException("DeviceA is null!");
        }
        if (port.getDeviceB() == null) {
            throw new IllegalArgumentException("DeviceB is null!");
        }
        /*if (port.getType() == PortType.ETHERNET && (port.getDeviceA().getArrayOfWifiPorts().contains(port) || port.getDeviceB().getArrayOfWifiPorts().contains(port))) {
            throw new IllegalArgumentException("Ethernet port is in wifi array!");
        }
        if (port.getType() == PortType.WIFI && (port.getDeviceA().getArrayOfEthernetPorts().contains(port) || port.getDeviceB().getArrayOfEthernetPorts().contains(port))) {
            throw new IllegalArgumentException("Ethernet port is in ethernet array!");
        }*/
    }

}
