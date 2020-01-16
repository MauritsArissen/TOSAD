package define.resource;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @POST
    @Path("/savedefined")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doSomethingWithUiInput(postJsonUIinput in) {
	    System.out.println("test");
	    System.out.println("tablename: " + in.getTable());

	    return Response.ok().build();
    }

}
