package define.resource;

import com.sun.research.ws.wadl.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/define")
public class DefineResource {

    @GET
    @Produces("application/json")
    public Response getBasicInfo() {



    }



}
