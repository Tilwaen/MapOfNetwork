/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Devices;

/**
 * Port.
 * 
 * @param type      Port type, either ethernet or wifi.
 * @param device1   First of the connected devices.
 * @param device2   Second of the connected device.
 * 
 * @author Kristýna Leknerová
 */
public class Port {
    protected PortType type;
    protected Device device1;
    protected Device device2;
    
    public Port(PortType portType, Device device1, Device device2) {
        this.type = portType;
        this.device1 = device1;
        this.device2 = device2;
    }
}
