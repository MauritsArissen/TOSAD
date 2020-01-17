package define.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import define.persistence.DefineOracleDao;

@Path("/define")
public class DefineResource {
	
	@GET
    @Produces("application/json")
    public Response getBasicInfo() {
		
		System.out.println("Main class works");

        DefineOracleDao dao = new DefineOracleDao();

        String result = dao.getDefineInfo();
        
        ResponseBuilder builder = Response.ok(result);
        
        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        
        return builder.build();
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
