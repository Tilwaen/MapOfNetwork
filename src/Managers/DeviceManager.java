/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import Devices.Device;
import java.util.List;

/**
 *
 * @author Petr Beran
 */
public interface DeviceManager {
 
    void createDevice();
 
    void deleteDevice(Device device);
 
    void deleteDeviceById(Long id);
 
    void updateDevice(Device device);
 
    List<Device> listAllDevices();
 
    List<Device> listAllComputers();
 
    List<Device> listAllHubs();
 
    List<Device> listAllRouters();
 
    List<Device> listAllSwitches();
 
    Device findDeviceById();
 
}
