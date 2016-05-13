
import Devices.Router;
import Devices.Router.Builder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Example new Router call.
        // Required attributes 'did' and "address' are passed manually in the Builder constructor, 
        // required attribute 'deviceType' is passed inside the dedicated Builder itself 
        // (when calling the super constructor),
        // optional parameters are passed as chainable methods.

        Router router = new Router.Builder("Super megacool router", "not-the-address-you-are-looking-for")
                .name("oh noes")
                .someRouterProperty(42)
                .build();
        
        System.out.println("required Device did: " + router.getDid());
        System.out.println("required Device address: " + router.getAddress());
        System.out.println("optional Device name: " + router.getName());
        System.out.println("optional Router property: " + router.getSomeRouterProperty());
    }
}
