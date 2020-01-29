package generate.resource;

import generate.business.controller.GenerateController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/generate")
public class GenerateResource {
    GenerateController controller = new GenerateController();

    @POST
    @Produces("application/json")
    public Response getTriggerInfo(String data) {

        ArrayList response = controller.returnTriggers(data);
        Response.ResponseBuilder builder = Response.ok(response);

        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        return builder.build();
    }

    @POST
    @Path("/getbusinessrules")
    @Produces("application/json")
    public Response getBusinessrulesByTrigger(String data) {
        ArrayList response = controller.returnRulesByTrigger(data);
        Response.ResponseBuilder builder = Response.ok(response);

        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        return builder.build();
    }

    @POST
    @Path("/generateTriggerCode")
    @Produces("application/json")
    public Response generateTriggerCode(String data) {
        ArrayList response = controller.generateTriggerCode(data);
        Response.ResponseBuilder builder = Response.ok(response);

        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        return builder.build();
    }

    @POST
    @Path("/generateTrigger")
    @Produces("application/json")
    public Response generateTrigger(String data) {
        ArrayList response = controller.generateTrigger(data);
        Response.ResponseBuilder builder = Response.ok(response);

        builder.header("Access-Control-Allow-Origin", "*");
        builder.header("Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        builder.header("Access-Control-Allow-Credentials", "true");
        builder.header("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        return builder.build();
    }
}
