package define.resource;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/define")
public class DefineResource {
	
	@GET
    @Produces("application/json")
    public Response getBasicInfo() {
        System.out.println("test");
        try {
            String output = "Hello world!";
            System.out.println(output);
            return Response.ok().build();
        } catch (Exception e) {
            System.out.println("test2");
            return null;
        }
    }

//    @POST
//    @Path("/savedefined")
//    public Response

}
