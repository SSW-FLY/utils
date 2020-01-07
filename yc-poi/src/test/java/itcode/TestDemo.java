package itcode;

import java.time.LocalDateTime;
import org.junit.Test;

public class TestDemo {

  @Test
  public void test01(){
    int now = LocalDateTime.now().getDayOfMonth();
    int dayOfWeek = LocalDateTime.now().getDayOfWeek().getValue();
    int dayOfYear = LocalDateTime.now().getDayOfYear();
    System.out.println(dayOfYear);
    System.out.println(dayOfWeek);
    System.out.println(now);
  }
}
