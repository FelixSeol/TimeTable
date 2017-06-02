
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
	private File file;
    private XSSFWorkbook wb = null;
    private XSSFSheet ws = null;
    private XSSFRow xr = null;
    private XSSFCell xc = null;
        
    @SuppressWarnings("deprecation")
	public TimeTable(String fileName){
    	
    	System.out.println(fileName);
		try {//엑셀 파일 오픈
			URL url = this.getClass().getResource(fileName);
			System.out.println(url);
			file = new File(url.getPath());
			wb = new XSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int sheetNum = wb.getNumberOfSheets();
		ws = wb.getSheetAt(0);
		int rows = ws.getPhysicalNumberOfRows();
		
		for(int i = 0 ; i < rows ; i++){
			xr = ws.getRow(i);
			int cells = xr.getPhysicalNumberOfCells();
			for(int j = 0 ; j < cells ; j++){
				xc = xr.getCell(j);
				switch(xc.getCellType()){
				case XSSFCell.CELL_TYPE_NUMERIC :
					System.out.println(xc.getNumericCellValue());
					break;
					
				case XSSFCell.CELL_TYPE_STRING :
					System.out.println(xc.getStringCellValue());
					break;
				
				case XSSFCell.CELL_TYPE_BLANK :
					System.out.println(xc.getBooleanCellValue());
					break;
					
				case XSSFCell.CELL_TYPE_ERROR :
					System.out.println(xc.getErrorCellString());
					break;
					
				case XSSFCell.CELL_TYPE_FORMULA :
					System.out.println(xc.getCellFormula());
					break;
				
				default :
					break;
				}
			}
		}
    }
	public static void main(String[] args){
		TimeTable tt = new TimeTable(args[0]);
	
	}
}
