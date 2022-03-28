package propertyFinder;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcelData {

    public static List<String> readFile(int rowNumber) throws IOException {
        FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\propertyDetails.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowNumber);
        List<String> values = new ArrayList<>();
        for(Cell cell: row){
            values.add(cell.getStringCellValue());
        }
        return values;
    }

}
