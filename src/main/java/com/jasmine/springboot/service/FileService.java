package com.jasmine.springboot.service;

/**
 * 文件服务
 *
 * @author xieshanghan
 * @version FileService.java, v 0.1 2023年02月16日 14:43 xieshanghan
 */
public interface FileService {

    /**
     * 从url下载文件并保存在本地
     *
     * @param url
     * @param destDir
     * @param fileName
     * @return
     */
    boolean download(String url, String destDir, String fileName);

    boolean multiThreadDownload(String url, String destDir, String fileName);

}
