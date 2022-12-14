package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Type;
import com.xun.sys.pojo.User;
import com.xun.sys.service.BlogService;
import com.xun.sys.vo.BlogUserTypeVo;
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

    @RequestMapping ( "countBlogType" )
    public JsonResult countBlogType ( ) {
        return new JsonResult ( blogServiceImpl.countBlogType ( ) );
    }

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
        return blogServiceImpl.findBlog ( blog, curPage, pageSize );
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

    /**
     * 审核文章
     *
     * @param id
     * @param essayStatus
     * @return
     */
    @RequestMapping ( "updateEssayStatus" )
    public JsonResult updateEssayStatus ( Integer id, Integer essayStatus, Integer recommend ) {
        Integer n = blogServiceImpl.updateEssayStatus ( id, essayStatus, recommend );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 修改评分
     *
     * @param id
     * @param essayGrade
     * @return
     */
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
    public JsonResult insertBlog ( BlogUserTypeVo vo, @RequestParam ( "tagIds[]" ) Integer[] tagIds, String authorName, Integer typeId ) {
        User user = new User ( authorName );
        vo.setUser ( user );
        Type type = new Type ( typeId );
        vo.setType ( type );
        Integer n = blogServiceImpl.insertBlog ( vo, tagIds );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "添加成功！" );
        return jr;
    }

    /**
     * 修改文章
     *
     * @param vo
     * @param tagIds
     * @param authorName
     * @param typeId
     * @return
     */
    @RequestMapping ( "updateBlog" )
    public JsonResult updateUser ( BlogUserTypeVo vo, @RequestParam ( "tagIds[]" ) Integer[] tagIds, String authorName, Integer typeId ) {
        User user = new User ( authorName );
        vo.setUser ( user );
        Type type = new Type ( typeId );
        vo.setType ( type );
        Integer n = blogServiceImpl.updateBlog ( vo, tagIds );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 加入回收站
     *
     * @param ids
     * @return
     */
    @RequestMapping ( "deleteBlog" )
    public JsonResult deleteUser ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = blogServiceImpl.deleteBlog ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已加入回收站！" );
        return jr;
    }

    /**
     * 恢复用户
     *
     * @return
     */
    @RequestMapping ( "recoverBlog" )
    public JsonResult recoverUser ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = blogServiceImpl.recoverBlog ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已恢复！" );
        return jr;
    }

    /**
     * 彻底删除博文
     *
     * @param ids
     * @return
     */
    @RequestMapping ( "chealBlog" )
    public JsonResult chealUser ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = blogServiceImpl.chealBlog ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已彻底删除！！！" );
        return jr;
    }

}
