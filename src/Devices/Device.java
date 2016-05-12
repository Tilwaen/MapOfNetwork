/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Devices;

import java.lang.reflect.Constructor;

/**
 * Represents any device in the network.
 * 
 * @author Kristýna Leknerová
 */
public abstract class Device {
    private int did;                // required
    private DeviceType deviceType;  // required
    private String address;         // optional
    private String name;            // optional
    
    /*** Constructor ***/
    protected <T extends Device> Device(DeviceBuilder<T> deviceBuilder) {
        this.did = deviceBuilder.did;
        this.deviceType = deviceBuilder.deviceType;
        this.address = deviceBuilder.address;
        this.name = deviceBuilder.name;
    }
    
    /*** Getters ***/
    public int getDid() {
        return did;
    }
    
    public DeviceType getDeviceType() {
        return deviceType;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getName() {
        return name;
    }
    
    /**
     * Builder for the class Device.
     * Why do we even use this monstrosity? 
     * Because there are two optional string parameters in the abstract method.
     * 
     * More about this specific pattern used is to be found here: 
     * http://bottlenet.blogspot.cz/2012/05/java-builder-pattern-in-inheritance.html 
     * 
     * IMPORTANT: Subclass optional properties must be called PRIOR to the Device optional properties!!!
     * ie. Router.Builder(did).routerProperty(yolo).name("")    works, while
     * Router.Builder(did).name("").routerProperty(yolo)        
     * doesn't work, because router property was called after the Device property.
     */
    public static class DeviceBuilder<T extends Device> {
        private final Class<T> deviceClass;
        
        private final int did;
        private final DeviceType deviceType;
        private String address;
        private String name;
        
        // Required
        protected DeviceBuilder(Class<T> deviceClass, int did, DeviceType deviceType) {
            this.deviceClass = deviceClass;
            this.did = did;
            this.deviceType = deviceType;
        }
        
        // Optional
        public DeviceBuilder<T> address(String address) {
            this.address = address;
            return this;
        }
        
        public DeviceBuilder<T> name(String name) {
            this.name = name;
            return this;
        }
        
        /**
         * Build method for the Builder.
         * @return Instance of the abstract Device subclass
         */
        public T build() {
            try {
                Constructor<T> constructor = null;
                constructor = deviceClass.getDeclaredConstructor(getClass());
                constructor.setAccessible(true);

                return constructor.newInstance(this);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
