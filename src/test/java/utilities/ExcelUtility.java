package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public  FileInputStream fi;
    public  FileOutputStream fo;
    public  XSSFWorkbook wb;
    public  XSSFSheet ws;
    public  XSSFRow row;
    public  XSSFCell cell;
    public  CellStyle style;
    String path;

    public ExcelUtility(String path)
    {
        this.path=path;
    }
    public  int getRowCount(String xlSheet) throws IOException {
        fi=new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws=wb.getSheet(xlSheet);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String xlSheet,int rownum) throws IOException {
        fi=new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws=wb.getSheet(xlSheet);
        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;

    }

    public  String getCellData(String xlSheet,int rownum,int colnum) throws IOException {
        fi=new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        ws=wb.getSheet(xlSheet);

        String data ="";
        try
        {
            row = ws.getRow(rownum);
            if(row != null) {
                cell = row.getCell(colnum);
                if (cell != null) {
                    //data = cell.toString();
                    DataFormatter formatter = new DataFormatter();
                    data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a string regardless of the cell type
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception while reading cell data: " + e.getMessage());
            data="";
        }
        finally {
            wb.close();
            fi.close();
        }

        return data;
    }
    public  void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
        File xlfile = new File(path);
        if(!xlfile.exists())
        {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);

        }
        fi=new FileInputStream(path);
        wb = new XSSFWorkbook(fi);

        if(wb.getSheetIndex(sheetName)==-1)
        {
            wb.createSheet(sheetName);
        }

        ws=wb.getSheet(sheetName);

        if(ws.getRow(rownum)==null)
        {
            ws.createRow(rownum);
        }
        row = ws.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }
    public  void fillGreenColor(String xlSheet,int rownum,int colnum) throws IOException {
        File xlfile = new File(path);
        if(!xlfile.exists())
        {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);

        }
        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlSheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo=new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }
    public  void fillRedColor(String xlSheet,int rownum,int colnum) throws IOException {

        File xlfile = new File(path);
        if(!xlfile.exists())
        {
            wb = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            wb.write(fo);

        }

        fi=new FileInputStream(path);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlSheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo=new FileOutputStream(path);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }


}
