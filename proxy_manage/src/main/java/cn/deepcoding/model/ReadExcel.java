package cn.deepcoding.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ReadExcel {
    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadExcel() {
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     * 
     * @param fielName
     * @return
     */
    public List getExcelInfo(MultipartFile mFile) {
    	List list =null;
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            list = createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }	

    /**
     * 根据excel里面的内容读取客户信息
     * 
     * @param is输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List createExcel(InputStream is, boolean isExcel2003) {
    	List list =null;
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            list = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 读取Excel里面客户的信息
     * 
     * @param wb
     * @return
     */
    private List readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<List> list = new ArrayList<List>();
        // 循环Excel行数
        for (int r = 0; r < totalRows; r++) {
        	Row row = sheet.getRow(r); 
        	if (row == null&&r!=0&&r!=1) continue;
        	List<String> list2 = new ArrayList<String>();
        	 if (row == null) continue;
            // 循环Excel的列
         s1:   for (int c = 0; c < this.totalCells; c++) { 
            	Cell cell = row.getCell(c);
            //转换cell格式string
            	if (cell != null){
            		cell.setCellType(CellType.STRING);	
            		if(r==0&&c==0&&cell.getStringCellValue()==""){	
            			list2.add("考试名称为空");  list.add(list2);  return list;
            		}else if(r==1&&c==0&&cell.getStringCellValue()==""){  
            			list2.add("日期不能为空");  list.add(list2);  return list;
            		}
            		list2.add(cell.getStringCellValue()); 
            	} 
            	else{
            		//当下一行学生信息没有时候停止添加 
            		if(r>2&&c==0){  return list;}
            		 list2.add(null);
            	}
            	//因为表头的考试时间和名称会分为多列读取，所以读取完第一个直接跳出此行循环
            	if(r==0|r==1){break s1;}
            }
            // 添加到list
            list.add(list2);
        }
        return list;
    }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

