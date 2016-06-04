package cz.muni.fi.pb138.Main;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.DeviceType;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.Managers.DeviceManagerImpl;
import cz.muni.fi.pb138.Managers.PortManagerImpl;
import cz.muni.fi.pb138.Validation.Validator;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        // Required parameters are passed in the constructor,
        // optional parameters are passed as chainable methods.
        // Must end with .build() method.
        /*Device router = new Device.Builder(666L, DeviceType.ROUTER, "12:23:45:56:aa:bb", 4)
                .name("Jogobella")
                .build();
        Device computer = new Device.Builder(999L, DeviceType.COMPUTER, "92:23:45:56:aa:bb", 4)
                .name("OhMyGod")
                .build();*/
        
        // Try import and export
        /*ListOfDevices lod = new ListOfDevices();
        
        lod.getListOfDevices().add(router);
        lod.getListOfDevices().add(computer);
        
        lod.exportXML();
        
        try {
            lod.importXML("./resultfile.xml");
        }
        catch (IOException ioe) {
            System.out.println("Unable to import the file");
        }*/
        
        
        // XML validation
        //Validator validation = new Validator(new String[] {"resultfile.xml", "masterrouter.xsd"});
    }
}
