package com.xun.dao;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.dao.CommentDao;
import com.xun.sys.vo.CommentVO;
import com.xun.sys.vo.ParentCommentVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-01 16:34
 */
@SpringBootTest
public class CommentVoTest {
    @Autowired
    private CommentDao commentDao;

    // 自动导入Java邮件发送实现类
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 测试邮箱是否能发送
     */
    @Test
    public void sendSimpleMail ( ) {
        String comtent = "亲爱的" + "博主小勋" + "，您在【阿勋博客】的评论收到了来自" + "管理员阿勋" + "的回复！内容如下：" + "\r\n" + "\r\n" + "听我说谢谢你，欢迎您的光顾！";
        //     String parentEmail = parentCom.getUser ( ).getEmail ( ); //获取用户的邮箱地址
        String parentEmail = "axun_6@qq.com";
        // 发送邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage ( );
        simpleMailMessage.setSubject ( "阿勋博客评论回复" );  //主题
        simpleMailMessage.setText ( comtent );   //内容
        simpleMailMessage.setTo ( parentEmail ); //接收者的邮箱
        simpleMailMessage.setFrom ( "axun_6@qq.com" );//发送者邮箱
        javaMailSender.send ( simpleMailMessage );

    }

    @Test
    public void test01 ( ) {
        CommentVO VO = new CommentVO ( );
        List< CommentVO > comment = commentDao.findComment ( VO );
        comment.stream ( ).forEach ( e -> {
            System.out.println ( e );
        } );
    }


    @Test
    public void test02 ( ) {
        for ( Map< String, Object > map : commentDao.findObjects ( ) ) {
            System.out.println ( map );
        }
    }


    //存放迭代找出的所有子代的集合
    private List< ParentCommentVo > tempReplys = new ArrayList<> ( );

    @Test
    public void commentVoTest ( ) {
        /**
         *  查询父亲
         */
        List< ParentCommentVo > byBlogIdParentIdNull = commentDao.findByBlogIdParentIdNull ( 10, 0 );
        byBlogIdParentIdNull.stream ( ).forEach ( e -> {
            System.out.println ( e );
            List< ParentCommentVo > children = commentDao.findByBlogIdParentIdNotNull ( e.getBlog ( ).getId ( ), e.getId ( ) );
            combineChildren ( e.getId ( ), children, e.getUser ( ).getAuthorName ( ) );
            e.setReplyComments ( tempReplys );
            tempReplys = new ArrayList<> ( );
        } );
        JsonResult jr = new JsonResult ( byBlogIdParentIdNull );
        System.out.println ( jr );
    }


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

    private void recursively ( Integer blogId, Integer childId, String parentNickname1 ) {
        //根据子一级评论的id找到子二级评论
        List< ParentCommentVo > replayComments = commentDao.findByBlogIdAndReplayId ( blogId, childId );
        replayComments.stream ( ).forEach ( e -> {
            String praentName3 = e.getUser ( ).getAuthorName ( );//二级评论者名称
            e.setParentNickname ( praentName3 );
            tempReplys.add ( e );
            recursively ( blogId, e.getId ( ), praentName3 );
        } );
    }
}
