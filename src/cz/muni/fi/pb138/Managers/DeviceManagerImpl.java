package cz.muni.fi.pb138.Managers;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Main.ListOfDevices;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @version 1.6.2016
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
    public DeviceManagerImpl(@NotNull ListOfDevices listOfDevices) {
        if (listOfDevices == null) {
            throw new IllegalArgumentException("List of devices can't be null");
        }
        
        this.devices = listOfDevices;
    }
    
    @Override
    public void createDevice(@NotNull Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }

        if (hasValidDidOrAddress(device)) {
            devices.getListOfDevices().add(device);
        }
    }

    @Override
    public void deleteDevice(@NotNull Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        
        List<Device> listOfDevices = devices.getListOfDevices();
        
        if (!listOfDevices.contains(device)) {
            throw new IllegalArgumentException("No device with id " 
                    + device.getDid() + " found in the list of devices.");
        }
        else {
            listOfDevices.remove(device);
        }
    }

    /**
     * Updates the device.
     * The only things that can be updated this way are 
     * name, maximal number of ports and device type.
     * If the new maximal number of ports is lower than the original one, 
     * all connections over this number are cropped.
     * @param device Must have the same did and address of the device that is being updated
     */
    @Override
    public void updateDevice(@NotNull Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        
        List<Device> listOfDevices = devices.getListOfDevices();
        
        if (!listOfDevices.contains(device)) {
            throw new IllegalArgumentException("No device with id " 
                    + device.getDid() + " found in the list of devices.");
        }
        else {
            int index = listOfDevices.indexOf(device);
            Device originalDevice = listOfDevices.get(index);
            
            originalDevice.setNumberOfPorts(device.getNumberOfEthernetPorts());
            originalDevice.setName(device.getName());
            originalDevice.setDeviceType(device.getDeviceType());
        }
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
     * @return Copy of the list of all computers.
     */
    @Override
    public List<Device> listAllComputers() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.COMPUTER).collect(Collectors.toList());
    }

    /**
     * Gets list of all hubs.
     * @return Copy of the list of all hubs.
     */
    @Override
    public List<Device> listAllHubs() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.HUB).collect(Collectors.toList());
    }

    /**
     * Gets list of all routers.
     * @return Copy of the list of all routers.
     */
    @Override
    public List<Device> listAllRouters() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.ROUTER).collect(Collectors.toList());
    }

    /**
     * Gets list of all switches.
     * @return Copy of the list of all switches.
     */
    @Override
    public List<Device> listAllSwitches() {
        return devices.getListOfDevices().stream().filter(device -> device.isSwitch()).collect(Collectors.toList());
    }

    /**
     * Finds device by its ID.
     * @param id Unique identificator.
     * @return Device with given ID. Null if there is no such device.
     */
    @Nullable
    @Override
    public Device findDeviceById(String id) {
        Optional<Device> optional = devices.getListOfDevices().stream().filter(device -> device.getDid().equals(id)).findFirst();
        //Device myDevice = devices.getListOfDevices().stream().filter(device -> device.getDid().equals(id)).findFirst().get();

        if (!optional.isPresent()) {
            return null;
            //throw new IllegalArgumentException("No device with id " + id + " found.");
        }
        
        return optional.get();
    }

    /**
     * Finds device by its address.
     * @param adress Unique MAC address.
     * @return Device with given address. Null if there is no such device.
     */
    @Nullable
    @Override
    public Device findDeviceByAdress(String adress) {
        Optional<Device> optional = devices.getListOfDevices().stream().filter(device -> device.getAddress().equals(adress)).findFirst();

        if (!optional.isPresent()) {
            return null;
            //throw new IllegalArgumentException("No device with id " + adress + " found.");
        }
        
        return optional.get();
    }

    /**
     * Finds first empty ethernet port of the device.
     * Returns negative number if there is no empty port.
     * @param device Device.
     * @return Index of the arrayOfEthernetPorts (positive or zero number).
     * -1 if there is no such element.
     */
    @Override
    public int findEmptyPort(@NotNull Device device) {
        
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }
        
        List<Port> ethernetPorts = device.getArrayOfEthernetPorts();
        
        if (ethernetPorts == null) {
            throw new IllegalArgumentException("Device can't have null array of ports");
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
    public boolean isPortEmpty(@NotNull Device device, int numberInArrayOfPorts){         
        
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }
        
        return device.getArrayOfEthernetPorts().get(numberInArrayOfPorts) == null;
    }

    /**
     * Checks duplicity of did and address attribute of the newly formed device.
     * Devices are uniquely identified by their id (did) and address.
     * If there is any other device with the same did OR the same address, return false.
     * @param device Device. Must have unique both address and id (did).
     * @return True if there is no other device with the same did or address, 
     * false if any other device has the same did or address.
     */
    private boolean hasValidDidOrAddress(@NotNull Device device) {
        
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }
        
        Predicate<Device> similarDid = (n) -> n.getDid().equals(device.getDid());
        Predicate<Device> similarAddress = (n) -> n.getAddress().equals(device.getAddress());
        
        return !devices.getListOfDevices().stream().anyMatch(similarDid.or(similarAddress));
    }
}
