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
    @Path("/savedefined")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doSomethingWithUiInput(postJsonUIinput in) {
	    System.out.println("test");
	    System.out.println("tablename: " + in.getTable());

	    return Response.ok().build();
    }

    public String getTrigger(
            @FormParam("tableName") String tableName,
            @FormParam("attribute") String attribute,
            @FormParam("operator") String operator,
            @FormParam("value1") int value1,
            @FormParam("value2") int value2,
            @FormParam("failureMessage") String failureMessage) {
        return "create or replace TRIGGER generated_name\n" +
                "    BEFORE INSERT ON " + tableName + "\n" +
                "    FOR EACH ROW\n" +
                "DECLARE\n" +
                "    l_passed boolean;\n" +
                "BEGIN\n" +
                "    l_passed := " + attribute + " " + operator + " " + value1 + " and " + value2 + ";\n" +
                "    IF not l_passed \n" +
                "        THEN    \n" +
                "            raise_application_error(-20000, '" + failureMessage + "');\n" +
                "    END IF;\n" +
                "END generated_name;";
    }

}
