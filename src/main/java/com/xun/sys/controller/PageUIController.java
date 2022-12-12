package com.xun.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping ( "/" )
public class PageUIController {
    //该对象利用CPU的算法保证了线程安全
    private final AtomicLong times = new AtomicLong ( );

    public PageUIController ( ) {
        System.out.println ( "欢迎访问主页" );
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping ( "doIndexUI" )
    public String AdminIndexUI ( ) {
        //加1的方法
        long n = times.incrementAndGet ( );
        System.out.println ( n );
        //后台主页
        return "admin/index";
    }

    @RequestMapping ( "/404" )
    public String error404 ( ) {
        return "admin/error/404";
    }

    /**
     * 加载分页组件
     *
     * @return
     */
    @RequestMapping ( "doPageUI" )
    public String doPageUI ( ) {
        long n = times.incrementAndGet ( );
        System.out.println ( n );
        return "admin/common/page";
    }

    /**
     * 跳往后台页面
     */
    @RequestMapping ( "admin/{module}/{page}" )
    public String doAdminPageUI ( @PathVariable ( "page" ) String page, @PathVariable ( "module" ) String module ) {
        return "admin" + "/" + module + "/" + page;
    }

    /**
     * 编辑文章内容回显
     *
     * @param model
     * @param content
     * @return
     */
    @RequestMapping ( "admin/essay/essayEdit/{content}" )
    public String blogEditPageUI ( Model model, @PathVariable ( "content" ) String content ) {
        model.addAttribute ( "content", content );
        return "admin/essay/essayEdit";
    }


}
