/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Devices;

/**
 * Represents a switch.
 * Switch can have 12, 24 or 48 ports.
 * 
 * @param type Required, switch type
 * 
 * @author Kristýna Leknerová
 */
public class Switch extends Device {
    
    private SwitchType type;
    
    private Switch(Builder builder) {
        super(builder);
        this.type = builder.type;
    }
    
    /*** Builder ***/ 
    public static class Builder extends Device.DeviceBuilder<Builder> {
        private SwitchType type;
        
        public Builder(String did, String address, SwitchType type) {
            this(did, address, chooseDeviceType(type));
            this.type = type;
        }
        
        // Workaround the "superconstructor must be called first" 
        // when we don't know the correct deviceType yet
        private Builder(String did, String address, DeviceType deviceType) {
            super(did, address, deviceType);
        }
        
        /**
         * Chooses correct deviceType based on the switchType.
         * Needed for the super constructor call workaround.
         * This method must be static.
         * @param type switch type
         * @return Device type based on the switch type
         */
        private static DeviceType chooseDeviceType(SwitchType type) {
            switch (type) {
                case SWITCH12: 
                    return DeviceType.SWITCH12;
                case SWITCH24:
                    return DeviceType.SWITCH24;
                case SWITCH48:
                    return DeviceType.SWITCH48;
                // TODO: Create custom exception
                default: throw new IllegalArgumentException("Unsupported switch type");
            }
        }
        
        @Override protected Builder getThis() {
            return this;
        }
        
        public Switch build() {
            return new Switch(this);
        }
    }
}
