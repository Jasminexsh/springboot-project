package com.jasmine.springboot.utils;

import com.jasmine.springboot.util.FileUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String fileName = "testTalentInfoService.txt";
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
        String fileName = "testTalentInfoService.txt";
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

    @Test
    public void testDownloadFileFromUrl() {
        String urlAddress = "https://t7.baidu.com/it/u=1951548898,3927145&fm=193&f=GIF";
        String destDir = "/Users/xieshanghan/idea_ce_projects/springboot-project/files/pics/";
        String fileName = "pic1.jpg";
        Map<String, String> requestProperties = new HashMap<>();
        requestProperties.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        FileUtil.downloadFromUrl(urlAddress, destDir, fileName);
    }

    @Test
    public void testDownload() throws Exception {
        String url = "https://www.usenix.org/system/files/nsdi22-paper-li_guanyu.pdf";
        String destDir = "/Users/xieshanghan/idea_ce_projects/springboot-project/files";
        String fileName = "ts.pdf";
        FileUtil.multiThreadDownloadFileFromUrl(url, destDir, fileName);
    }

}