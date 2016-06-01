package cz.muni.fi.pb138.Main;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Devices.PortType;
import cz.muni.fi.pb138.Managers.DeviceManagerImpl;
import cz.muni.fi.pb138.Managers.PortManagerImpl;
import cz.muni.fi.pb138.Validation.Validator;

public class Main {

    public static void main(String[] args) {
        
        // Required parameters are passed in the constructor,
        // optional parameters are passed as chainable methods.
        // Must end with .build() method.
        Device router = new Device.Builder("routerId", DeviceType.ROUTER, "routerAddress", 4)
                .name("Jogobella")
                .build();
        
        System.out.println("Testing Device builder:");
        System.out.println("required Device did: " + router.getDid());
        System.out.println("required Device address: " + router.getAddress());
        System.out.println("optional Device name: " + router.getName());
        System.out.println("optional (default) array of ports, length: " + router.getArrayOfEthernetPorts().size());
        
        System.out.println("Testing List of devices:");
        System.out.println("Adding devices to the list of devices");
        ListOfDevices listOfDevices = new ListOfDevices();
        listOfDevices.getListOfDevices().add(router);
        System.out.println("Added device name: " + listOfDevices.getListOfDevices().get(0).getName());
        
        System.out.println("Testing DeviceManager:");
        DeviceManagerImpl deviceManager = new DeviceManagerImpl(listOfDevices);
        System.out.println("Created device manager; size of all devices list: " + deviceManager.listAllDevices().size());
        System.out.println("Adding computer:");
        Device computer = new Device.Builder("computerId", DeviceType.COMPUTER, "computerAddress", 4).name("Pralinka").build();
        deviceManager.createDevice(computer);
        
        deviceManager.deleteDevice(router);
        System.out.println("Deleted router; size of all devices list: " + deviceManager.listAllDevices().size());
        
        deviceManager.createDevice(router);
        System.out.println("Added router; size of all devices list: " + deviceManager.listAllDevices().size());
        System.out.println("Size of all routers list: " + deviceManager.listAllRouters().size());
        System.out.println("Size of all computers list: " + deviceManager.listAllComputers().size());
        System.out.println("Size of all switches list: " + deviceManager.listAllSwitches().size());
        
        System.out.println("Testing PortManager");
        PortManagerImpl portManager = new PortManagerImpl();
        portManager.createEthernetPort(new Port(PortType.ETHERNET, router, computer));
        System.out.println("Number of used ports for router: " + portManager.listAllEthernetPortsOfDevice(router).size());
        // TODO: This lists both used AND unused ports. Error?
        
        // XML validation
        // Odkomentovat pro validaci. Oba soubory, .xml i .xsd, budou ve slozce, ktera obsahuje slozku src (tj. "vedle" slozky src)
        //Validator validation = new Validator(new String[] {"masterrouter.xml", "masterrouter.xsd"});
    }
}
