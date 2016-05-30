package cz.muni.fi.pb138.Main;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Validation.Validator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Required parameters are passed in the constructor,
        // optional parameters are passed as chainable methods.
        // Must end with .build() method.
        Device router = new Device.Builder("id", DeviceType.ROUTER, "address", 4)
                .name("Jogobella")
                .arrayOfEthernetPorts()
                .build();
        
        System.out.println("required Device did: " + router.getDid());
        System.out.println("required Device address: " + router.getAddress());
        System.out.println("optional Device name: " + router.getName());
        System.out.println("optional default array of ports, length: " + router.getArrayOfEthernetPorts().size());
        
        // XML validation
        // Odkomentovat pro validaci. Oba soubory, .xml i .xsd, budou ve slozce, ktera obsahuje slozku src (tj. "vedle" slozky src)
        //Validator validation = new Validator(new String[] {"masterrouter.xml", "masterrouter.xsd"});
    }
}
