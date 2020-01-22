package define.resource;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

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
	@Produces("application/json")
	public Response defineBusinessRule(String data) throws Exception {
	    JSONObject jsondata = new JSONObject(data);
	    System.out.println(jsondata);
	    
	    
	    
	    ResponseBuilder builder = Response.ok();
       
        return builder.build();
	    
	}
	
	

}
