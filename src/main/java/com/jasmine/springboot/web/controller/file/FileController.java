package com.jasmine.springboot.web.controller.file;

import com.jasmine.springboot.common.model.BaseResponse;
import com.jasmine.springboot.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xieshanghan
 * @version FileController.java, v 0.1 2023年02月16日 10:09 xieshanghan
 */
@Controller
@RequestMapping("/api/v1/file")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    private static final int BUFFER_SIZE = 1024;

    private static final int OVERTIME = 50 * 1000;

    private static final String DEFAULT_SUCCESS_MESSAGE = "成功";

    private static final String DEFAULT_FAIL_MESSAGE = "失败";

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<?> downloadFile(String url, String destDir, String fileName) {
        boolean downloadSuccess = FileUtil.downloadFromUrl(url, destDir, fileName);
        if (downloadSuccess) {
            return BaseResponse.builder().success(true).message(DEFAULT_SUCCESS_MESSAGE).build();
        }
        return BaseResponse.builder().success(false).message(DEFAULT_FAIL_MESSAGE).build();
    }

//    @RequestMapping("/download1")
//    @ResponseBody
//    public void download(String filePath,String savePath) {
//        try {
//            DownloadUtil downloadUtil = new DownloadUtil (filePath,savePath,10);
//            downloadUtil.download();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

}