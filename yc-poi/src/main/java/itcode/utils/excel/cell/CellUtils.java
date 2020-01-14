package itcode.utils.excel.cell;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row;

public class CellUtils {

  /**
   * 获取单元格值</br> 值分为string和num </br>
   */
  public static Object getCellValue(Cell cell, boolean isStrCellValue) {
    if (null == cell) {
      return null;
    }
    if (isStrCellValue) {
      return cell.getStringCellValue();
    } else {
      return cell.getNumericCellValue();
    }
  }

  /**
   * string
   *
   * @param cell cell
   * @return string
   */
  public static Object getStrCellValue(Cell cell) {
    return getCellValue(cell, true);
  }

  /**
   * num
   *
   * @param cell cell
   * @return num
   */
  public static Object getNumCellValue(Cell cell) {
    return getCellValue(cell, false);
  }

  /**
   * 公式没有处理,获取公式时为null
   *
   * @param cell     cell
   * @param cellType cellType
   * @return value
   */
  public static Object getCellValue(Cell cell, CellType cellType) {
    if (null == cell) {
      return null;
    }
    if (null == cellType) {
      return null;
    }
    Object value;
    switch (cellType) {
      case NUMERIC:
        value = cell.getNumericCellValue();
        break;
      case BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case FORMULA:
        value = null;
        break;
      case BLANK:
        value = "";
        break;
      case ERROR:
        final FormulaError error = FormulaError.forInt(cell.getErrorCellValue());
        value = (null == error) ? "" : error.getString();
        break;
      default:
        value = cell.getStringCellValue();
    }
    return value;
  }

  /**
   * 根据索引获取 or 创建cell
   *
   * @param row       r
   * @param cellIndex index
   * @return cell
   */
  public static Cell getOrCreateCell(Row row, int cellIndex) {
    Cell cell = row.getCell(cellIndex);
    if (null == cell) {
      cell = row.createCell(cellIndex);
    }
    return cell;
  }
}
