package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

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


    public String generateDynamicPart() {
        //Checks of het vergelijkbare data typen zijn, front-end check?

        //Er is nog geen onderscheid in de getSelectedTableAttribute(). Het heeft nog geen weet van de twee verschillende attributen
        return  "  l_compareValue " + table.getName() + "." + table.getSelectedTableAttributes() + ";\n" +
                "  l_targetValue " + values.get(0).getValue() + "." + values.get(1).getValue() + "%type;\n" +
                "begin\n" +
                "l_passed := :new." + table.getSelectedTableAttributes() + " " + operator.getName() + " :new." +
                values.get(0).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
    }

}