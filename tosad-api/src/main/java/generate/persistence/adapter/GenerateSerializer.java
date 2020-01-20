package generate.persistence.adapter;

import generate.business.domain.BusinessRule;

public interface GenerateSerializer {

    public String serialize(BusinessRule businessrule);
}
