package cn.mldn.web.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web 操作工具类
 *
 * @author yaojia
 * 
 */
public class WebUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

   
    public static void writeJSON(HttpServletResponse response, Object data) {
        try {
         
            response.setContentType("application/json"); 
            response.setCharacterEncoding("UTF-8"); 
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJson(data)); 
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }

    
    public static void writeHTML(HttpServletResponse response, Object data) {
        try {
            // 设置响应头
            response.setContentType("text/html"); 
            response.setCharacterEncoding("UTF-8");
            // 向响应中写入数据
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJson(data)); 
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("在响应中写数据出错！", e);
            throw new RuntimeException(e);
        }
    }
}