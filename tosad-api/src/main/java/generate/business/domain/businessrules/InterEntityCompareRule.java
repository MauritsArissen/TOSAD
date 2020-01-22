package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

import java.util.ArrayList;

public class InterEntityCompareRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private ArrayList<Table> table;

    public InterEntityCompareRule(Operator operator, Trigger trigger,
                                ArrayList<LiteralValue> values, ArrayList<Table> table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

    public String generateDynamicPart() {
        //Checks of het vergelijkbare data types zijn, front-end check?
        //Checks of het verschillende tabellen zijn, front-end check?

        //Er is nog geen onderscheid in de getSelectedTableAttribute(). Het heeft nog geen weet van de twee verschillende attributen
        return "l_passed := :new." + table.get(0).getSelectedTableAttribute().get(0) + " " + operator.getName() + " :new." +
                table.get(1).getSelectedTableAttribute().get(0) + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
    }

}