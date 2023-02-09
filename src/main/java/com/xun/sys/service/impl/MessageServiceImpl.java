package com.xun.sys.service.impl;

//自动导包设置

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.config.pageProperties;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.util.Assert;
import com.xun.common.util.IPUtils;
import com.xun.sys.dao.MessageDao;
import com.xun.sys.pojo.Message;
import com.xun.sys.pojo.User;
import com.xun.sys.service.MessageService;
import com.xun.sys.vo.ParentMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author xun
 * @since 2023-02-07 09:12:18
 */
@Service ( "messageService" )
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Resource
    private pageProperties pp;//分页默认数据

    //存放迭代找出的所有子代的集合
    private List< ParentMessageVo > tempReplys = new ArrayList<> ( );

    /**
     * 查询留言 (已审批）
     *
     * @return
     */
    @Override
    public List< ParentMessageVo > listMessageVoList ( ) {
        List< ParentMessageVo > listMes = messageDao.findByParentIdNotNull ( 0 );
        listMes.stream ( ).forEach ( e -> {
            List< ParentMessageVo > children = messageDao.findByParentIdNotNull ( e.getId ( ) );
            combineChildren ( children, e.getUser ( ).getAuthorName ( ) );
            e.setReplyMessages ( tempReplys );
            tempReplys = new ArrayList<> ( );
        } );
        return listMes;
    }

    @Override
    public Integer saveMessage ( ParentMessageVo messageVo, HttpSession session ) {
        /**
         *  ( User ) session.getAttribute ( "LoginUser" );
         *   登录的用户信息
         */
        User loginUser = new User ( 39, "axun", "", "", "江景枫" );
        //     Integer userId = loginUser.getId ( ); //用户id
        messageVo.setUser ( loginUser );
        messageVo.setMessageIp ( IPUtils.getIpAddr ( ) );
        List< ParentMessageVo > parentCom = null;
        /**
         *  点了回复获取父亲id
         */
        Integer ParentId = messageVo.getParentMessage ( ).getId ( );
        if ( ParentId != null || ParentId > 0 ) {
            messageVo.setParentMessageId ( ParentId );
            // 根据父评论id查询评论信息
            parentCom = messageDao.findByParentIdNotNull ( ParentId );
        }
        //插入评论
        Integer saveMessage = messageDao.saveMessage ( messageVo );
        Assert.isEmpty ( saveMessage == 0, "添加失败！" );
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
//                simpleMailMessage.setSubject ( "阿勋博客留言回复" );  //主题
//                simpleMailMessage.setText ( comtent );   //内容
//                simpleMailMessage.setTo ( "axun_6@qq.com" ); //接收者的邮箱
//                simpleMailMessage.setFrom ( "axun_6@qq.com" );//发送者邮箱
//                javaMailSender.send ( simpleMailMessage );
//            } );
//        }
        return saveMessage;
    }

    @Override
    public Integer deleteMessageByIdAndUserId ( String id, String userId ) {
        Assert.isEmpty ( id == null || id.equals ( "0" ) || userId == null || userId.equals ( "0" ), "请求参数异常！" );
        messageDao.deleteMessageByReplay ( id );
        Integer delInteger = messageDao.deleteMessageByIdAndUserId ( id, userId );
        Assert.isEmpty ( delInteger == 0, "删除失败！" );
        return delInteger;
    }

    /**
     * 查询子集留言
     *
     * @param children
     * @param authorName
     */
    private void combineChildren ( List< ParentMessageVo > children, String authorName ) {
        if ( children.size ( ) > 0 ) {
            children.stream ( ).forEach ( e -> {
                String parentNickname = e.getUser ( ).getAuthorName ( );
                e.setParentNickname ( authorName );
                tempReplys.add ( e );
                Integer childrenId = e.getId ( );
                //查找二级留言
                recursively ( childrenId, parentNickname );
            } );
        }
    }

    /**
     * 查找二级留言
     *
     * @param childrenId
     * @param parentNickname
     */
    private void recursively ( Integer childrenId, String parentNickname ) {
        //根据子一级评论的id找到子二级评论
        List< ParentMessageVo > replayments = messageDao.findByParentIdNotNull ( childrenId );
        replayments.stream ( ).forEach ( e -> {
            String praentName3 = e.getUser ( ).getAuthorName ( );//二级评论者名称
            e.setParentNickname ( parentNickname );
            tempReplys.add ( e );
            recursively ( e.getId ( ), praentName3 );
        } );
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Message queryById ( Integer id ) {
        return this.messageDao.queryById ( id );
    }

    /**
     * 分页查询
     *
     * @param message  筛选条件
     * @param curPage  当前页
     * @param pageSize 页数
     * @return 查询结果
     */
    @Override
    public JsonResult queryByPage ( Message message, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< ParentMessageVo > page = PageHelper.startPage ( curPage, pageSize );
        List< ParentMessageVo > lists = messageDao.queryByPage ( message );
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( lists );
        return new JsonResult ( pageObj );
    }


    /**
     * 修改数据
     *
     * @param message 实例对象
     * @return 实例对象
     */
    @Override
    public Message update ( Message message ) {
        this.messageDao.update ( message );
        return this.queryById ( message.getId ( ) );
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById ( Integer id ) {
        return this.messageDao.deleteById ( id ) > 0;
    }

    @Override
    public Integer updateMessageState ( Integer id, Integer messageState ) {
        Assert.isEmpty ( id == null || messageState == null, "请求参数异常" );
        Integer n = messageDao.updateMessageState ( id, messageState );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer deleteMessage ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "至少选择一条数据删除！" );
        int n = messageDao.deleteMessage ( ids );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }
}
