/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public static final class Builder extends Device.DeviceBuilder<Router> {
        private int someRouterProperty;
        
        // TODO: Think of better way how to exactly pass the "did" 
        // - and how to generate it in the first place.
        public Builder(int did) {
            super(Router.class, did, DeviceType.ROUTER);
            // Should the "someRouterProperty" not be optional, but required,
            // it's place is here (instead of the method)
        }
        
        public Builder someRouterProperty(int someRouterProperty) {
            this.someRouterProperty = someRouterProperty;
            return this;
        }
    }
}
