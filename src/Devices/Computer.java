package Devices;

/**
 * Represents a computer. Two computers can be linked together.
 * 
 * @author Kristýna Leknerová
 */
public class Computer extends Device {

    private Computer(Builder builder) {
        super(builder);
    }
    
    /*** Builder ***/ 
    public static class Builder extends Device.DeviceBuilder<Builder> {
        public Builder(String did, String address) {
            super(did, address, DeviceType.COMPUTER);
        }
        
        @Override protected Builder getThis() {
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}
