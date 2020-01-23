package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;

public interface BusinessRule {
    String generateDynamicPart();
    void addValue(LiteralValue value);
    String getName();
}
