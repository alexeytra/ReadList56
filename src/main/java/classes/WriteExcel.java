package classes;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteExcel {
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillBackgroundColor(HSSFColor.LAVENDER.index);
        return style;
    }

    private static HSSFCellStyle createStyleForCell(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 11);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public static void WriteToExcel(List<ListOne> l, int choose) throws IOException {

        File file = new File("List1956.xls");
        //file.getParentFile().mkdirs();
        HSSFWorkbook workbook = null;

        if (file.exists()){
            //Запись если файл существует
            FileInputStream inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);
            write(workbook, choose, l, file);
            //
            inputStream.close();
        } else {
            workbook = new HSSFWorkbook();
            write(workbook, choose, l, file);
            //
        }

    }

    public static void write(HSSFWorkbook workbook, int choose, List<ListOne> l, File file){
        FileOutputStream outFile = null;
        HSSFSheet sheet;
        switch (choose) {
            case 1: sheet = workbook.createSheet("СТ отрасли"); break;
            case 2: sheet = workbook.createSheet("СТ виды работ"); break;
            case 3: sheet = workbook.createSheet("СТ пункты"); break;
            case 4: sheet = workbook.createSheet("СТ подпункты"); break;
            case 5: sheet = workbook.createSheet("СТ позиции"); break;
            default:  sheet = workbook.createSheet("error");
        }
        int rownum = 0;
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForTitle(workbook);
        HSSFCellStyle styleforSell = createStyleForCell(workbook);

        row = sheet.createRow(rownum);

        //Название столбцов
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("discription (Описание)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Сode (Кодировка)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("value (Значение)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("type (Тип)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("begin (Срок с)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("end (Срок по)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("beginPer (Срок периода с)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("endPer (Срок периода по)");
        cell.setCellStyle(style);
        //
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("НПА");
        cell.setCellStyle(style);

        for (ListOne p : l) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getDescription());
            //
            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getCode());
            //
            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getValue());
            //
            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getType());
            //
            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getBegin());
            //
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getEnd());
            //
            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getBeginPer());
            //
            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getEndPer());
            //
            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(styleforSell);
            cell.setCellValue(p.getNpa());
        }
        for (int i = 1; i <= 8; i++) {
            sheet.autoSizeColumn(i);
        }

        //Запись результата
        try {
            outFile = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Все ok " + file.getAbsolutePath());
    }

    public static List<IntermediateList> getList(String path) throws IOException {
        List<StringOfList> listOfRazd = new ArrayList<>();
        String[] cells = new String[2];
        List<String> interString = new ArrayList<>();
        List<IntermediateList> interList = new ArrayList<>();
        List<ListOne> listOfPosition = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(path));
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Ногмер раздела и Название раздела
        int i = 0;
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                if (i == 2) break;
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellTypeEnum();
                switch (cellType) {
                    case STRING:
                        cells[i] = cell.getStringCellValue();
                        break;
                }
                i++;
            }

            listOfRazd.add(new StringOfList(cells[1], cells[0]));
            i = 0;
        }


        // Позиции
        sheet = workbook.getSheetAt(4);
        rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                CellType cellType = cell.getCellTypeEnum();
                i++;
                switch (cellType) {
                    case STRING:
                        interString.add(cell.getStringCellValue());
                        break;
                }
            }
            listOfPosition.add(new ListOne(interString.get(0), interString.get(1), interString.get(2), interString.get(3), interString.get(4),
                    interString.get(5), interString.get(6), interString.get(7), interString.get(8)));
            interString.clear();
            i = 0;
        }
        boolean back = false;
        String razd = "";

        ListIterator<StringOfList> iteratorForListOfRazd = listOfRazd.listIterator();
        ListIterator<ListOne> interatorForListOfPositon = listOfPosition.listIterator();

        while (iteratorForListOfRazd.hasNext()){

            StringOfList stringOfList = iteratorForListOfRazd.next();
            while (interatorForListOfPositon.hasNext()){
                ListOne listOne;
                if (back) {
                    listOne = interatorForListOfPositon.next();
                    while (interatorForListOfPositon.hasPrevious())
                        listOne = interatorForListOfPositon.previous();
                    back = false;
                }
                else
                    listOne = interatorForListOfPositon.next();

                Pattern p = Pattern.compile("(razd[0-9]+)");
                Matcher m = p.matcher(listOne.getCode());
                while(m.find()) {
                    razd = m.group();
                }

                if(stringOfList.getNum().equals(razd)) {
                    interList.add(new IntermediateList(stringOfList.content, listOne.getDescription(),
                            listOne.getCode(), listOne.getValue(),
                            listOne.getType(), listOne.getBegin(),
                            listOne.getEnd(), listOne.getBeginPer(),
                            listOne.getEndPer(), listOne.getNpa()));
                    interatorForListOfPositon.remove();
                }else { break;}
            }
            back = true;
            iteratorForListOfRazd.remove();
        }

        //interList.forEach((temp) -> System.out.println(temp.NameRazd + " " + temp.description));

        return interList;



    }

}
