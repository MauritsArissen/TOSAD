package define.business.domain.businessrules;

import define.business.domain.*;

import java.util.ArrayList;

public class TupleCompareRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public TupleCompareRule(Operator operator, Trigger trigger,
                            ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

}