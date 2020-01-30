package generate.business.domain.businessrules;

import generate.business.domain.businessrules.ruleattributes.LiteralValue;
import generate.business.domain.businessrules.ruleattributes.Operator;
import generate.business.domain.businessrules.ruleattributes.Table;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;

public class AttributeCompareRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;
    private String name;
    private String constraintTemplate;
    private String declareTemplate;

    public AttributeCompareRule(Operator operator, Table table, String failuremessage, String name, String constraintTemplate, String declareTemplate) {
        this.operator = operator;
        this.values = new ArrayList<>();
        this.table = table;
        this.failuremessage = failuremessage;
        this.name = name;
        this.constraintTemplate = constraintTemplate;
        this.declareTemplate = declareTemplate;
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

    public String generateDeclare() {
        return "";
    }

    public String generateDynamicPart() {
        String template =  "--" + name + "\n" +
                "l_passed := :new." + table.getSelectedTableAttribute().getName() + " " + operator.getName() + " ";
        if(NumberUtils.isNumber(values.get(0).getValue())) {
            template += values.get(0).getValue() + ";\n";
        } else {
            template += "'" + values.get(0).getValue() + "';\n";
        }
        template += "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

}