package define.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import define.persistence.DefineOracleDao;

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

//    @POST
//    @Path("/savedefined")
//    public Response

}
