package define.business.domain.businessrules;

import generate.business.domain.*;

import java.util.ArrayList;

public class TupleOtherRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public TupleOtherRule(Operator operator, Trigger trigger,
                          ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

}