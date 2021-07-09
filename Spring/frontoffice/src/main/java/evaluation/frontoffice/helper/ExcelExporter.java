package evaluation.frontoffice.helper;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Object modele;
	private List<Object> liste;
	
	public ExcelExporter(Object modele,List<Object> liste) {
		this.modele=modele;
		this.liste=liste;
		this.workbook=new XSSFWorkbook();
	}


	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public List<Object> getListe() {
		return liste;
	}

	public void setListe(List<Object> liste) {
		this.liste = liste;
	}
	
	
	public void writeHeaderLine() {
		String name=this.modele.getClass().getSimpleName()+"s";
		sheet=workbook.createSheet(name);
		
		Row row=sheet.createRow(0);
		
		// create colonne header
		CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        
        ArrayList<String> colonnes=Util.getFieldsName(this.modele);
        for(int i=0;i<colonnes.size();i++) {
        	this.createCell(row, i,colonnes.get(i), style);
        }
        
	}
	
	 private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else if(value instanceof Double) {
	            cell.setCellValue((Double) value);
	        }else if(value instanceof String) {
	        	cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	 }
	 
	 private void writeDataLines() throws Exception{
		 int rowCount=1;
		 
		 CellStyle style = workbook.createCellStyle();
	     XSSFFont font = workbook.createFont();
	     font.setFontHeight(12);
	     style.setFont(font);
	     ArrayList<String> methods=Util.getMethodGet(Util.getFieldsName(this.modele));
	     for(Object modele:this.liste) {
	    	 Row row=this.sheet.createRow(rowCount++);
	    	 for(int i=0;i<methods.size();i++) {
	         	this.createCell(row,i,modele.getClass().getMethod(methods.get(i), null).invoke(modele, null), style);
	         }
	     }
	     
	 }
	 
	 public void export(HttpServletResponse response) throws Exception{
		 response.setContentType("application/octet-stream");
		 DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		 String currentDateTime = dateFormatter.format(new Date());
		         
		 String headerKey = "Content-Disposition";
		 String headerValue = "attachment; filename="+ this.modele.getClass().getSimpleName() +"s " + currentDateTime + ".xlsx";
		 response.setHeader(headerKey, headerValue);
		 this.writeHeaderLine();
		 this.writeDataLines();
		 
		 ServletOutputStream outputStream = response.getOutputStream();
	     workbook.write(outputStream);
	     workbook.close();
	         
	     outputStream.close();
	 }
	
}
