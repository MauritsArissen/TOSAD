package define.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import define.persistence.DefineOracleDao;
import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/define")
public class DefineResource {
	
	@GET
    @Produces("application/json")
    public String getBasicInfo() {
		
		System.out.println("Main class works");

        DefineOracleDao dao = new DefineOracleDao();

        String result = dao.getDefineInfo();
        
        return result;
//        try {
//        	
//        	DefineOracleDao dao = new DefineOracleDao();
//        	String result = dao.getDefineInfo();
//        	
//        	System.out.println(result.getClass().getName());
//        	System.out.println("de define resource");
//        	System.out.println(result);
//        	
//            return result;
//        } catch (Exception e) {
//            return "Failed";
//        }
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
