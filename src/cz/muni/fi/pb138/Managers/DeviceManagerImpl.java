package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Main.ListOfDevices;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class implementing operations with devices from interface DeviceManager.
 *
 * @version 1.6.2016
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public class DeviceManagerImpl implements DeviceManager {

    private ListOfDevices devices;

    public DeviceManagerImpl() {
        devices = new ListOfDevices();
        // other possibility = SomeClass.getListOfDevices();
    }

    /**
     * DeviceManager constructor.
     *
     * @param listOfDevices Keeps array of devices.
     * @throws IllegalArgumentException if list of devices is null.
     */
    public DeviceManagerImpl(ListOfDevices listOfDevices) {
        if (listOfDevices == null) {
            throw new IllegalArgumentException("List of devices can't be null");
        }

        this.devices = listOfDevices;
    }
    
    @Override
    public void createDevice(DeviceType deviceType, String address, int numberOfPorts, List<Port> arrayOfPorts, String name) {

        if (address == null) {
            throw new IllegalArgumentException("Required parameter address is null");
        }
        
        Device device;
        int iterationCounter = 0;
        int maxIterations = 1000;
        
        // Select unused id
        do {
            UUID uuid = UUID.randomUUID();
            Long did = uuid.getLeastSignificantBits();
            did = Math.abs(did);
            
            device = new Device.Builder(did, deviceType, address, numberOfPorts)
                .arrayOfPorts(arrayOfPorts)
                .name(name)
                .build();
            
            iterationCounter++;
            
        } while(!hasValidDid(device) && iterationCounter < maxIterations);
        
        if (!hasValidAddress(device)) {
            throw new IllegalArgumentException("Address is in wrong format");
        }

        devices.getListOfDevices().add(device);
    }

    /**
     * Adds newly builded device to listOfDevices
     *
     * @param device Builded device to create in listOfDevices.
     * @throws IllegalArgumentException if device is null.
     */
    @Override
    public void createDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        if (hasValidDid(device) || hasValidAddress(device)) {
            devices.getListOfDevices().add(device);
        }
    }

    /**
     * Deletes specified device from listOfDevices. Also deletes all ports of
     * deleted device.
     *
     * @param device Selected device to delete.
     * @throws IllegalArgumentException of device is null or there is no device
     * with such id.
     */
    @Override
    public void deleteDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        List<Device> listOfDevices = devices.getListOfDevices();

        if (!listOfDevices.contains(device)) {
            throw new IllegalArgumentException("No device with id "
                    + device.getDid() + " found in the list of devices.");
        } else {
            listOfDevices.remove(device);
        }
    }

    /**
     * Updates the device. The only things that can be updated this way are
     * name, maximal number of ports and device type. If the new maximal number
     * of ports is lower than the original one, all connections over this number
     * are cropped.
     *
     * @param device Must have the same did and address of the device that is
     * being updated
     * @throws IllegalArgumentException if device is null or there is no device
     * with such id.
     */
    @Override
    public void updateDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        List<Device> listOfDevices = devices.getListOfDevices();

        if (!listOfDevices.contains(device)) {
            throw new IllegalArgumentException("No device with id "
                    + device.getDid() + " found in the list of devices.");
        } else {
            int index = listOfDevices.indexOf(device);
            Device originalDevice = listOfDevices.get(index);

            originalDevice.setNumberOfPorts(device.getNumberOfPorts());
            originalDevice.setName(device.getName());
            originalDevice.setDeviceType(device.getDeviceType());
        }
    }

    /**
     * Gets list of all devices.
     *
     * @return Unmodifiable list of all devices.
     */
    @Override
    public List<Device> listAllDevices() {
        return Collections.unmodifiableList(devices.getListOfDevices());
    }

    /**
     * Gets list of all computers.
     *
     * @return Copy of the list of all computers.
     */
    @Override
    public List<Device> listAllComputers() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.COMPUTER).collect(Collectors.toList());
    }

    /**
     * Gets list of all hubs.
     *
     * @return Copy of the list of all hubs.
     */
    @Override
    public List<Device> listAllHubs() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.HUB).collect(Collectors.toList());
    }

    /**
     * Gets list of all routers.
     *
     * @return Copy of the list of all routers.
     */
    @Override
    public List<Device> listAllRouters() {
        return devices.getListOfDevices().stream().filter(device -> device.getDeviceType() == DeviceType.ROUTER).collect(Collectors.toList());
    }

    /**
     * Gets list of all switches.
     *
     * @return Copy of the list of all switches.
     */
    @Override
    public List<Device> listAllSwitches() {
        return devices.getListOfDevices().stream().filter(device -> device.isSwitch()).collect(Collectors.toList());
    }

    /**
     * Finds device by its ID.
     *
     * @param id Unique identificator.
     * @return Device with given ID. Null if there is no such device.
     */
    @Override
    public Device findDeviceById(Long id) {
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
     *
     * @param adress Unique MAC address.
     * @return Device with given address. Null if there is no such device.
     */
    @Override
    public Device findDeviceByAddress(String adress) {
        Optional<Device> optional = devices.getListOfDevices().stream().filter(device -> device.getAddress().equals(adress)).findFirst();

        if (!optional.isPresent()) {
            return null;
            //throw new IllegalArgumentException("No device with id " + adress + " found.");
        }

        return optional.get();
    }

    /**
     * Finds first empty ethernet port of the device. Returns negative number if
     * there is no empty port.
     *
     * @param device Device.
     * @return Index of the arrayOfPorts (positive or zero number). -1
 if there is no such element.
     * @throws IllegalArgumentException if either device or device's list of
     * port is null.
     */
    @Override
    public int findEmptyPort(Device device) {

        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        List<Port> ethernetPorts = device.getArrayOfPorts();

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
     *
     * @param device Device.
     * @param numberInArrayOfPorts Index of the ethernet port in the array of
     * device ethernet ports.
     * @return True if there is no Port on the specified index; false if the
     * port is already occupied.
     * @throws IllegalArgumentException if device is null.
     */
    @Override
    public boolean isPortEmpty(Device device, int numberInArrayOfPorts) {

        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        return device.getArrayOfPorts().get(numberInArrayOfPorts) == null;
    }
    
    /**
     * Checks duplicity of did attribute of the newly formed device.

     * @param device Device. Must have unique both address and id (did).
     * @return True if there is no other device with the same did,
     * false if any other device has the same did.
     * @throws IllegalArgumentException if device is null.
     */
    private boolean hasValidDid(Device device) {
        
        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        Predicate<Device> similarDid = (n) -> n.getDid().equals(device.getDid());

        return !devices.getListOfDevices().stream().anyMatch(similarDid);
    }

    /**
     * Checks duplicity of address attribute of the newly formed device.

     * @param device Device. Must have unique both address and id (did).
     * @return True if there is no other device with the same address,
     * false if any other device has the same address.
     * @throws IllegalArgumentException if device is null.
     */
    private boolean hasValidAddress(Device device) {

        if (device == null) {
            throw new IllegalArgumentException("Device can't be null");
        }

        Predicate<Device> similarAddress = (n) -> n.getAddress().equals(device.getAddress());

        return !devices.getListOfDevices().stream().anyMatch(similarAddress);
    }
}
