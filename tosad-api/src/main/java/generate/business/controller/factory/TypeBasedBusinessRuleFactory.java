package generate.business.controller.factory;

import generate.business.domain.*;
import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.RangeRule;

import java.util.ArrayList;

public class TypeBasedBusinessRuleFactory implements BusinessRuleFactory {
    String type;

    public TypeBasedBusinessRuleFactory(String type) {
        this.type = type;
    };

    @Override
    public String createRule(String name, Operator operator, Trigger trigger, ArrayList<Parameter> parameters, Table table) {
        // allemaal if-statements per type, dan maak je de juiste businessrule
        // Try-catch schrijven om een verkeerde input te vangen? In principe is een verkeerde input niet mogelijk.
        String generatedRule = null;
        if (type.equals("ARNG")) {
            //parameters is nu een Parameter object, niet een list.
            generatedRule = new RangeRule(name, operator, trigger, parameters, table).generate();


        } else if (type.equals("ACMP")) {

        } else if (type.equals("ALIS")) {

        } else if (type.equals("AOTH")) {

        } else if (type.equals("TCMP")) {

        } else if (type.equals("TOTH")) {

        } else if (type.equals("ICMP")) {

        } else if (type.equals("EOTH")) {

        } else if (type.equals("MODI")) {

        } else {
            return null;
        }
        return null;
    }
}
