package cz.muni.fi.pb138.Main;

import cz.muni.fi.pb138.Devices.Device;
import java.util.ArrayList;

/**
 * @version 30.5.2016
 * @author Petr Beran
 * @author Kristýna Leknerová
 */
public class ListOfDevices {

    private final ArrayList<Device> listOfDevices = new ArrayList();

    public ArrayList<Device> getListOfDevices() {
        return listOfDevices;
    }
    
    /**
     * Method for exporting listOfDevices to XML.
     */
    public void exportXML() {
        return;
    }

    /**
     * Method for importing XML into listOfDevices.
     */
    public void importXML() {
        return;
    }

}
