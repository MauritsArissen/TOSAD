package define.resource;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import define.business.controller.DefineController;

@Path("/define")
public class DefineResource {
	private DefineController controller = new DefineController();
	
	@GET
    @Produces("application/json")
    public Response getBasicInfo() {

		HashMap<String, HashMap> hashedresult = controller.getDefineData();
        ResponseBuilder builder = Response.ok(hashedresult);
        
        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        
        return builder.build();
    }
	
	@POST
	@Path("/saverule")
	public Response defineBusinessRule(String data) throws Exception {  
		
		String response = controller.saveDefineData(data);  
		HashMap<String, String> result = new HashMap();
		result.put("response", response);
		
	    ResponseBuilder builder = Response.ok(result);
       
	    builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        
        return builder.build();    
	}
	
	

}
