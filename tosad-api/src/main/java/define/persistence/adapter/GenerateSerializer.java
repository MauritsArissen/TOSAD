package define.persistence.adapter;

import define.domain.BusinessRule;

public interface GenerateSerializer {

    public String serialize(BusinessRule businessrule);
}
