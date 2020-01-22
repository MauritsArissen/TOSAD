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
    private Table table;

    public InterEntityCompareRule(Operator operator, Trigger trigger,
                                  ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

    public String generateDynamicPart() {
        //Checks of het vergelijkbare data types zijn, front-end check?
        //Checks of het verschillende tabellen zijn, front-end check?

        //geen idee of dit klopt
        String template = "cursor lc_first is\n" +
                "select " + table.getName() + "." + table.getSelectedTableAttributes() + "\n" +
                "from " + table.getName() + "\n" +
                "l_targetValue " + values.get(0).getValue() + "." + values.get(1).getValue() + "%type;\n" +
                "begin\n" +
                "open lc_first;\n" +
                "fetch lc_first into l_targetValue;\n" +
                "close lc_first;\n" +
                "l_passed := :new." + table.getSelectedTableAttributes() + " " + operator.getName() + " l_targetValue;\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

}