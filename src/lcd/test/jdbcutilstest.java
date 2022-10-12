package lcd.test;

import lcd.utils.JdbcUtils;
import org.junit.jupiter.api.Test;

/**
 * @athor lcdstart
 * @create 2022-07-24 17:17
 */
public class jdbcutilstest {
    @Test
    public void test() {
       for (int i=0;i<100;i++){
           System.out.println(JdbcUtils.getConnection());
       }
    }
}
