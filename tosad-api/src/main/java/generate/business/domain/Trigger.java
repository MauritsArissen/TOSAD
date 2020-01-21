package generate.business.domain;

import generate.business.domain.businessrules.BusinessRule;

import java.util.ArrayList;

public class Trigger {
    private String triggercode;
    private String triggerevent;
    private String failuremessage;
    private ArrayList<BusinessRule> businessRules = new ArrayList<>();

    public Trigger(String triggercode, String triggerevent, String failuremessage, ArrayList<BusinessRule> businessRules) {
        this.triggercode = triggercode;
        this.triggerevent = triggerevent;
        this.failuremessage = failuremessage;
        this.businessRules = businessRules;
    }

    public String getTriggercode() {
        return triggercode;
    }

    public String getTriggerevent() {
        return triggerevent;
    }

    public String getFailuremessage() {
        return failuremessage;
    }

    public void addBusinessRule(BusinessRule businessRule) {
        businessRules.add(businessRule);
    }

    public ArrayList<BusinessRule> getBusinessRules() {
        return businessRules;
    }
}
