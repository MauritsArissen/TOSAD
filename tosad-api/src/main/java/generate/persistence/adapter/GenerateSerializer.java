package generate.persistence.adapter;

import generate.business.domain.businessrules.RangeRule;

public interface GenerateSerializer {

    public String serialize(RangeRule businessrule);
}
