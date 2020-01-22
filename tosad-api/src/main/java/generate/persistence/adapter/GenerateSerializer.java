package generate.persistence.adapter;

import generate.business.domain.businessrules.BusinessRule;

public interface GenerateSerializer {

    public String serialize (BusinessRule rule);
}
