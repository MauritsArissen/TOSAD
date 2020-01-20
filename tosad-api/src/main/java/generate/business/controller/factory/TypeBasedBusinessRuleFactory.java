package generate.business.controller.factory;

import generate.business.domain.*;
import generate.business.domain.businessrules.RangeRule;

public class TypeBasedBusinessRuleFactory implements BusinessRuleFactory {
    String type;

    public TypeBasedBusinessRuleFactory(String type) {
        this.type = type;
    };

    @Override
    public RangeRule createRule(String name, Operator operator, Trigger trigger, Parameter parameters, Table table) {
        // allemaal if-statements per type, dan maak je de juiste businessrule
        return null;
    }
}
