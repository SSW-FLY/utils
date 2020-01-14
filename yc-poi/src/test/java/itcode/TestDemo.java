package itcode;

import itcode.utils.POIUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

public class TestDemo {

  private List<Integer> list = new ArrayList<>();

  @Before
  public void before() {
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
  }

  @Test
  public void test01() {
    int now = LocalDateTime.now().getDayOfMonth();
    int dayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
    int dayOfYear = LocalDateTime.now().getDayOfYear();
    System.out.println(dayOfYear);
    System.out.println(dayOfWeek);
    System.out.println(now);
  }


  @Test
  public void test02() {
    XSSFWorkbook workBook = POIUtils.createWorkBook();
    XSSFSheet sheet = workBook.createSheet();
    XSSFRow row = sheet.createRow(0);
    XSSFCell cell = row.createCell(0);
    cell.setCellValue(123);
    CellType cellType = cell.getCellType();
    System.out.println(cellType);
    switch (cellType) {
      case NUMERIC:
        Double a = cell.getNumericCellValue();
        break;
      case _NONE:
        String error = cell.getErrorCellString();
        System.out.println(error);
        break;
      case BLANK:
    }
  }

  @Test
  public void test03() {
    Integer integer = list.stream().reduce(1, (x, y) -> x * y);
    System.out.println(integer);
  }

  @Test
  public void test04() {
    HashMap<String, String> map = new HashMap<>();
    map.put("string", null);
    map.putIfAbsent("string", "sss");
    String string = map.get("string");
    System.out.println(string);
  }

  @Test
  public void test05() {
    Predicate<Integer> filter = x -> x > 2;
    List<Integer> collect = Optional.ofNullable(list).orElse(Collections.emptyList()).stream()
        .filter(filter).collect(
            Collectors.toList());
    System.out.println(collect);

    List<Integer> list1 = list.stream().skip(4).collect(Collectors.toList());
    System.out.println(list1);
  }

  @Test
  public void test06() {
    UnaryOperator<String> unaryOperator = x -> x + 1;
    String apply = unaryOperator.apply("2");
    System.out.println(apply);
  }
}
