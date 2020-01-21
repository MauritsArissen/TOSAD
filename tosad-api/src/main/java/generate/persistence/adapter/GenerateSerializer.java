package generate.persistence.adapter;

import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.RangeRule;

public interface GenerateSerializer {

    public String serialize (BusinessRule rule);
}
