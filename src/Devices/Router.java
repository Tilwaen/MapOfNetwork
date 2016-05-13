package Devices;

/**
 * Represents a router.
 * @author Kristýna Leknerová
 */
public final class Router extends Device {
    // Demonstrative optional parameter.
    private int someRouterProperty;

    private Router(Builder builder) {
        super(builder);
        this.someRouterProperty = builder.someRouterProperty;
    }
    
    /*** Getters ***/
    public int getSomeRouterProperty() {
        return someRouterProperty;
    }
    
    /*** Builder ***/ 
    public static class Builder extends Device.DeviceBuilder<Builder> {
        private int someRouterProperty;
        
        public Builder(int did) {
            super(did, DeviceType.ROUTER);
            // Should the "someRouterProperty" be required,
            // it's place is here (instead of the method)
        }
        
        @Override protected Builder getThis() {
            return this;
        }
        
        public Builder someRouterProperty(int someRouterProperty) {
            this.someRouterProperty = someRouterProperty;
            return this;
        }
        
        public Router build() {
            return new Router(this);
        }
    }
}
