package ExcelUtils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReadWrite {
    public static void main(String[] args)  {
//        getRowCount();
        getCellData();
    }
    public static void getRowCount(){
        String excelPath = "./data/test_data.xlsx";
        try {
            XSSFWorkbook wb = new XSSFWorkbook(excelPath);
            XSSFSheet s = wb.getSheet("Sheet1");
            int rowCount = s.getPhysicalNumberOfRows();
            System.out.println("no. of rows ="+ rowCount);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
    public static void getCellData(){
        String excelPath = "./data/test_data.xlsx";

        try {
            XSSFWorkbook wb = new XSSFWorkbook(excelPath);
            XSSFSheet s = wb.getSheet("Sheet2");
            String value = s.getRow(2).getCell(0).getStringCellValue();
            System.out.println("cell value is ="+value);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
