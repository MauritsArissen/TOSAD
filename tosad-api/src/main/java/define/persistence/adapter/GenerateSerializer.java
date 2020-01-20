package define.persistence.adapter;

import define.business.domain.businessrules.RangeRule;

public interface GenerateSerializer {

    public String serialize(RangeRule businessrule);
}
