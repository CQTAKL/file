package com.example.controller;

import com.example.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        String path = this.uploadService.uploadImage(file);
        map.put("path",path);
        return map;
    }
}
