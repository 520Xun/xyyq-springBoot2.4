package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.config.pageProperties;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.util.Assert;
import com.xun.common.util.IPUtils;
import com.xun.sys.dao.CommentDao;
import com.xun.sys.pojo.User;
import com.xun.sys.service.CommentService;
import com.xun.sys.vo.BlogUserTypeVo;
import com.xun.sys.vo.CommentVO;
import com.xun.sys.vo.ParentCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:56
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    //分页
    @Autowired
    private pageProperties pp;

    // 自动导入Java邮件发送实现类
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 查找评论
     *
     * @param comment
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public JsonResult findComment ( CommentVO comment, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< BlogUserTypeVo > page = PageHelper.startPage ( curPage, pageSize );
        List< CommentVO > blogs = commentDao.findComment ( comment );
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( blogs );
        return new JsonResult ( pageObj );
    }

    /**
     * 审核评论
     *
     * @param id
     * @param commentState
     * @return
     */
    @Override
    public Integer updateCommentState ( Integer id, Integer commentState ) {
        Assert.isEmpty ( id == null || commentState == null, "请求参数异常" );
        Integer n = commentDao.updateCommentState ( id, commentState );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    /**
     * 删除评论
     *
     * @param ids
     * @return
     */
    @Override
    public Integer deleteComment ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "至少选择一条数据删除！" );
        int n = commentDao.deleteComment ( ids );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }


    //存放迭代找出的所有子代的集合
    private List< ParentCommentVo > tempReplys = new ArrayList<> ( );

    public List< ParentCommentVo > findParentCommentVoList ( Integer id ) {
        /**
         *  查询父亲
         */
        List< ParentCommentVo > byBlogIdParentIdNull = commentDao.findByBlogIdParentIdNull ( id, 0 );
        byBlogIdParentIdNull.stream ( ).forEach ( e -> {
            List< ParentCommentVo > children = commentDao.findByBlogIdParentIdNotNull ( e.getBlog ( ).getId ( ), e.getId ( ) );
            combineChildren ( e.getBlog ( ).getId ( ), children, e.getUser ( ).getAuthorName ( ) );
            e.setReplyComments ( tempReplys );
            tempReplys = new ArrayList<> ( );
        } );
        return byBlogIdParentIdNull;
    }

    @Override
    public Integer saveComent ( ParentCommentVo comment, HttpSession session ) {
        /**
         *  ( User ) session.getAttribute ( "LoginUser" );
         *   登录的用户信息
         */
        User loginUser = new User ( 39, "axun", "", "", "江景枫" );
        //     Integer userId = loginUser.getId ( ); //用户id
        comment.setUser ( loginUser );
        comment.setCommentIp ( IPUtils.getIpAddr ( ) );
        List< ParentCommentVo > parentCom = null;
        /**
         *  点了回复获取父亲id
         */
        Integer ParentId = comment.getParentComment ( ).getId ( );
        if ( ParentId != null || ParentId > 0 ) {
            comment.setParentId ( ParentId );
            // 根据父评论id查询评论信息
            parentCom = commentDao.findCommentInfoByParentId ( ParentId );
        }
        //插入评论
        Integer saveCommentNum = commentDao.saveComment ( comment );
        /**
         * 更新文章的评论数量（未写）
         */
        /**
         * 判断是否有父评论，有的话就发送邮件
         */
//        parentCom.stream ( ).forEach ( e -> {
//            System.out.println ( e.getUser ( ).getAuthorName ( ) + ":" + e.getId ( ) );
//        } );
//        if ( parentCom != null || parentCom.size ( ) > 0 || comment.getParentId ( ) != null || comment.getParentId ( ) != 0 ) {
//            parentCom.stream ( ).forEach ( e -> {
//                String parentNickname = e.getUser ( ).getAuthorName ( ); //父亲
//                String nickName = comment.getUser ( ).getAuthorName ( );//儿子
//                String comtent = "亲爱的" + parentNickname + "，您在【阿勋博客】的评论收到了来自" + nickName + "的回复！内容如下：" + "\r\n" + "\r\n" + comment.getContant ( );
//                /**
//                 *  已评论人的邮箱
//                 */
//                //     String parentEmail = e.getUser ( ).getEmail ( ); //获取用户的邮箱地址
//                // 发送邮件
//                SimpleMailMessage simpleMailMessage = new SimpleMailMessage ( );
//                simpleMailMessage.setSubject ( "阿勋博客评论回复" );  //主题
//                simpleMailMessage.setText ( comtent );   //内容
//                simpleMailMessage.setTo ( "axun_6@qq.com" ); //接收者的邮箱
//                simpleMailMessage.setFrom ( "axun_6@qq.com" );//发送者邮箱
//                javaMailSender.send ( simpleMailMessage );
//            } );
//        }
        return saveCommentNum;
    }

    @Override
    public Integer deleteCommentByCid ( String id ) {
        Assert.isEmpty ( id == null || id.equals ( "" ), "请求参数异常" );
        commentDao.deleteCommentByReplay ( id );
        int n = commentDao.deleteCommentByCid ( id );
        Assert.isEmpty ( n == 0, "删除失败" );
        return n;
    }

    /**
     * 查询子评论
     *
     * @param blogId
     * @param children
     * @param authorName
     */
    private void combineChildren ( Integer blogId, List< ParentCommentVo > children, String authorName ) {
        //判断是否有一级子评论
        if ( children.size ( ) > 0 ) {
            //循环找出子评论的id
            children.stream ( ).forEach ( e -> {
                String parentNickname = e.getUser ( ).getAuthorName ( );
                e.setParentNickname ( authorName );
                tempReplys.add ( e );
                Integer childId = e.getId ( );
                //查询出子二级评论
                recursively ( blogId, childId, parentNickname );
            } );
        }
    }

    /**
     * 子集回复
     *
     * @param blogId
     * @param childId
     * @param parentNickname1
     */
    private void recursively ( Integer blogId, Integer childId, String parentNickname1 ) {
        //根据子一级评论的id找到子二级评论
        List< ParentCommentVo > replayComments = commentDao.findByBlogIdAndReplayId ( blogId, childId );
        replayComments.stream ( ).forEach ( e -> {
            String praentName3 = e.getUser ( ).getAuthorName ( );//二级评论者名称
            e.setParentNickname ( parentNickname1 );
            tempReplys.add ( e );
            recursively ( blogId, e.getId ( ), praentName3 );
        } );
    }
}
