package define.domain;

public class Trigger {
    private String triggercode;
    private String triggerevent;
    private String failuremessage;

    public Trigger(String triggercode, String triggerevent, String failuremessage) {
        this.triggercode = triggercode;
        this.triggerevent = triggerevent;
        this.failuremessage = failuremessage;
    }
}
