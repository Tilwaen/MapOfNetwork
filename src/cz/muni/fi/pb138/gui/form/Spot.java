/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.gui.form;

import cz.muni.fi.pb138.Devices.Device;
import cz.muni.fi.pb138.Devices.Port;
import cz.muni.fi.pb138.gui.Main;
import cz.muni.fi.pb138.gui.form.constants.GUIConstants;
import cz.muni.fi.pb138.gui.form.utils.LineDrawer;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Mags
 */
public class Spot {

    private int number;
    private JLabel label;
    private Device device;

    public Spot(int number, JLabel label, Device device) {
        this.number = number;
        this.label = label;
        this.device = device;
    }
    
    public void clearDevice() {
               
        //clear ports if present
        LineDrawer linker = Main.getLinker();
        Set<Port> toRemove = new HashSet<>();
        
        for (Port port : linker.getOpenPorts()) {
            if (port.getDeviceA().getAddress().equals(device.getAddress()) ||
                    port.getDeviceB().getAddress().equals(device.getAddress())) {
                toRemove.add(port);
            }
        }
        
        for (Port port: toRemove) {
            linker.unlink(port);
        }
        
        this.device = null;
        this.label.setIcon(null);
        this.label.setText("Spot " + number);
    }
    
    public void updateSpot(Device device) {
        
        setDevice(device);
        getLabel().setText("");
        
        Icon image = null;
        String deviceTypeString = device.getDeviceType().toString().toUpperCase();
        switch(deviceTypeString) {
            case "COMPUTER" : 
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.computerPath));
                break;
            case "HUB" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.hubPath));
                break;
            case "MODEM" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.modemPath));
                break;
            case "ROUTER" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.routerPath));
                break;
            case "SWITCH12" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.switch12Path));
                break;
            case "SWITCH24" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.switch24Path));
                break;
            case "SWITCH48" :
                image = new ImageIcon(getClass().getClassLoader().getResource(GUIConstants.switch48Path));
                break;
        }
        getLabel().setIcon(image);
        getLabel().setHorizontalTextPosition( SwingConstants.CENTER );
        getLabel().setToolTipText( device.getName() + ", number of ports: " + 
                device.getNumberOfPorts() + ", address: " + device.getAddress() );
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
