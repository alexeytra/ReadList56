package classes;

public class IntermediateList {
    String NameRazd;
    String description;
    String code;
    String value;
    String type;
    String begin = "";
    String end = "";
    String beginPer = "";
    String endPer = "";
    String npa;

    public IntermediateList(String nameRazd, String description, String code, String value, String type, String begin, String end, String beginPer, String endPer, String npa) {
        NameRazd = nameRazd;
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

    public String getNameRazd() {
        return NameRazd;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public String getBeginPer() {
        return beginPer;
    }

    public String getEndPer() {
        return endPer;
    }

    public String getNpa() {
        return npa;
    }
}
