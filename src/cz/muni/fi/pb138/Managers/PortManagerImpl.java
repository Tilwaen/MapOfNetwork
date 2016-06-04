package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.gui.Main;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implementing operations with ports. Update port is not supported.
 * Delete port and create new one instead.
 *
 * @version 1.6.2016
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public class PortManagerImpl implements PortManager {

    private DeviceManager deviceManager = Main.getDeviceManager();

    /**
     * Creates new port. Port will be inserted into first available position in
     * devices's ports arrays. Don't use this for creating port from imported
     * XML file.
     *
     * @param port Builded port to assign.
     * @throws IndexOutOfBoundsException if there is no empty port in either
     * device.
     */
    @Override
    public void createPort(Port port) {
        validatePort(port);
        Device deviceA = port.getDeviceA();
        Device deviceB = port.getDeviceB();

        for (Port portInList : deviceA.getArrayOfPorts()) {
            if (portInList != null && ((portInList.getDeviceA().equals(deviceA) && portInList.getDeviceB().equals(deviceB))
                    || (portInList.getDeviceA().equals(deviceB) && portInList.getDeviceB().equals(deviceA)))) {
                throw new IllegalArgumentException("There is already port between these two devices");
            }
        }

        try {
            List<Port> deviceAEthernetPorts = deviceA.getArrayOfPorts();
            deviceAEthernetPorts.set(deviceManager.findEmptyPort(deviceA), port);

            List<Port> deviceBEthernetPorts = deviceB.getArrayOfPorts();
            deviceBEthernetPorts.set(deviceManager.findEmptyPort(deviceB), port);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Creates new port from imported XML file. Don't use for any other purpose.
     * Created port will be inserted into specific place in devices' ports
     * arrays.
     *
     * @param port Builded port to assign.
     * @param numberOfPortInDeviceA Position of port in first device's ports
     * array
     * @param numberOfPortInDeviceB Position of port in second device's ports
     * array
     * @throws IllegalArgumentException if either number is negative.
     * @throws IndexOutOfBoundsException if either number is greater/smaller
     * than devices' ports array.
     */
    @Override
    public void createPortFromXML(Port port, int numberOfPortInDeviceA, int numberOfPortInDeviceB) {
        if (numberOfPortInDeviceA < 0 || numberOfPortInDeviceB < 0) {
            throw new IllegalArgumentException("Number of port in device cannot be negative!");
        }
        validatePort(port);
        Device deviceA = port.getDeviceA();
        Device deviceB = port.getDeviceB();
        for (Port portInList : deviceA.getArrayOfPorts()) {
            if (portInList != null && ((portInList.getDeviceA().equals(deviceA) && portInList.getDeviceB().equals(deviceB))
                    || (portInList.getDeviceA().equals(deviceB) && portInList.getDeviceB().equals(deviceA)))) {
                throw new IllegalArgumentException("There is already port between these two devices");
            }
        }
        if (!deviceManager.isPortEmpty(deviceA, numberOfPortInDeviceA)) {
            throw new IllegalArgumentException("Port in first device is occupied");
        }
        if (!deviceManager.isPortEmpty(deviceB, numberOfPortInDeviceA)) {
            throw new IllegalArgumentException("Port in second device is occupied");
        }
        try {
            List<Port> deviceAEthernetPorts = deviceA.getArrayOfPorts();
            deviceAEthernetPorts.set(numberOfPortInDeviceA, port);

            List<Port> deviceBEthernetPorts = deviceB.getArrayOfPorts();
            deviceBEthernetPorts.set(numberOfPortInDeviceB, port);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Deletes selected port. In devices' lists of ports is port index set to
     * null.
     *
     * @param port Port to delete
     * @throws IllegalArgumentException if port is null
     */
    @Override
    public void deletePort(Port port) {
        if (port == null) {
            throw new IllegalArgumentException("Port is null!");
        }
        validatePort(port);
        Device deviceA = port.getDeviceA();
        Device deviceB = port.getDeviceB();
        deviceA.getArrayOfPorts().set(deviceA.getArrayOfPorts().indexOf(port), null);
        deviceB.getArrayOfPorts().set(deviceB.getArrayOfPorts().indexOf(port), null);
        port = null;
    }

    /**
     * Lists all ports of selected device that are occupied.
     *
     * @param device Device which ports are listed
     * @return List of ports of selected device
     */
    @Override
    public List<Port> listAllPortsOfDevice(Device device) {
        List<Port> listOfPorts = new ArrayList<>();
        device.getArrayOfPorts().stream().filter((port) -> (port != null)).forEach((port) -> {
            listOfPorts.add(port);
        });
        return listOfPorts;
    }

    /**
     * Validates if selected port is not null and has both deviced set. Throws
     * IllegalArgumentException otherwise. Also checks if the devices are the
     * same.
     *
     * @param port Port to validate
     * @throws IllegalArgumentException if port and/or devices are null
     */
    public void validatePort(Port port) {
        if (port == null) {
            throw new IllegalArgumentException("Port is null!");
        }
        if (port.getDeviceA() == null) {
            throw new IllegalArgumentException("DeviceA is null!");
        }
        if (port.getDeviceB() == null) {
            throw new IllegalArgumentException("DeviceB is null!");
        }
        if (port.getDeviceA().equals(port.getDeviceB())) {
            throw new IllegalArgumentException("Devices are the same!");
        }
    }

}
