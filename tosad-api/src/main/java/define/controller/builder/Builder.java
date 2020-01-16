package define.controller.builder;

import define.domain.*;

import java.util.ArrayList;

public interface Builder {
    void reset();
    void setName(String name);
    void setType(BusinessRuleType businessRuleType);
    void setOperator(Operator operator);
    void setTrigger(Trigger trigger);
    void setParameters(ArrayList<Parameter> parameters);
    void setTarget(Table table);
}
