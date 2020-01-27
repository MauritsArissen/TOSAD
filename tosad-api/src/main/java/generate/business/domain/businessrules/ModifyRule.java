package generate.business.domain.businessrules;

import generate.business.domain.businessrules.ruleattributes.LiteralValue;
import generate.business.domain.businessrules.ruleattributes.Operator;
import generate.business.domain.businessrules.ruleattributes.Table;

import java.util.ArrayList;

public class ModifyRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;
    private String name;

    public ModifyRule(Operator operator, Table table, String failuremessage, String name) {
        this.operator = operator;
        this.values = values;
        this.table = table;
        this.failuremessage = failuremessage;
        this.name = name;
    }

    public void addValue(LiteralValue value) {
        values.add(value);
    }

    public String getName() {
        return name;
    }

    public Table getTable () {
        return table;
    }

    public String generateDynamicPart() {
        //Checks of het vergelijkbare data types zijn, front-end check?
        //Checks of het verschillende tabellen zijn, front-end check?

        //geen idee of dit klopt
        String template =  "--" + name + " code\n" +
                values.get(0).getValue() + "\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";

        return template;
    }

    public String generateDeclare() {
        String template =  "--" + name + " declare\n" +
                values.get(1).getValue() + "\n";

        return template;
    }
}
