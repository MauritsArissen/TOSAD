package define.business.domain.businessrules;

import define.business.domain.*;

import java.util.ArrayList;

public class InterEntityCompareRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public InterEntityCompareRule(Operator operator, Trigger trigger,
                                  ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

	@Override
	public Operator getOperator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trigger getTrigger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LiteralValue> getValues() {
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