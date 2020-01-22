package generate.business.domain;

public class LiteralValue {
    private String value;
    private String datatype;
    private int length;
    private int scale;

    public LiteralValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
