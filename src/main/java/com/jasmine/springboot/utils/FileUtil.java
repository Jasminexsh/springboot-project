package com.jasmine.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author xieshanghan
 * @version FileUtil.java, v 0.1 2023年02月15日 13:07 xieshanghan
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

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
            LogUtils.error(LOGGER, "createNewFile failed");
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
            LogUtils.error(LOGGER, "write file data error, file name: " + file.getName() + ", data: " + data, e);
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                LogUtils.error(LOGGER, "close bufferdWriter error, file name: " + file.getName(), e);
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

}