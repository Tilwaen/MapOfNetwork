package Devices;

/**
 * Represents a Hub.
 * 
 * @author Kristýna Leknerová
 */
public class Hub extends Device {
        
    private Hub(Builder builder) {
            super(builder);
        }
        
        /*** Builder ***/ 
        public static class Builder extends Device.DeviceBuilder<Builder> {
            public Builder(String did, String address) {
                super(did, address, DeviceType.HUB);
            }
        
            @Override protected Builder getThis() {
                return this;
            }
        
            public Hub build() {
                return new Hub(this);
            }
        }
}
