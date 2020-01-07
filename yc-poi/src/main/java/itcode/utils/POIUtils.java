package itcode.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * poi工具类
 */
public class POIUtils {

  public static XSSFWorkbook createWorkBook(String path) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    return workbook;
  }

  /**
   * 汇聚一个文件夹下的所有excel的数据
   * @param path 文件夹路径
   * @throws IOException io
   */
  public static void mergeExcel(String path) throws IOException {
    path = dealPath(path);
    XSSFWorkbook workBook = new XSSFWorkbook();
    workBook.createSheet();

    File file = new File(path);
    List<File> files = new ArrayList<>();
    if (file.isDirectory()) {
      // 文件名称
      String fileName = path;
      String[] list = file.list();
      files = Arrays.stream(list).map(x -> {
        File subFile = new File(fileName + x);
        return subFile;
      }).filter(x -> x.getAbsolutePath().endsWith(".xlsx") && !x.isDirectory())
          .collect(Collectors.toList());
    }
    FileOutputStream out = new FileOutputStream(path + "merge__"+UUID.randomUUID() + ".xlsx");
    XSSFSheet sheet = workBook.getSheetAt(0);
    // 行数计数器
    int count = 0;
    for (int i = 0; i < files.size(); i++) {
      FileInputStream in = new FileInputStream(files.get(i));
      XSSFWorkbook workbook = new XSSFWorkbook(in);
      XSSFSheet sheetAt = workbook.getSheetAt(0);

      for (Row row : sheetAt) {
        if (null != row) {
          XSSFRow row1 = (XSSFRow) row;
          XSSFRow row2 = sheet.createRow(count++);
          int cellNum = row1.getPhysicalNumberOfCells();
          for (int i1 = 0; i1 < cellNum; i1++) {
            XSSFCell cell = row2.createCell(i1);
            cell.setCellValue(row1.getCell(i1).getNumericCellValue());
          }
        }
      }
    }
    workBook.write(out);
  }

  // 处理文件夹路径.
  public static String dealPath(String path) {
    if (!path.endsWith("\\")) {
      path = path + "\\";
    }
    return path;
  }
}
