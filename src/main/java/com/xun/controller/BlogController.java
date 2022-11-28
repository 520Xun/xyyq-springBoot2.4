package com.xun.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.pojo.BlogUserTypeVo;
import com.xun.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-25 21:51
 */
@RestController
@RequestMapping ( "blog" )
public class BlogController {

    @Autowired
    private BlogService blogServiceImpl;

    /**
     * 查找博文 目前只支持根据标题搜索
     *
     * @param blog
     * @param curPage
     * @param pageSize
     * @return
     */
    @RequestMapping ( "findBlog" )
    public JsonResult findBlog ( BlogUserTypeVo blog, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        JsonResult jr = blogServiceImpl.findBlog ( blog, curPage, pageSize );
        return jr;
    }

    /**
     * 修改推荐
     *
     * @return
     */
    @RequestMapping ( "updateRecommend" )
    public JsonResult updateRecommend ( Integer id, String recommend ) {
        Integer n = blogServiceImpl.updateRecommend ( id, recommend );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    @RequestMapping ( "updateEssayGrade" )
    public JsonResult updateEssayGrade ( Integer id, String essayGrade ) {
        Integer n = blogServiceImpl.updateEssayGrade ( id, essayGrade );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    //    public static void main ( String[] args ) {
//        String springVersion = SpringVersion.getVersion ( );
//        String springBootVersion = SpringBootVersion.getVersion ( );
//        System.out.println ( "Spring版本:" + springVersion + "\nSpringBoot版本:" + springBootVersion );
//    }

    /**
     * |
     * 新增文章
     *
     * @param vo
     * @param tagIds
     * @return
     */
    @RequestMapping ( "saveBlog" )
    public JsonResult insertBlog ( BlogUserTypeVo vo, @RequestParam ( "tagIds[]" ) Integer[] tagIds ) {
        System.out.println ( vo );
        Integer n = blogServiceImpl.insertBlog ( vo, tagIds );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "添加成功！" );
        return jr;
    }
}
