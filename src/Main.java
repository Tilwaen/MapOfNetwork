
import Devices.Router;
import Devices.Router.Builder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Example new Router call.
        // Required attribute 'did' is passed manually in the Builder constructor, 
        // required attribute 'deviceType' is passed in the dedicated Builder itself,
        // optional parameters are passed as chainable methods.
        // Subclass optional properties must be called PRIOR to the abstract Device properties.
        // Will think of a way to bypass this limitation.
        Router router = new Router.Builder(10)
                .someRouterProperty(5365)                       // works, Router property called first
                .address("not-the-address-you-are-looking-for")
                //.someRouterProperty(5365)                     // doesn't work, address (in Device) called prior to that
                .build();
        System.out.println("required Device did: " + router.getDid());
        System.out.println("optional Device address: " + router.getAddress());
        System.out.println("optional Router property: " + router.getSomeRouterProperty());
    }
}
