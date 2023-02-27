package com.jasmine.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩/解压缩工具类
 *
 * @author xieshanghan
 * @version ZipUtil.java, v 0.1 2023年02月14日 20:58 xieshanghan
 */
public class ZipUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtil.class);

    /** BUFFER字节大小 */
    public static final int BUFFER = 4096;

    public static void compress(File file, String fileName, ZipOutputStream out) {
        if (file.isFile()) {
            FileInputStream fi = null;
            BufferedInputStream origin = null;
            try {
                fi = new FileInputStream(file);
                origin = new BufferedInputStream(fi, BUFFER);
                int index = file.getAbsolutePath().indexOf(fileName);
                String entryName = file.getAbsolutePath().substring(index);
                ZipEntry entry = new ZipEntry(entryName);
                out.putNextEntry(entry);
                byte[] data = new byte[BUFFER];
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("compress file error,file:" + file.getName() + ",mainFileName:"
                        + fileName, e);
            } catch (IOException e) {
                LOGGER.error("compress file error,file:" + file.getName() + ",mainFileName:"
                        + fileName, e);
            } finally {
                if (origin != null) {
                    try {
                        origin.close();
                    } catch (IOException e) {
                        LOGGER.error("close file error,file:" + file.getName() + ",mainFileName:"
                                + fileName, e);
                    }
                }
            }
        } else if (file.isDirectory()) {
            File[] fs = file.listFiles();
            if (fs != null && fs.length > 0) {
                for (File f : fs) {
                    compress(f, fileName, out);
                }
            }
        }
    }

}
