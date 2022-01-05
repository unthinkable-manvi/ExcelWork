package com.assignments.organization.excelExport;
import com.assignments.organization.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Employee> employeeList;

      public  EmployeeExcelExporter(List<Employee> employeeList){
          this.employeeList=employeeList;
          workbook=new XSSFWorkbook();
      }
      private  void createCell(Row row, int columnCount, Object value, CellStyle style){
          sheet.autoSizeColumn(columnCount);
          Cell cell=row.createCell(columnCount);
          if(value instanceof  Long){
              cell.setCellValue((Long) value);
          }else if(value instanceof Integer){
              cell.setCellValue((Integer) value);
          }else if(value instanceof  Boolean){
              cell.setCellValue((Boolean) value);
          }if(value instanceof  String){
              cell.setCellValue((String) value);
          }
        cell.setCellStyle(style);
      }
      private void writeHeaderLine(){
          sheet=workbook.createSheet("Employee");
          Row row=sheet.createRow(0);
          CellStyle style=workbook.createCellStyle();
          XSSFFont font=workbook.createFont();
          font.setBold(true);
          font.setFontHeight(20);
          style.setFont(font);
          style.setAlignment(HorizontalAlignment.CENTER);
          createCell(row,0,"Employee Information", style);
          sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
          font.setFontHeightInPoints((short)(10));


          row=sheet.createRow(1);
          font.setBold(true);
          font.setFontHeight(16);
          style.setFont(font);
          createCell(row,0,"Employee Id",style);
          createCell(row,1,"Employee Name",style);
          createCell(row,2,"Employee Address",style);
          createCell(row,3,"Employee Email",style);
          createCell(row,4,"Employee Phonenum",style);
          createCell(row,5,"Employee Status",style);

      }
      private  void writeDataLines(){
          int rowCount=2;
          CellStyle style=workbook.createCellStyle();
          XSSFFont font=workbook.createFont();
          font.setFontHeight(12);
          style.setFont(font);

          for (Employee emp:employeeList){
              Row row=sheet.createRow(rowCount++);
              int  columnCount=0;
              createCell(row,columnCount++,emp.getEmpId(),style);
              createCell(row,columnCount++,emp.getEmpName(),style);
              createCell(row,columnCount++,emp.getEmpAddress(),style);
              createCell(row,columnCount++,emp.getEmpEmail(),style);
              createCell(row,columnCount++,emp.getEmpPhonenum(),style);
              createCell(row,columnCount++,emp.getStatus(),style);

          }
      }
      public void export(HttpServletResponse emp) throws IOException {
          writeHeaderLine();
         writeDataLines();
          ServletOutputStream outputStream=emp.getOutputStream();
          workbook.write(outputStream);
          workbook.close();
          outputStream.close();
      }

}
