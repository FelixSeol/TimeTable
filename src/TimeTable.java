
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TimeTable {
	private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
	private String fileName;
	private int rowNum;
	private int columnNum;
	private String[][] excelTable;
	@SuppressWarnings("deprecation")
	public TimeTable(){}
	
	public TimeTable(String fileName){
		this.fileName = fileName;
		setExcelTable();
	}
	public void setExcelTable(){
		try{
			
		    XSSFRow row = null;
		    XSSFCell cell = null;
	
		    
	    	URL url = this.getClass().getResource(fileName);
			try {//엑셀 파일 오픈
				workbook = new XSSFWorkbook(new FileInputStream(new File(url.getPath())));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = workbook.getSheetAt(0);
			rowNum = sheet.getPhysicalNumberOfRows();
			columnNum = sheet.getRow(0).getPhysicalNumberOfCells();
			excelTable = new String[rowNum][columnNum];
			
			
			
			for(int i = 0 ; i < rowNum; i++){
				row = sheet.getRow(i);
				columnNum = Math.max(columnNum, row.getPhysicalNumberOfCells());
				for(int j = 0 ; j < columnNum; j++){
					cell = row.getCell(j);
					switch(cell.getCellType()){
		            case XSSFCell.CELL_TYPE_NUMERIC :
		                excelTable[i][j] = String.valueOf((long)Math.floor(cell.getNumericCellValue() + 0.5d));
		                break;
		               
		            case XSSFCell.CELL_TYPE_STRING :
		            	excelTable[i][j] = String.valueOf(cell.getStringCellValue());
		                break;
		            
		            case XSSFCell.CELL_TYPE_BLANK :
		            	excelTable[i][j] = "";
		                break;
		               
		            case XSSFCell.CELL_TYPE_ERROR :
		            	excelTable[i][j] = String.valueOf(cell.getErrorCellString());
		                break;
		               
		            case XSSFCell.CELL_TYPE_FORMULA :
		            	excelTable[i][j] = String.valueOf(cell.getCellFormula());
		            	break;
		            
		            default :
		                break;
		            }
				}
			}
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
	public void showExcelTable(){
		System.out.println("xlsx file name is : "+fileName);
		for(int i = 0; i < rowNum ; i++){
			for(int j = 0 ; j < columnNum ; j++){
				System.out.printf("%"+(sheet.getColumnWidthInPixels(j)/10 + 6)+"s",excelTable[i][j]);
			}
			System.out.print("\n");
		}
	}
}
	

