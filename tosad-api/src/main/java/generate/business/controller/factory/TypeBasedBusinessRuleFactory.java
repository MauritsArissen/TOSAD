package generate.business.controller.factory;

import define.business.domain.businessrules.BusinessRule;
import define.business.domain.businessrules.RangeRule;
import generate.business.domain.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TypeBasedBusinessRuleFactory implements BusinessRuleFactory {
    String type;

    public TypeBasedBusinessRuleFactory(String type) {
        this.type = type;
    }

    ;

    @Override
    public BusinessRule createRule(JSONObject jsondata) {
        JSONArray jsonArray = (JSONArray) jsondata.get("values");
        ArrayList<define.business.domain.LiteralValue> values = new ArrayList<>();

        for (Object value : jsonArray) {
            define.business.domain.LiteralValue lvalue = new define.business.domain.LiteralValue(value.toString());
            values.add(lvalue);
        }

        define.business.domain.Operator operator = new define.business.domain.Operator(jsondata.get("operator").toString());
        // BRG_VB_ must be generated, hardcoded for testing.
        String triggercode = "BRG_VB_" + jsondata.get("table").toString().substring(0, 2) + jsondata.get("table").toString().substring(jsondata.get("table").toString().length() - 1) + "_trigger";
        define.business.domain.Trigger trigger = new define.business.domain.Trigger(triggercode, "before delete or insert or update", jsondata.get("failureMessage").toString());
        define.business.domain.Table table = new define.business.domain.Table(jsondata.get("table").toString(), new define.business.domain.TableAttribute(jsondata.get("attribute").toString()));

        if (type.equals("Attribute Range rule")) {
            return new RangeRule(operator, trigger, table, values);

        } else if (type.equals("Attribute Compare rule")) {

        } else if (type.equals("Attribute List rule")) {

        } else if (type.equals("Attribute Other rule")) {

        } else if (type.equals("Tuple Compare rule")) {

        } else if (type.equals("Tuple Other rule")) {

        } else if (type.equals("Inter-Entity Compare rule")) {

        } else if (type.equals("Entity Other rule")) {

        } else {
            // modify rule
            return null;
        }
        return null;
    }
}
