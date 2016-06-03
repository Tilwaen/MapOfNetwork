package cz.muni.fi.pb138.Managers;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Test for implementation DeviceManagerImpl of an DeviceManager interface
 * 
 * @author Kristýna Leknerová
 * @version 2.6.2016
 */
public class DeviceManagerImplTest {
    
    private DeviceManager manager;
    private PortManager portManager;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setUp() {
        manager = new DeviceManagerImpl();
        portManager = new PortManagerImpl();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createDevice() {
        Device device = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device);
        
        Device createdDevice = manager.findDeviceById(10L);
        assertEquals(device, createdDevice);
    }
    
    @Test
    public void createDeviceMultipleSame() {
        Device computer = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        
        Device computer2 = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Fidorka")
                .build();
        manager.createDevice(computer2);
        
        int allDevicesSize = manager.listAllDevices().size();
        assertEquals(allDevicesSize, 1);
    }
    
    @Test
    public void createDeviceWithoutDidCorrect() {
        manager.createDevice(DeviceType.HUB, "01:02:03:04:ab:cd", 0, null, null);
        
        Device foundDevice = manager.findDeviceByAddress("01:02:03:04:ab:cd");
        assertNotNull(foundDevice);
        assertNotNull(foundDevice.getDid());
        System.out.println("Randomly generated id: " + foundDevice.getDid());
    }
    
    @Test
    public void createDeviceWithoutDidAddressNull() {
        exception.expect(IllegalArgumentException.class);
        manager.createDevice(DeviceType.HUB, null, 0, null, null);
    }

    @Test
    public void deleteDevice() {
        Device computer = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        manager.deleteDevice(computer);
        
        int allDevicesSize = manager.listAllDevices().size();
        assertEquals(allDevicesSize, 0);
    }
    
    @Test
    public void deleteDeviceNull() {
        exception.expect(IllegalArgumentException.class);
        manager.deleteDevice(null);
    }
    
    @Test
    public void deleteDeviceNotPresent() {
        Device device = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        exception.expect(IllegalArgumentException.class);
        manager.deleteDevice(device);
    }

    @Test
    public void updateDevice() {
        Device device = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 4)
                .name("Jogobella")
                .build();
        manager.createDevice(device);
        
        Device newDevice = manager.findDeviceById(10L);
        newDevice.setDeviceType(DeviceType.ROUTER);
        newDevice.setName("Trautenberk");
        
        manager.updateDevice(newDevice);
        assertEquals(device, newDevice);
        assertEquals(device.getDid(), newDevice.getDid());
        assertNotEquals(DeviceType.COMPUTER, newDevice.getDeviceType());
        assertNotEquals("Jogobella", newDevice.getName());
    }

    @Test
    public void listAllDevices() {
        Device computer = new Device.Builder(10L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(computer);
        
        Device router = new Device.Builder(666L, DeviceType.ROUTER, "22:22:22:22:22:22", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(router);
        
        Device switch12 = new Device.Builder(42L, DeviceType.SWITCH12, "33:33:33:33:33:33", 0)
                .name("Pls")
                .build();
        manager.createDevice(switch12);
        
        List<Device> allDevices = manager.listAllDevices();
        assertEquals(allDevices.size(), 3);
        assertEquals(allDevices.contains(computer), true);
        assertEquals(allDevices.contains(router), true);
        assertEquals(allDevices.contains(switch12), true);
    }

    @Test
    public void listAllComputers() {
        Device device1 = new Device.Builder(666L, DeviceType.COMPUTER, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.ROUTER, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder(69L, DeviceType.COMPUTER, "21:02:03:04:ab:cd", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allComputers = manager.listAllComputers();
        assertEquals(allComputers.size(), 2);
    }

    @Test
    public void listAllHubs() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.HUB, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder(69L, DeviceType.COMPUTER, "21:02:03:04:ab:cd", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allHubs = manager.listAllHubs();
        assertEquals(allHubs.size(), 2);
    }

    @Test
    public void listAllRouters() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.ROUTER, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder(69L, DeviceType.ROUTER, "21:02:03:04:ab:cd", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        List<Device> allRouters = manager.listAllRouters();
        assertEquals(allRouters.size(), 2);
    }

    @Test
    public void listAllSwitches() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device device3 = new Device.Builder(69L, DeviceType.SWITCH24, "21:02:03:04:ab:cd", 0)
                .name("Pls")
                .build();
        manager.createDevice(device3);
        
        Device device4 = new Device.Builder(1L, DeviceType.SWITCH48, "31:02:03:04:ab:cd", 0)
                .build();
        manager.createDevice(device4);
        
        List<Device> allSwitches = manager.listAllSwitches();
        assertEquals(allSwitches.size(), 3);
    }

    @Test
    public void findDeviceById() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device foundDevice = manager.findDeviceById(666L);
        assertEquals(foundDevice, device1);
    }
    
    @Test
    public void findDeviceByIdNotPresent() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device foundDevice = manager.findDeviceById(42L);
        assertNull(foundDevice);
    }

    @Test
    public void findDeviceByAddress() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 0)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        Device foundDevice = manager.findDeviceByAddress("01:02:03:04:ab:cd");
        assertEquals(foundDevice, device1);
    }
    
    @Test
    public void findDeviceByAddressNotPresent() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 0)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device foundDevice = manager.findDeviceByAddress("11:02:03:04:ab:cd");
        assertNull(foundDevice);
    }

    @Test
    public void findEmptyPort() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 4)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 2)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        int indexOfEmptyPort1 = manager.findEmptyPort(device1);
        int indexOfEmptyPort2 = manager.findEmptyPort(device2);
        
        assertEquals(indexOfEmptyPort1, 1);
        assertEquals(indexOfEmptyPort2, 1);
    }
    
    @Test
    public void findEmptyPortNothingEmpty() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 1)
                .name("Jogobella")
                .build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 2)
                .name("Ovoce")
                .build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        int indexOfEmptyPort1 = manager.findEmptyPort(device1);
        int indexOfEmptyPort2 = manager.findEmptyPort(device2);
        
        assertEquals(indexOfEmptyPort1, -1);
        assertEquals(indexOfEmptyPort2, 1);
    }

    @Test
    public void isPortEmpty() {
        Device device1 = new Device.Builder(666L, DeviceType.HUB, "01:02:03:04:ab:cd", 2).build();
        manager.createDevice(device1);
        
        Device device2 = new Device.Builder(42L, DeviceType.SWITCH12, "11:02:03:04:ab:cd", 2).build();
        manager.createDevice(device2);
        
        portManager.createPort(new Port(device1, device2));
        boolean occupiedPort = manager.isPortEmpty(device1, 0);
        boolean emptyPort = manager.isPortEmpty(device1, 1);
        
        assertEquals(occupiedPort, false);
        assertEquals(emptyPort, true);
    }
}
