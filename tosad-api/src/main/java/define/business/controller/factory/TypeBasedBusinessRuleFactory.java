package define.business.controller.factory;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import define.business.domain.LiteralValue;
import define.business.domain.Operator;
import define.business.domain.Table;
import define.business.domain.TableAttribute;
import define.business.domain.Trigger;
import define.business.domain.businessrules.BusinessRule;
import define.business.domain.businessrules.RangeRule;

public class TypeBasedBusinessRuleFactory implements BusinessRuleFactory {
    String type;

    public TypeBasedBusinessRuleFactory(String type) {
        this.type = type;
    };

    @Override
    public BusinessRule createRule(JSONObject jsondata) {
    	JSONArray jsonArray = (JSONArray) jsondata.get("values");
    	ArrayList<LiteralValue> values = new ArrayList<>();
    	
		for (Object value : jsonArray) {
			LiteralValue lvalue = new LiteralValue(value.toString());
			values.add(lvalue);
		}

    	Operator operator = new Operator(jsondata.get("operator").toString());
    	// BRG_VB_ must be generated, hardcoded for testing.
    	String triggercode = "BRG_BRGEN_" + jsondata.get("table").toString().substring(0, 2) + jsondata.get("table").toString().substring(jsondata.get("table").toString().length() - 1) + "_trigger";
    	String rulename = "BRG_BRGEN_" + jsondata.get("table").toString().substring(0, 2) + jsondata.get("table").toString().substring(jsondata.get("table").toString().length() - 1) + "_CNS_";
    	Trigger trigger = new Trigger(triggercode, jsondata.get("triggerEvent").toString() , jsondata.get("failureMessage").toString());
    	Table table = new Table(jsondata.get("table").toString(), new TableAttribute(jsondata.get("attribute").toString()));	
    	
    	if (type.equals("Attribute Range rule")) {
    		return new RangeRule(rulename, operator, trigger, table, values, type);
    		
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
