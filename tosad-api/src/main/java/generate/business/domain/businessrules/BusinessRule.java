package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Table;

public interface BusinessRule {
    String generateDynamicPart();
    void addValue(LiteralValue value);
    String getName();
    Table getTable();
}
