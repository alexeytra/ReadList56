import classes.IntermediateList;
import classes.Reader;
import classes.SearcherCipher;
import classes.WriteExcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        /*File file = new File("List1956.xls");
        if (file.exists() && file.delete()) {
            System.out.println("Удален");
        } else {
            System.out.println("Не удален");
        }

        String path = "C:/Users/Student/Desktop/11.txt";
        Reader reader = new Reader(path);
        reader.ReadFile();*/

        //Поиск шифра
        List<IntermediateList> list1956;
        List<IntermediateList> list1991;
        String path = "List1956.xls";
        list1956 = WriteExcel.getList(path);
        path = "List1991.xls";
        list1991 = WriteExcel.getList(path);
        SearcherCipher searcherCipher = new SearcherCipher(list1956, list1991);
        searcherCipher.search();

    }
}
