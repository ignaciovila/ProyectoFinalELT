package edu.curso.java.bo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@SuppressWarnings("deprecation")
public class ExcelBuilder extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		@SuppressWarnings("unchecked")
		List<Proyecto> proyectos = (List<Proyecto>) model.get("proyectos");
		
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Listado Proyectos");
		sheet.setDefaultColumnWidth(30);
		
		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);
		
		// create header row
		HSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Id Proyecto");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("Nombre");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Descripcion");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("Usuario Principal");
		header.getCell(3).setCellStyle(style);
		
		header.createCell(4).setCellValue("Fecha de Inicio");
		header.getCell(4).setCellStyle(style);
		
		header.createCell(5).setCellValue("Fecha de Fin");
		header.getCell(5).setCellStyle(style);
		
		header.createCell(6).setCellValue("Cant. Hs. Asignadas");
		header.getCell(6).setCellStyle(style);
		
		header.createCell(7).setCellValue("Cant. Hs Utilizadas");
		header.getCell(7).setCellStyle(style);
		
		header.createCell(8).setCellValue("Usuarios");
		header.getCell(8).setCellStyle(style);
		
		int rowCount = 1;

		for (Proyecto proyecto : proyectos) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			
			HSSFCellStyle my_style = workbook.createCellStyle();
            my_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            my_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            my_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            my_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            my_style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            
            Cell cell0 = aRow.createCell(0);
            cell0.setCellValue(proyecto.getId());  
            cell0.setCellStyle(my_style); 
            Cell cell1 = aRow.createCell(1);
            cell1.setCellValue(proyecto.getNombre());  
            cell1.setCellStyle(my_style);
            Cell cell2 = aRow.createCell(2);
            cell2.setCellValue(proyecto.getDescripcion());  
            cell2.setCellStyle(my_style);
            Cell cell3 = aRow.createCell(3);
            cell3.setCellValue(proyecto.getUsuarioPrincipal().getNombreCompleto());  
            cell3.setCellStyle(my_style);
            Cell cell4 = aRow.createCell(4);
            cell4.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(proyecto.getFechaInicio()));  
            cell4.setCellStyle(my_style);
            Cell cell5 = aRow.createCell(5);
            cell5.setCellValue(new SimpleDateFormat("dd-MM-yyyy").format(proyecto.getFechaFinalizacion()));  
            cell5.setCellStyle(my_style);
            Cell cell6 = aRow.createCell(6);
            cell6.setCellValue(proyecto.getHorasAsignadas());  
            cell6.setCellStyle(my_style);
            Cell cell7 = aRow.createCell(7);
            cell7.setCellValue(proyecto.getHorasUtilizadas());  
            cell7.setCellStyle(my_style);
            Cell cell8 = aRow.createCell(8);
            for(Usuario usuario : proyecto.getUsuarios()) {
            	cell8.setCellValue(usuario.getNombreCompleto());
            }
            cell8.setCellStyle(my_style);
            
		}
	}

}