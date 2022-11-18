package com.xun.controller;

import com.xun.common.pojo.JsonResult;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description: 文件上传控制类
 * @date: 2022-11-17 9:28
 */
@RestController
@RequestMapping ("/upload")
public class UploadController {

    /**
     * 上传图片（头像）
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping (value = "/uploadImage", method = RequestMethod.POST)
    public JsonResult uploadFile (@RequestParam ("file") MultipartFile file) throws IOException {
        // 如果文件内容不为空，则写入上传路径
        if (! file.isEmpty ()) {
            //文件上传的地址
            String path = ResourceUtils.getURL ("classpath:").getPath () + "static/upload";
            //String path = getSavePath ();
            String realPath = path.replace ('/', '\\').substring (1, path.length ());
            //用于查看路径是否正确
            System.out.println (realPath);
            // 上传文件路径
            String UploadPath = realPath; //应该放入真实路径
            //取得原文件名字
            String fileName = file.getOriginalFilename ();
            //取得文件扩展名
            String suffix = fileName.substring (fileName.lastIndexOf ("."));
            //提取系统时间作为新文件名
            String prefix = new SimpleDateFormat ("yyyyMMddHHmmss").format (new Date ().getTime ());
            //保存路径
            // 上传文件名
            String filename = prefix + suffix;
            File filepath = new File (UploadPath, filename);
            // 判断路径是否存在,没有创建
            if (! filepath.getParentFile ().exists ()) {
                filepath.getParentFile ().mkdirs ();
            }
            // 将上传文件保存到一个目标文档中
            File file1 = new File (UploadPath + File.separator + filename);
            file.transferTo (file1); //写入文件
//            Map<String, Object> res = new HashMap<> ();
//            // 返回的是一个url对象,图片名称
//            res.put ("url", filename);
            filename = "/upload" + "/" + filename;
            return new JsonResult (filename);
        } else {
            return null;
        }
    }
}
