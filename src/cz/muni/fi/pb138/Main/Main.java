package cz.muni.fi.pb138.Main;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Managers.DeviceManagerImpl;
import cz.muni.fi.pb138.Managers.PortManagerImpl;
import cz.muni.fi.pb138.Validation.Validator;

public class Main {

    public static void main(String[] args) {
        
        // Required parameters are passed in the constructor,
        // optional parameters are passed as chainable methods.
        // Must end with .build() method.
        /*Device router = new Device.Builder("routerId", DeviceType.ROUTER, "routerAddress", 4)
                .name("Jogobella")
                .build();*/
        
        
        // XML validation
        // Odkomentovat pro validaci. Oba soubory, .xml i .xsd, budou ve slozce, ktera obsahuje slozku src (tj. "vedle" slozky src)
        //Validator validation = new Validator(new String[] {"masterrouter.xml", "masterrouter.xsd"});
    }
}
