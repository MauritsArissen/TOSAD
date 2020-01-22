package define.business.domain.businessrules;

import java.util.ArrayList;

import define.business.domain.LiteralValue;
import define.business.domain.Operator;
import define.business.domain.Table;
import define.business.domain.TableAttribute;
import define.business.domain.Trigger;

public class RangeRule implements BusinessRule {
    private String name;
    private Operator operator;
    private Trigger trigger;
    private Table table;
    private ArrayList<LiteralValue> values = new ArrayList<>();

    public RangeRule(Operator operator, Trigger trigger, Table table, ArrayList<LiteralValue> values) {
        this.operator = operator;
        this.trigger = trigger;
        this.table = table;
        this.values = values;
    }

}
