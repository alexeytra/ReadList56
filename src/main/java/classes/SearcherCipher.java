package classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SearcherCipher {
    List<IntermediateList> list1991;
    List<IntermediateList> list1956;
    List<ListOne> list;


    public SearcherCipher(List<IntermediateList> list1956, List<IntermediateList> list1991) {
        this.list1991 = list1991;
        this.list1956 = list1956;
    }

    public List<ListOne> search() {
        list = new ArrayList<>();

        ListIterator<IntermediateList> interatorOfList1991 = list1991.listIterator();
        ListIterator<IntermediateList> iteratorOfList1956 = list1956.listIterator();

        while (iteratorOfList1956.hasNext()){
            IntermediateList list1 = iteratorOfList1956.next();
            while (interatorOfList1991.hasNext()){
                IntermediateList list2 = interatorOfList1991.next();
                if (searchConcurrences(list1.getNameRazd(), list2.getNameRazd())){
                    if(searchConcurrences(list1.getDescription(), list2.getDescription())){
                        list.add(new ListOne(list1.description, list1.code, list2.value, list1.type, list1.begin, list1.end, list1.beginPer, list1.endPer, list1.npa));
                        interatorOfList1991.remove();
                        break;
                    }
                }
            }
        }


        System.out.println("H");

        return list;
    }

    private boolean searchConcurrences(String str91, String str56){
        //String str91 = "Главные инженеры";
        //String str56 = "Начальники строительства шахт, главные инженеры и технические руководители строительства шахт; их заместители и помощники по производству\n";
        String[] partsOne = str91.toUpperCase().replaceAll(",", "").split("\\s{1,5}");
        String[] partsTwo = str56.toUpperCase().replaceAll(",", "").split("\\s{1,5}");
        int countMatch = 0;
        for ( Object entry: partsTwo ) {

            String entryText = (String)entry;
            for ( String part: partsOne ) {
                if (entryText.toUpperCase().contains(part)) {
                    if(entryText.length() == 2) break;
                    countMatch++;
                    break;
                }
            }
        }
        if ( countMatch >= 2 ) {
            return true;
        }else return false;
    }


}
