package com.example.mybatisplus.service.impl;


import com.example.mybatisplus.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMM");

    @Value("${file-upload-path}")
    private String fileUploadPath;

    @Override
    public Map upload(MultipartFile file) throws IOException {
        Map<String, String> map = storeFile(file, Paths.get(fileUploadPath, "image").toString());
        return map;
    }

    private static Map<String, String> storeFile(MultipartFile file, String fileUploadPath) throws IOException {

        String yearMonth = SDF.format(new Date());//当前年月
        //String random = "" + (int) (Math.random() * 1000);//随机4位数,没补0
        String fileName = file.getOriginalFilename();//文件全名
        String suffix = suffix(fileName);//文件后缀
        String relPath = "/" + yearMonth + "/" + "-" + UUID.randomUUID().toString().replaceAll("-","") + suffix;
        String toPath = fileUploadPath + relPath;
        FileOutputStream out = null;

        File toFile = new File(toPath).getParentFile();
        if (!toFile.exists()) {
            toFile.mkdirs(); //自动创建目录
        }
        try {
            out = new FileOutputStream(toPath);
            out.write(file.getBytes());
            out.flush();
            Map<String, String> map = new HashMap();
            map.put("url", "./image" + relPath);
            log.info(relPath);
            return map;
        } catch (FileNotFoundException fnfe) {
            throw fnfe;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 后缀名或empty："a.png" => ".png"
     */
    private static String suffix(String fileName) {
        int i = fileName.lastIndexOf('.');
        return i == -1 ? "" : fileName.substring(i);
    }
}
