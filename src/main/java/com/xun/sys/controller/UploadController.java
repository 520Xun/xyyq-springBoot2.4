package com.xun.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.xun.common.pojo.JsonResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping ( "/upload" )
public class UploadController {

    /**
     * 上传图片（头像）
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping ( value = "/uploadImage", method = RequestMethod.POST )
    public JsonResult uploadFile ( @RequestParam ( "file" ) MultipartFile file ) throws IOException {
        // 如果文件内容不为空，则写入上传路径
        if ( ! file.isEmpty ( ) ) {
            //文件上传的地址
            // String path = ResourceUtils.getURL ("classpath:").getPath () + "static/upload";
            String realPath = "E:/images";
            //String path = getSavePath ();
            //  String realPath = path.replace ('/', '\\').substring (1, path.length ());
            //用于查看路径是否正确
            System.out.println ( realPath );
            // 上传文件路径
            String UploadPath = realPath; //应该放入真实路径
            //取得原文件名字
            String fileName = file.getOriginalFilename ( );
            //取得文件扩展名
            String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );
            //提取系统时间作为新文件名
            String prefix = new SimpleDateFormat ( "yyyyMMddHHmmss" ).format ( new Date ( ).getTime ( ) );
            //保存路径
            // 上传文件名
            String filename = prefix + suffix;
            File filepath = new File ( UploadPath, filename );
            // 判断路径是否存在,没有创建
            if ( ! filepath.getParentFile ( ).exists ( ) ) {
                filepath.getParentFile ( ).mkdirs ( );
            }
            // 将上传文件保存到一个目标文档中
            File file1 = new File ( UploadPath + File.separator + filename );
            file.transferTo ( file1 ); //写入文件
            filename = "/images" + "/" + filename;
            return new JsonResult ( filename );
        } else {
            return null;
        }
    }


    @RequestMapping ( value = "/mackDown/uploadImage" )
    @ResponseBody
    public JSONObject imageUpload ( @RequestParam ( value = "editormd-image-file" ) MultipartFile file, HttpServletResponse response ) throws Exception {
        JSONObject jsonObject = new JSONObject ( );
        try {
            if ( file != null ) {
                //文件上传的地址
                // String path = ResourceUtils.getURL ("classpath:").getPath () + "static/upload";
                String realPath = "E:/images";
                //String path = getSavePath ();
                //  String realPath = path.replace ('/', '\\').substring (1, path.length ());
                //用于查看路径是否正确
                System.out.println ( realPath );
                // 上传文件路径
                String UploadPath = realPath; //应该放入真实路径
                //取得原文件名字
                String fileName = file.getOriginalFilename ( );
                //取得文件扩展名
                String suffix = fileName.substring ( fileName.lastIndexOf ( "." ) );
                //提取系统时间作为新文件名
                String prefix = new SimpleDateFormat ( "yyyyMMddHHmmss" ).format ( new Date ( ).getTime ( ) );
                //保存路径
                // 上传文件名
                String filename = prefix + suffix;
                File filepath = new File ( UploadPath, filename );
                // 判断路径是否存在,没有创建
                if ( ! filepath.getParentFile ( ).exists ( ) ) {
                    filepath.getParentFile ( ).mkdirs ( );
                }
                // 将上传文件保存到一个目标文档中
                File file1 = new File ( UploadPath + File.separator + filename );
                file.transferTo ( file1 ); //写入文件
                filename = "/images" + "/" + filename;
                System.out.println ( filename );
                jsonObject.put ( "url", filename );
                jsonObject.put ( "success", 1 );
                jsonObject.put ( "message", "upload success!" );
                System.out.println ( jsonObject );
                return jsonObject;
            }
        } catch ( Exception e ) {
            jsonObject.put ( "success", 0 );
            jsonObject.put ( "message", "upload error!" );
        }
        //  response.setContentType ( "text/html;charset=UTF-8" );
        return jsonObject;
    }
}
