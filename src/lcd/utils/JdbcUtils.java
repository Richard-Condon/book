package lcd.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**获取数据库连接池的连接
 * @athor lcdstart
 * @create 2022-07-24 16:47
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;


    static {
        Properties pro=new Properties();
        InputStream fis = null;
        try {
            File file=new File("D:\\IJ\\新建文件夹\\book\\src\\jdbc.properties");
            fis=(InputStream) new FileInputStream(file);
            pro.load(fis);
            dataSource=(DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static Connection getConnection(){
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    public static void CloseConnrctionAndCommit(Connection conn){
        if (conn!=null){
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void CloseConnrctionAndRollback(Connection conn){
        if (conn!=null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
