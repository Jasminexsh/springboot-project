package com.jasmine.springboot.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

/**
 * @author xieshanghan
 * @version FileUtilTests.java, v 0.1 2023年02月14日 23:32 xieshanghan
 */
public class FileUtilTests {

    private static final String FILE_SEPERATOR = "/";

    @Test
    public void testCheckPathValidation() {
        String correctPath = "/Users/xieshanghan/idea_ce_projects/springboot-project";
        String wrongPath = "/Users/xieshanghan/idea_ce_projects/springboot";
        boolean correctResult = FileUtil.checkPath(correctPath);
        boolean wrongResult = FileUtil.checkPath(wrongPath);
        Assert.assertTrue(correctResult);
        System.out.println("correctResult: " + correctResult);
        Assert.assertFalse(wrongResult);
        System.out.println("wrongResult: " + wrongResult);
    }

    @Test
    public void testCreateDir() {
        String validPath = "/Users/xieshanghan/idea_ce_projects/springboot-project";
        String invalidPath = "/Users/xieshanghan/idea_ce_projects/springboot-project/invalid";
        String existentDirName = "files";
        String nonexistentDirName = "testFiles";
        FileUtil.createDir(validPath, existentDirName);
        FileUtil.createDir(invalidPath, existentDirName);
        FileUtil.createDir(validPath, nonexistentDirName);
        FileUtil.createDir(invalidPath, nonexistentDirName);
    }

    @Test
    public void testForceCreateDir() {
        String validPath = "/Users/xieshanghan/idea_ce_projects/springboot-project";
        String invalidPath = "/Users/xieshanghan/idea_ce_projects/springboot-project/invalid";
        String existentDirName = "files";
        String nonexistentDirName = "testFiles";
        FileUtil.forceCreateDir(validPath, existentDirName);
        FileUtil.forceCreateDir(validPath, nonexistentDirName);
        FileUtil.forceCreateDir(invalidPath, nonexistentDirName);
    }

    @Test
    public void testCreateFile() {
        String path = "/Users/xieshanghan/idea_ce_projects/springboot-project/files";
        String fileName = "test.txt";
        FileUtil.createFile(path, fileName);
    }

    @Test
    public void testReadFile() {
        String path = "/Users/xieshanghan/Desktop/trouble_strategy_uuid.txt";
        List<String> dataList = FileUtil.readFile(path);
        System.out.println(dataList);
    }

    @Test
    public void testWriteFile() {
        String dirName = "/Users/xieshanghan/idea_ce_projects/springboot-project/files";
        String fileName = "test.txt";
        String absolutePath = dirName + FILE_SEPERATOR + fileName;

        File file = new File(absolutePath);
        String data = "测试";
        FileUtil.writeFile(file, data);
    }

    @Test
    public void testGetFileSize() {
        File file = new File("/Users/xieshanghan/Desktop/trouble_strategy_uuid.txt");
        long fileSize = FileUtil.getFileSize(file);
        System.out.println(fileSize);
    }

}