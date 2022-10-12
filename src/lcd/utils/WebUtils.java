package lcd.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @athor lcdstart
 * @create 2022-07-29 9:18
 */
public class WebUtils {
    public static  <T>T  copyParamBean(Map value, Object bean){
        try {
            /**
             * 把所有请求参数注入到bean对象中
             */
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return (T) bean;
    }
    public static int parseInt(String strInt,int defaultValue){
        if (strInt==null){
            return defaultValue;
        }
        return Integer.parseInt(strInt);


    }
}
