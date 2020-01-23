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

	@Override
	public define.business.domain.Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public define.business.domain.Trigger getTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public define.business.domain.Table getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<define.business.domain.LiteralValue> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}