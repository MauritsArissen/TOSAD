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
