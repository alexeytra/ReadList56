package classes;

public class ConverterToEngLetter {
    static String getEngLetter(String s){
        switch (s){
            case "а": return "a";
            case "б": return "b";
            case "в": return "c";
            case "г": return "d";
            case "д": return "e";
            case "е": return "f";
            case "ж": return "g";
            default: return "";
        }
    }
}
