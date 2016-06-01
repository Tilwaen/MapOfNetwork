package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for implementation DeviceManagerImpl of an DeviceManager interface
 * 
 * @author Kristýna Leknerová
 * @version 1.6.2016
 */
public class DeviceManagerImplTest {
    
    private DeviceManager manager;
    private PortManager portManager;
    
    @Before
    public void setUp() {
        manager = new DeviceManagerImpl();
        portManager = new PortManagerImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDevice method, of class DeviceManagerImpl.
     */
    @Test
    public void createDevice() {
        Device device = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device);
        
        Device createdDevice = manager.findDeviceById("did");
        assertEquals(device, createdDevice);
    }
    
    /**
     * Test of createDevice method, multiple same devices, of class DeviceManagerImpl.
     */
    @Test
    public void createDeviceMultipleSame() {
        Device computer = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        
        Device computer2 = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Fidorka")
                .build();
        manager.createDevice(computer2);
        
        int allDevicesSize = manager.listAllDevices().size();
        assertEquals(allDevicesSize, 1);
    }

    /**
     * Test of deleteDevice method, of class DeviceManagerImpl.
     */
    @Test
    public void deleteDevice() {
        Device computer = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        manager.deleteDevice(computer);
        
        int allDevicesSize = manager.listAllDevices().size();
        assertEquals(allDevicesSize, 0);
    }

    /**
     * Test of updateDevice method, of class DeviceManagerImpl.
     */
    @Test
    public void updateDevice() {
        Device device = new Device.Builder("did", DeviceType.COMPUTER, "address", 4)
                .name("Jogobella")
                .build();
        manager.createDevice(device);
        
        Device newDevice = manager.findDeviceById("did");
        newDevice.setDeviceType(DeviceType.ROUTER);
        newDevice.setName("Trautenberk");
        
        manager.updateDevice(newDevice);
        assertEquals(device, newDevice);
        assertEquals(device.getDid(), newDevice.getDid());
        assertNotEquals(DeviceType.COMPUTER, newDevice.getDeviceType());
        assertNotEquals("Jogobella", newDevice.getName());
    }

    /**
     * Test of listAllDevices method, of class DeviceManagerImpl.
     */
    @Test
    public void listAllDevices() {
        Device computer = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        
        Device router = new Device.Builder("did2", DeviceType.ROUTER, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(router);
        
        Device switch12 = new Device.Builder("did3", DeviceType.SWITCH12, "address3", 0)
                .name("Pls")
                .build();
        manager.createDevice(switch12);
        
        List<Device> allDevices = manager.listAllDevices();
        assertEquals(allDevices.size(), 3);
        assertEquals(allDevices.contains(computer), true);
        assertEquals(allDevices.contains(router), true);
        assertEquals(allDevices.contains(switch12), true);
    }

    /**
     * Test of listAllComputers method, of class DeviceManagerImpl.
     */
    @Test
    public void listAllComputers() {
        Device device1 = new Device.Builder("did", DeviceType.COMPUTER, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.ROUTER, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder("did3", DeviceType.COMPUTER, "address3", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allComputers = manager.listAllComputers();
        assertEquals(allComputers.size(), 2);
    }

    /**
     * Test of listAllHubs method, of class DeviceManagerImpl.
     */
    @Test
    public void listAllHubs() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.HUB, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder("did3", DeviceType.COMPUTER, "address3", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allHubs = manager.listAllHubs();
        assertEquals(allHubs.size(), 2);
    }

    /**
     * Test of listAllRouters method, of class DeviceManagerImpl.
     */
    @Test
    public void listAllRouters() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.ROUTER, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder("did3", DeviceType.ROUTER, "address3", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allRouters = manager.listAllRouters();
        assertEquals(allRouters.size(), 2);
    }

    /**
     * Test of listAllSwitches method, of class DeviceManagerImpl.
     */
    @Test
    public void listAllSwitches() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder("did3", DeviceType.SWITCH24, "address3", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        Device device4 = new Device.Builder("did4", DeviceType.SWITCH48, "address4", 0)
                .build();
        manager.createDevice(device4);
        
        List<Device> allSwitches = manager.listAllSwitches();
        assertEquals(allSwitches.size(), 3);
    }

    /**
     * Test of findDeviceById method, of class DeviceManagerImpl.
     */
    @Test
    public void findDeviceById() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device foundDevice = manager.findDeviceById("did");
        assertEquals(foundDevice, device1);
    }

    /**
     * Test of findDeviceByAddress method, of class DeviceManagerImpl.
     */
    @Test
    public void findDeviceByAddress() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device foundDevice = manager.findDeviceByAddress("address");
        assertEquals(foundDevice, device1);
    }

    /**
     * Test of findEmptyPort method, of class DeviceManagerImpl.
     */
    @Test
    public void findEmptyPort() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 4)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 2)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        int indexOfEmptyPort1 = manager.findEmptyPort(device1);
        int indexOfEmptyPort2 = manager.findEmptyPort(device2);
        
        assertEquals(indexOfEmptyPort1, 1);
        assertEquals(indexOfEmptyPort2, 1);
    }
    
    /**
     * Test of findEmptyPort method, of class DeviceManagerImpl.
     */
    @Test
    public void findEmptyPortNothingEmpty() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 1)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 2)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        int indexOfEmptyPort1 = manager.findEmptyPort(device1);
        int indexOfEmptyPort2 = manager.findEmptyPort(device2);
        
        assertEquals(indexOfEmptyPort1, -1);
        assertEquals(indexOfEmptyPort2, 1);
    }

    /**
     * Test of isPortEmpty method, of class DeviceManagerImpl.
     */
    @Test
    public void isPortEmpty() {
        Device device1 = new Device.Builder("did", DeviceType.HUB, "address", 2).build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder("did2", DeviceType.SWITCH12, "address2", 2).build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        boolean occupiedPort = manager.isPortEmpty(device1, 0);
        boolean emptyPort = manager.isPortEmpty(device1, 1);
        
        assertEquals(occupiedPort, false);
        assertEquals(emptyPort, true);
    }
    
}
