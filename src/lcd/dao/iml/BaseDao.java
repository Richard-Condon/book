package lcd.dao.iml;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-25 8:45
 */
public abstract class BaseDao {
    //使用dbutils操作数据库
    private QueryRunner queryRunner=new QueryRunner();

    public int updatauser(Connection conn,String sql,Object...args){
        System.out.println("BaseDao程序在【" + Thread.currentThread().getName() + "】中");
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public <T> T select(Class<T> type,Connection conn,String sql,Object...args){
        try {
            BeanHandler<T> beanHandler = new BeanHandler<T>(type);
            return queryRunner.query(conn,sql,beanHandler,args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public <T>List<T> selectlist(Class<T> type, Connection conn, String sql, Object...args){

        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object select1(Connection conn,String sql,Object...args){
        try {
            ScalarHandler scalarHandler = new ScalarHandler();
            return queryRunner.query(conn,sql,scalarHandler,args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
