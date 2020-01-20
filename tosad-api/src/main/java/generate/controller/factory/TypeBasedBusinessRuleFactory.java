package generate.controller.factory;

import generate.domain.*;

public class TypeBasedBusinessRuleFactory implements BusinessRuleFactory {
    String type;

    public TypeBasedBusinessRuleFactory(String type) {
        this.type = type;
    };

    @Override
    public BusinessRule createRule(String name, BusinessRuleType ruletype, Operator operator, Trigger trigger, Parameter parameters, Table table) {
        return null;
    }
}
