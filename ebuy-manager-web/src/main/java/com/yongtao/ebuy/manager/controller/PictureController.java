package com.yongtao.ebuy.manager.controller;


import com.yongtao.ebuy.util.fdfs.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController
{
    @Value("${FastDFS_server}")
    private String FastDFS_server;


    @RequestMapping("/pic/upload")
    @ResponseBody
    public Map uploadFile(MultipartFile uploadFile) {
        Map map = new HashMap();

        FastDFSClient client = new FastDFSClient();
        String fileName = uploadFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            String path = client.uploadFile(uploadFile.getBytes(), ext);
            map.put("error", 0);
            map.put("url", "http://" + FastDFS_server + "/" + path);
        } catch (Exception e) {
            map.put("error", 1);
            map.put("message", "上传出错!");
            e.printStackTrace();
        }
        return map;
    }
}
