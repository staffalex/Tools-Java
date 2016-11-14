package de.ast.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel
{

  public static final int NUM_ROWS = 400;
  public static final int NUM_CELLS = 40;

  public static void main(String[] args)
  {
    long startTime = System.currentTimeMillis();

    for (int i = 0; i < 10; i++)
    {
      createExcelFile();
    }

    long stopTime = System.currentTimeMillis();

    System.out.println(stopTime - startTime);
  }

  public static void createExcelFile()
  {
    XSSFWorkbook wb = new XSSFWorkbook();

    XSSFSheet sheet1 = wb.createSheet();
    addSomeRowsToSheet(sheet1, NUM_ROWS);

    saveExcelToDisk(wb, "c:/temp/myExcelFile.xlsx");
  }

  private static void saveExcelToDisk(XSSFWorkbook wb, String fullFileName)
  {
    FileOutputStream fileOut;
    try
    {
      fileOut = new FileOutputStream(fullFileName);
      wb.write(fileOut);
      fileOut.close();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void addSomeRowsToSheet(XSSFSheet sheet, int numRows)
  {
    
    // rows und cells koennen direkt an der gewuenschten Position
    // erzeugt werden
    // Ein Erzeugen der Zeilen bzw. Zellen zwischen der letzten
    // und der gewuenschten Position ist NICHT notwendig 
    
    
//    Row row = sheet.createRow(17);
//    Cell cell = row.createCell(105);
    
    for (int i = 0; i < numRows; i++)
    {
      XSSFRow row = sheet.createRow(i);

      addSomeCellsToRow(row, NUM_CELLS);
    }
  }

  private static void addSomeCellsToRow(XSSFRow row, int numCells)
  {
    for (int i = 0; i < numCells; i++)
    {
      Cell cell = row.createCell(i);
      cell.setCellValue(getCellValue(row.getRowNum(), i));
    }
  }

  private static String getCellValue(int rowNum, int colNum)
  {
    String row = Integer.toString(rowNum);
    String col = Integer.toString(colNum);
    row = StringUtils.leftPad(row, 4, '0');
    col = StringUtils.leftPad(col, 4, '0');

    return row + ' ' + col;

    // bei 10-maligem Erzeugen eines Excel-Files mit 400 Zeilen und jeweils 40 Spalten 
    // ist das hier 0,4 - 0,5 Sekunden langsamer
    // return String.format("%04d %04d", rowNum, colNum);
  }
  
  private static Cell createCellAt(XSSFSheet sheet, int rowNum, int colNum)
  {
    Row row = getRowAt(sheet, rowNum, true);
    return row.createCell(colNum);
  }
  
  private static Row getRowAt(XSSFSheet sheet, int rowNum, boolean create)
  {
    if (rowNum > sheet.getLastRowNum())
    {
      return create == false ? null : createRowAt(sheet, rowNum);
    }
    return sheet.getRow(rowNum);
  }
  
  private static Row createRowAt(XSSFSheet sheet, int rowNum)
  {
    Row row = null;
    
    for(int i = sheet.getLastRowNum() + 1; i <= rowNum; i++)
    {
      row = sheet.createRow(i);
    }
    return row;
  }
  

}
