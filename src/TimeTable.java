
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
			XSSFWorkbook workbook = null;
		    XSSFSheet sheet = null;
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
					/*
					 * switch here
					 * */
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
				System.out.print(excelTable[i][j]+"\t");
			}
			System.out.print("\n");
		}
	}
}
	

