package define.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/define")
public class DefineResource {

    @GET
    @Produces("application/json")
    public String getBasicInfo() {
        try {
            String output = "Hello world!";
            return output;
        } catch (Exception e) {
            return null;
        }
    }

}
