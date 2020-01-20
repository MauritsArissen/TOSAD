package define.persistence.adapter;

import define.business.domain.BusinessRule;

public interface GenerateSerializer {

    public String serialize(BusinessRule businessrule);
}
