
import Devices.Router;
import Devices.Router.Builder;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        // Example new Router call.
        // Required attribute 'did' is passed manually in the Builder constructor, 
        // required attribute 'deviceType' is passed in the dedicated Builder itself,
        // optional parameters are passed as chainable methods.

        Router router = new Router.Builder(10)
                .address("not-the-address-you-are-looking-for")
                .someRouterProperty(3)
                .build();
        
        System.out.println("required Device did: " + router.getDid());
        System.out.println("optional Device address: " + router.getAddress());
        System.out.println("optional Router property: " + router.getSomeRouterProperty());
    }
}
