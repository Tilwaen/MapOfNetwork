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
 * 
 * Tests for PortManager class
 * 
 * @version 2.6.2016
 * @author Petr Beran
 */
public class PortManagerImplTest {

    private PortManager portManager;
    private Device testDeviceA;
    private Device testDeviceB;

    public PortManagerImplTest() {
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        this.portManager = new PortManagerImpl();
        this.testDeviceA = new Device.Builder(1L, DeviceType.COMPUTER, "address", 2)
                .name("Kačer Donald")
                .build();
        this.testDeviceB = new Device.Builder(2L, DeviceType.ROUTER, "address", 4)
                .name("Hamoun")
                .build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createPort() {
        Port port = new Port(testDeviceA, testDeviceB);
        portManager.createPort(port);
        assertEquals(true, testDeviceA.getArrayOfPorts().contains(port));
        assertEquals(true, testDeviceB.getArrayOfPorts().contains(port));
    }

    @Test
    public void createNullPort() {
        Port port = null;
        exception.expect(IllegalArgumentException.class);
        portManager.createPort(port);
    }

    @Test
    public void createPortWithNullDevice() {
        Port port = new Port(null, testDeviceB);
        exception.expect(IllegalArgumentException.class);
        portManager.createPort(port);
    }

    @Test
    public void createPortWithSameDevice() {
        Port port = new Port(testDeviceA, testDeviceA);
        exception.expect(IllegalArgumentException.class);
        portManager.createPort(port);
    }

    @Test
    public void createPortForDeviceWithOccupiedPortSlots() {
        Device testDeviceC = new Device.Builder(666L, DeviceType.ROUTER, "address", 4)
                .name("Kulík")
                .build();
        Device testDeviceD = new Device.Builder(42L, DeviceType.ROUTER, "address", 4)
                .name("Dulík")
                .build();
        Port portAB = new Port(testDeviceA, testDeviceB);
        Port portAC = new Port(testDeviceA, testDeviceC);
        Port portAD = new Port(testDeviceA, testDeviceD);
        portManager.createPort(portAB);
        portManager.createPort(portAC);
        exception.expect(IndexOutOfBoundsException.class);
        portManager.createPort(portAD);
    }

    @Test
    public void createPortForDeviceWithNoPortSlots() {
        Device testDeviceC = new Device.Builder(666L, DeviceType.ROUTER, "address", 0)
                .name("Bubík")
                .build();
        Port portAC = new Port(testDeviceA, testDeviceC);
        exception.expect(IndexOutOfBoundsException.class);
        portManager.createPort(portAC);
    }

    @Test
    public void createDoublePortForTheSameDevices() {
        Port port = new Port(testDeviceA, testDeviceB);
        portManager.createPort(port);
        exception.expect(IllegalArgumentException.class);
        portManager.createPort(port);
    }

    @Test
    public void createDoublePortForTheSameRevertedDevices() {
        Port port = new Port(testDeviceA, testDeviceB);
        portManager.createPort(port);
        Port revertedPort = new Port(testDeviceB, testDeviceA);
        exception.expect(IllegalArgumentException.class);
        portManager.createPort(revertedPort);
    }

    @Test
    public void createPortFromXML() {
        assertNull(testDeviceA.getArrayOfPorts().get(0));
        assertNull(testDeviceB.getArrayOfPorts().get(0));
        Port port = new Port(testDeviceA, testDeviceB);
        portManager.createPortFromXML(port, 0, 0);
        assertEquals(true, testDeviceA.getArrayOfPorts().contains(port));
        assertEquals(true, testDeviceB.getArrayOfPorts().contains(port));
        assertEquals(port, testDeviceA.getArrayOfPorts().get(0));
        assertEquals(port, testDeviceB.getArrayOfPorts().get(0));
    }

    @Test
    public void createNullPortFromXML() {
        Port port = null;
        exception.expect(IllegalArgumentException.class);
        portManager.createPortFromXML(port, 0, 0);
    }

    @Test
    public void createPortFromXMLWithNegativeNumber() {
        Port port = new Port(testDeviceA, testDeviceB);
        exception.expect(IllegalArgumentException.class);
        portManager.createPortFromXML(port, -1, 0);
    }

    @Test
    public void createPortFromXMLForNonexistentDevice() {
        Port port = new Port(null, testDeviceB);
        exception.expect(IllegalArgumentException.class);
        portManager.createPortFromXML(port, 0, 0);
    }

    @Test
    public void createPortFromXMLForOccupiedPort() {
        Device testDeviceC = new Device.Builder(666L, DeviceType.ROUTER, "address", 4)
                .name("Strýček Skrblík")
                .build();

        Port portAB = new Port(testDeviceA, testDeviceB);
        Port portAC = new Port(testDeviceA, testDeviceC);
        portManager.createPortFromXML(portAB, 0, 0);
        exception.expect(IllegalArgumentException.class);
        portManager.createPortFromXML(portAC, 0, 0);
    }

    @Test
    public void createPortFromXMLForDeviceWithZeroPorts() {
        Device testDeviceC = new Device.Builder(666L, DeviceType.ROUTER, "address", 0)
                .name("Daisy")
                .build();
        Port portAC = new Port(testDeviceA, testDeviceC);
        exception.expect(IndexOutOfBoundsException.class);
        portManager.createPortFromXML(portAC, 0, 0);
    }

    @Test
    public void createPortFromXMLWithPositionOutOfIndex() {
        Port port = new Port(testDeviceA, testDeviceB);
        exception.expect(IndexOutOfBoundsException.class);
        portManager.createPortFromXML(port, 3, 0);
    }

    @Test
    public void deletePort() {
        Device testDeviceC = new Device.Builder(666L, DeviceType.ROUTER, "address", 1)
                .name("Magika von Čáry")
                .build();
        Port portAB = new Port(testDeviceA, testDeviceB);
        Port portAC = new Port(testDeviceA, testDeviceC);
        portManager.createPort(portAB);
        portManager.createPort(portAC);

        List<Port> listOfDeviceAPorts = testDeviceA.getArrayOfPorts();
        List<Port> listOfDeviceBPorts = testDeviceB.getArrayOfPorts();
        List<Port> listOfDeviceCPorts = testDeviceC.getArrayOfPorts();

        portManager.deletePort(listOfDeviceAPorts.get(0));
        assertEquals(true, listOfDeviceAPorts.contains(portAC));
        assertEquals(true, listOfDeviceCPorts.contains(portAC));
        assertEquals(false, listOfDeviceAPorts.contains(portAB));
        assertEquals(false, listOfDeviceBPorts.contains(portAB));
        assertNull(listOfDeviceAPorts.get(0));
        assertNull(listOfDeviceBPorts.get(0));
    }

    @Test
    public void deleteNullPort() {
        exception.expect(IllegalArgumentException.class);
        portManager.deletePort(null);
    }

    @Test
    public void deleteNullPortInList() {
        exception.expect(IllegalArgumentException.class);
        portManager.deletePort(testDeviceA.getArrayOfPorts().get(0));
    }

    @Test
    public void testListAllPortsOfDevice() {
        assertNotNull(testDeviceA.getArrayOfPorts());
        assertEquals(false, testDeviceA.getArrayOfPorts().isEmpty());
        assertNull(testDeviceA.getArrayOfPorts().get(0));
        assertNull(testDeviceA.getArrayOfPorts().get(1));

        Port port = new Port(testDeviceA, testDeviceB);
        portManager.createPort(port);

        assertEquals(true, testDeviceA.getArrayOfPorts().contains(port));
        assertNull(testDeviceA.getArrayOfPorts().get(1));

        List<Port> testList = portManager.listAllPortsOfDevice(testDeviceA);
        assertEquals(true, testList.size() == 1);
        assertEquals(true, testList.contains(port));

    }

}
