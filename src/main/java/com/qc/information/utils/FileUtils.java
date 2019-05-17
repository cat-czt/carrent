package com.qc.information.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: czt
 * @Date: 19-ie10-viewport-bug-workaround.js-4 下午3:13
 */
public class FileUtils {


    /**
     *
     * @param baseUrl  下载路径
     * @param dirPath  目标路径
     */
    public static void downLoadFile(String baseUrl,String dirPath) {
        URL url = null;
        try {
            //获取到文件输入流
            url = new URL(baseUrl);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            //获取到文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream(new File(dirPath));
            //字节流
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
