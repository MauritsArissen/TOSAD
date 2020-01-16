package define.controller.builder;

import define.domain.*;

import java.util.ArrayList;

public interface Builder {
    void setName(String name);
    void setType(BusinessruleType businessruleType);
    void setOperator(Operator operator);
    void setTrigger(Trigger trigger);
    void setParameters(ArrayList<Parameter> parameters);
    void setTarget(Table table);
}
