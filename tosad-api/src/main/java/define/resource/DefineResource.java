package define.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/define")
public class DefineResource {

    @GET
    @Produces("application/json")
    public String getBasicInfo() {
        System.out.println("test");
        try {
            String output = "Hello world!";
            System.out.println(output);
            return output;
        } catch (Exception e) {
            System.out.println("test2");
            return null;
        }
    }

}
