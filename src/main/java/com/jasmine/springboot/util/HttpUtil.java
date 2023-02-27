package com.jasmine.springboot.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xieshanghan
 * @version HttpUtil.java, v 0.1 2023年02月20日 20:13 xieshanghan
 */
public class HttpUtil {

    /**
     * 获取HTTP文件内容长度
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static long getHttpFileContentLength(String url) throws IOException {
        long contentLength;
        HttpURLConnection httpURLConnection = null;
        try {
            URL httpUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestProperty("", "");
            contentLength = httpURLConnection.getContentLengthLong();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return contentLength;
    }

    /**
     * 获取HttpURLConnection链接对象
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection)httpUrl.openConnection();
        //向文件所在的服务器发送标识信息
        httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1");
        return httpURLConnection;
    }

    public static HttpURLConnection getHttpURLConnection(String url, long startPos, long endPos) throws IOException {
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        if (endPos != 0) {
            httpURLConnection.setRequestProperty("RANGE","bytes=" + startPos + "-" + endPos);
        }else {
            httpURLConnection.setRequestProperty("RANGE","bytes=" + startPos + "-");
        }
        return httpURLConnection;
    }

    /**
     * 获取下载文件名
     *
     * @param url
     * @return
     */
    public static String getHttpFileName(String url) {
        int index = url.indexOf("/");
        return url.substring(index + 1);
    }

}