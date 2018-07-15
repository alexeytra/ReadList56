package classes;

public class ListOne {
    String description;
    String code;
    String value;
    String type;
    String begin = "";
    String end = "";
    String beginPer = "";
    String endPer = "";
    String npa;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBeginPer() {
        return beginPer;
    }

    public void setBeginPer(String beginPer) {
        this.beginPer = beginPer;
    }

    public String getEndPer() {
        return endPer;
    }

    public void setEndPer(String endPer) {
        this.endPer = endPer;
    }

    public String getNpa() {
        return npa;
    }

    public void setNpa(String npa) {
        this.npa = npa;
    }

    public ListOne(String description, String code, String value, String type, String begin, String end, String beginPer, String endPer, String npa) {
        this.description = description;
        this.code = code;
        this.value = value;
        this.type = type;
        this.begin = begin;
        this.end = end;
        this.beginPer = beginPer;

        this.endPer = endPer;
        this.npa = npa;
    }
}
