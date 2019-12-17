package com.asiainfo.crm.order.util;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: dong.chao
 * @create: 2019-12-13 10:09
 * @description:
 **/
public class FlieUtils {

    public static void saveAsFileWriter(String filePath,String content) {
        FileOutputStream fos = null;
        try {
            //true不覆盖已有内容
            fos = new FileOutputStream(filePath, true);
            //写入
            fos.write(content.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }finally {
                return;
            }
        }
    }

}
