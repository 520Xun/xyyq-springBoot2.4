package com.xun.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicLong;

@Controller
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
        model.addAttribute ( "text", content );
        return "admin/essay/essayEdit";
    }

    /**
     * 审批文章
     *
     * @return
     */
    @RequestMapping ( "admin/essay/blogHtml" )
    public String blogInfo ( Model model ) {
        model.addAttribute ( "comment", "今天看到群里有小伙伴问，这个异常要怎么解决：\n" +
                        "```java\n" +
                        "java.lang.IllegalArgumentException: Request header is too large\n" +
                        "```\n" +
                        "#### 异常原因\n" +
                        "\n" +
                        "##### 根据Exception MessageRequest header is too large，就可以判断这个错误原因是HTTP请求头过大导致的。\n" +
                        "\n" +
                        "#### 如何解决\n" +
                        "\n" +
                        "##### 解决方法主要两个方向：\n" +
                        "\n" +
                        "#### 方向一：配置应用服务器使其允许的最大值 > 你实用实用的请求头数据大小\n" +
                        "\n" +
                        "##### 如果用Spring Boot的话，只需要在配置文件里配置这个参数即可：\n" +
                        "    server.max-http-header-size=\n" +
                        "#### 方向二：规避请求头过大的情况\n" +
                        "\n" +
                        "##### 虽然上面的配置可以在解决，但是如果无节制的使用header部分，那么这个参数就会变得很不可控。你来一段，他来一段，为了适配不出错，还得求个并集的最大值，保证万无一失...即便如此，未来可能还得扩...\n" +
                        "\n" +
                        "##### 所以，对于请求头部分的数据还是不建议放太大的数据，建议把这些数据放到body里更为合理。这是我的建议，那么在读的各位都是如何处理的呢？留言说说你认为最好的处理方式吧？\n" +
                        "![](http://localhost/images/20221124164904.jpg)" );
        return "admin/essay/blogHtml";
    }

    @RequestMapping ( "Myblog" )
    public String webIndexPageUI ( ) {
        long n = times.incrementAndGet ( );//加1的方法
        System.out.println ( n );
        return "web/index";//前台首页
    }

    /**
     * 跳往博客页面
     */
    public String doWebPageUI ( @PathVariable ( "page" ) String page, @PathVariable ( "module" ) String module ) {
        return "web" + "/" + module + "/" + page;
    }
}
