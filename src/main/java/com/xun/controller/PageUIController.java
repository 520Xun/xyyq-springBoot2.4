package com.xun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicLong;
@Controller
public class PageUIController {
    //该对象利用CPU的算法保证了线程安全
    private final AtomicLong times = new AtomicLong();

    public PageUIController() {
        System.out.println("欢迎访问主页");
    }

    /**
     * 后台首页
     * @return
     */
    @RequestMapping("doIndexUI")
    public String AdminIndexUI() {
        //加1的方法
        long n = times.incrementAndGet();
        System.out.println(n);
        //后台主页
        return "admin/index";
    }

    @RequestMapping("doPageUI")
    public String doPageUI () {
        long n = times.incrementAndGet();
        System.out.println(n);
        return "admin/common/page";
    }

    /**
     * 跳往后台页面
     */
    @RequestMapping("admin/{module}/{page}")
    public String doAdminPageUI(@PathVariable("page") String page,@PathVariable("module") String module) {
        return "admin"+"/"+module  +"/"+ page;
    }



    @RequestMapping("Myblog")
    public String webIndexPageUI() {
        long n = times.incrementAndGet();//加1的方法
        System.out.println(n);
        return "web/index";//前台首页
    }

    /**
     * 跳往博客页面
     */
    public String doWebPageUI(@PathVariable("page") String page,@PathVariable("module")String  module){
        return "web"+"/"+module  +"/"+ page;
    }
}
