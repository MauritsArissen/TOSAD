package define.persistence.adapter;

import define.business.domain.businessrules.BusinessRule;

public interface GenerateSerializer {

    public String serialize(BusinessRule rule);
}
