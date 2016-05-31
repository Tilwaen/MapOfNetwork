package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Main.ListOfDevices;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @version 31.5.2016
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public class DeviceManagerImpl implements DeviceManager{

    private ListOfDevices devices;
    
    public DeviceManagerImpl() {
        devices = new ListOfDevices();
        // other possibility = SomeClass.getListOfDevices();
    }
    
    /**
     * DeviceManager constructor.
     * @param listOfDevices Keeps array of devices.
     */
    public DeviceManagerImpl(ListOfDevices listOfDevices) {
        this.devices = listOfDevices;
    }
    
    @Override
    public void createDevice(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDevice(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDevice(Device device) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Gets list of all devices.
     * @return Unmodifiable list of all devices.
     */
    @Override
    public List<Device> listAllDevices() {
        return Collections.unmodifiableList(devices.getListOfDevices());
    }

    /**
     * Gets list of all computers.
     * @return Unmodifiable list of all computers.
     */
    @Override
    public List<Device> listAllComputers() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.COMPUTER).collect(Collectors.toList());
    }

    /**
     * Gets list of all hubs.
     * @return Unmodifiable list of all hubs.
     */
    @Override
    public List<Device> listAllHubs() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.HUB).collect(Collectors.toList());
    }

    /**
     * Gets list of all routers.
     * @return Unmodifiable list of all routers.
     */
    @Override
    public List<Device> listAllRouters() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.ROUTER).collect(Collectors.toList());
    }

    @Override
    public List<Device> listAllSwitches() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // return devices.getListOfDevices().stream().filter(device -> device.isSwitch()).collect(Collectors.toList());
    }

    /**
     * Finds device by its ID.
     * @param id Unique identificator.
     * @return Device with given ID. Throws an exception if there is no such device.
     */
    @Override
    public Device findDeviceById(String id) {
        Optional<Device> optional = devices.getListOfDevices().stream().filter(device -> device.getName().equals(id)).findFirst();
        //Device myDevice = devices.getListOfDevices().stream().filter(device -> device.getName().equals(id)).findFirst().get();

        if (!optional.isPresent()) {
            // return null instead?
            throw new IllegalArgumentException("No device with id " + id + " found.");
        }
        
        return optional.get();
    }

    /**
     * Finds device by its address.
     * @param adress Unique MAC address.
     * @return Device with given address. Throws an exception if there is no such device.
     */
    @Override
    public Device findDeviceByAdress(String adress) {
        Optional<Device> optional = devices.getListOfDevices().stream().filter(device -> device.getAddress().equals(adress)).findFirst();
        //Device myDevice = devices.getListOfDevices().stream().filter(device -> device.getName().equals(id)).findFirst().get();

        if (!optional.isPresent()) {
            // return null instead?
            throw new IllegalArgumentException("No device with id " + adress + " found.");
        }
        
        return optional.get();
    }

    /**
     * Finds first empty ethernet port of the device.
     * Throws an exception if there is no empty port.
     * @param device Device.
     * @return Index of the arrayOfEthernetPorts (positive or zero number).
     * -1 if there is no such element.
     */
    @Override
    public int findEmptyPort(Device device) {
        
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }
        
        List<Port> ethernetPorts = device.getArrayOfEthernetPorts();
        
        if (ethernetPorts == null) {
            throw new IllegalArgumentException("Device can't be null");
        }
        
        for (int i = 0; i < ethernetPorts.size(); i++) {
            if (ethernetPorts.get(i) == null) {
                return i;
            }
        }
        
        // No empty port
        return -1;
    }

    /**
     * Checks whether the device ethernet port is occupied or not.
     * @param device Device.
     * @param numberInArrayOfPorts Index of the ethernet port in the array of device ethernet ports.
     * @return True if there is no Port on the specified index; false if the port is already occupied.
     */
    @Override
    public boolean isPortEmpty(Device device, int numberInArrayOfPorts){         
        return device.getArrayOfEthernetPorts().get(numberInArrayOfPorts) == null;
    }

}
