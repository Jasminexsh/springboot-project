package com.jasmine.springboot.util;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 文件工具类
 *
 * @author xieshanghan
 * @version FileUtil.java, v 0.1 2023年02月15日 13:07 xieshanghan
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static final int BUFFER_SIZE = 4096;

    /** 默认超时时间 3分钟 */
    private static final int DEFAULT_OVERTIME = 3 * 60 * 1000;

    /** 标志位，不设置超时时间 */
    private static final int NONE_OVERTIME = -1;

    /** 下载线程数 */
    private static final int DOWNLOAD_THREAD_NUM = 10;

    /** */
    private static long downloadByteCount;

//    private static ExecutorService downloadThreadPool = DownloadThreadUtil.buildDownloadThreadPool(DOWNLOAD_THREAD_NUM);

    /**
     *
     *
     * @param path
     * @return
     */
    public static long getFileContentLength(String path) {
        File file = new File(path);
        return file.exists() && file.isFile() ? file.length() : 0;
    }

    /**
     * 检查路径是否存在
     *
     * @param path 绝对路径
     * @return
     */
    public static boolean checkPath(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 在指定路径下创建指定名称的文件夹
     *
     * @param path    绝对路径
     * @param dirName 文件夹名称
     */
    public static void createDir(String path, String dirName) {
        //校验指定路径是否存在
        if (!checkPath(path)) {
            return;
        }

        String filePath = path + File.separator + dirName;
        File file = new File(filePath);

        //文件夹已存在
        if (file.exists()) {
            return;
        }

        file.mkdir();
    }

    /**
     * 在指定路径下强制创建指定名称的文件夹
     *
     * @param path    绝对路径
     * @param dirName 文件夹名称
     */
    public static void forceCreateDir(String path, String dirName) {
        String filePath = path + File.separator + dirName;
        File file = new File(filePath);

        //文件夹已存在
        if (file.exists()) {
            return;
        }

        file.mkdirs();
    }

    /**
     *
     *
     * @param path
     */
    public static void createFile(String path) {
        if (checkPath(path)) {
            return;
        }

        File file = new File(path);
        try {
            file.createNewFile();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "createNewFile failed");
        }
    }

    /**
     * 在指定路径下创建指定名称的文件
     *
     * @param path     绝对路径
     * @param fileName 文件名
     */
    public static void createFile(String path, String fileName) {
        //校验指定路径是否存在
        if (!checkPath(path)) {
            return;
        }

        String filePath = path + File.separator + fileName;
        File file = new File(filePath);

        //文件已存在
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "createNewFile failed");
        }
    }

    /**
     * 按行读取文件内容
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<String> readFile(String filePath) {
        BufferedReader bufferedReader = null;
        List<String> dataList = new ArrayList<>();
        try {

            //从文件中读取文件内容
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;

            //按行读取数据
            while ((str = bufferedReader.readLine()) != null) {
                dataList.add(str);
            }
            return dataList;
        } catch (Exception e) {
            LOGGER.error("read file data error, filePath:" + filePath, e);
            return null;
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                LOGGER.error("close reader error, filePath:" + filePath, e);
            }
        }
    }

    /**
     * 将内容写入文件
     *
     * @param file 文件
     * @param data 内容数据
     */
    public static void writeFile(File file, String data) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.flush();
        } catch (Exception e) {
            LogUtil.error(LOGGER, "write file data error, file name: " + file.getName() + ", data: " + data, e);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                LogUtil.error(LOGGER, "close bufferdWriter error, file name: " + file.getName(), e);
            }
        }
    }

    /**
     * 获取文件长度
     *
     * @param file 文件
     * @return
     */
    public static long getFileSize(File file) {
        long fileSize = 0;
        if (file.exists()) {
            fileSize = file.length();
        }
        return fileSize;
    }

    /**
     *
     *
     * @param urlString
     * @param destDir
     * @param fileName
     */
    public static boolean downloadFromUrl(String urlString, String destDir, String fileName) {
        return downloadFromUrl(urlString, destDir, fileName, DEFAULT_OVERTIME, null);
    }

    /**
     *
     *
     * @param urlString
     * @param destDir
     * @param fileName
     * @param overtime
     */
    public static boolean downloadFromUrl(String urlString, String destDir, String fileName, int overtime) {
        return downloadFromUrl(urlString, destDir, fileName, overtime, null);
    }

    /**
     * 从给定URL下载文件到本地目录下的本地文件
     *
     * @param urlString
     * @param destDir
     * @param fileName
     * @param overtime
     * @param requestProperties
     */
    public static boolean downloadFromUrl(String urlString, String destDir, String fileName, int overtime, Map<String, String> requestProperties) {
        long start = System.currentTimeMillis();
        OutputStream outputStream = null;
        URLConnection urlConnection;
        InputStream inputStream = null;
        try {
            //校验文件夹目录，若不存在则立即创建
            if (!checkPath(destDir)) {
                File file = new File(destDir);
                file.mkdirs();
            }

            //创建文件
            createFile(destDir, fileName);
            outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(destDir + File.separator + fileName)));

            URL url = new URL(urlString);
            urlConnection = url.openConnection();
            //设置超时时间
            if (overtime != -1) {
                urlConnection.setConnectTimeout(overtime);
            }
            //设置请求头信息
            if (!CollectionUtils.isEmpty(requestProperties)) {
                for (Map.Entry<String, String> entry : requestProperties.entrySet()) {
                    urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            //输入流
            inputStream = urlConnection.getInputStream();
            //4K的数据缓冲
            byte[] buffer = new byte[BUFFER_SIZE];
            //读取到的数据长度
            int byteReadLength = 0;
            while ((byteReadLength = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteReadLength);
                outputStream.flush();
            }
            ((HttpURLConnection) urlConnection).disconnect();
            long end = System.currentTimeMillis();
            LogUtil.info(LOGGER, "文件下载时长: " + (end - start) + "ms");
            return true;
        } catch (Exception e) {
            LogUtil.error(LOGGER, e.getMessage());
            return false;
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                return true;
            } catch (Exception e) {
                LogUtil.error(LOGGER, e.getMessage());
                return false;
            }
        }
    }

    public static boolean multiThreadDownloadFileFromUrl(String urlString, String destDir, String fileName) throws Exception {
        long startMs = System.currentTimeMillis();

        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        long fileLength = httpURLConnection.getContentLengthLong();

        try {
            //校验文件夹目录
            if (!checkPath(destDir)) {
                File file = new File(destDir);
                file.mkdirs();
            }

            //创建文件
            createFile(destDir, fileName);

            RandomAccessFile randomAccessFile = new RandomAccessFile(destDir + File.separator + fileName, "rwd");
            randomAccessFile.setLength(fileLength);
            randomAccessFile.close();

            int threadNumber = 10;
            long singleThreadReadByteLength = fileLength / threadNumber;

            for (int i = 0; i < threadNumber; i++) {
                long startPos = i * singleThreadReadByteLength;
                long endPos = i == threadNumber - 1 ? fileLength : (i + 1) * singleThreadReadByteLength - 1;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL subUrl = new URL(urlString);
                            HttpURLConnection conn = (HttpURLConnection) subUrl.openConnection();
                            conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
                            if (conn.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                                // 关键响应码 ：206，请求成功 + 请求数据字节范围成功
                                RandomAccessFile file = new RandomAccessFile(destDir + File.separator + fileName, "rwd");
                                file.seek(startPos);
                                // 关键方法 ：每条线程起始写入文件的位置
                                InputStream in = conn.getInputStream();
                                byte[] buf = new byte[8192];
                                int len;
                                while ((len = in.read(buf)) > 0) {
                                    file.write(buf, 0, len);
                                    downloadByteCount += len;
                                }
                                // 关闭网络连接及本地流
                                in.close();
                                file.close();
                                conn.disconnect();
                                System.out.println(Thread.currentThread().getName() + ": download OK");
                            }
                        } catch (Exception e) {

                        }
                    }
                });
                thread.start();
                thread.join();
            }

            if (downloadByteCount == fileLength) {
                System.out.println("下载成功");
                long endMs = System.currentTimeMillis();
                System.out.println(endMs - startMs + "ms");
            } else {
                System.out.println("下载失败");
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

}