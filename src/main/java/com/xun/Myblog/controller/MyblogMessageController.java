package com.xun.Myblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xun.common.util.Assert;
import com.xun.sys.service.MessageService;
import com.xun.sys.vo.ParentMessageVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-12-13 20:25
 * 留言控制类
 */
@RequestMapping ( "message" )
@Controller
public class MyblogMessageController {
    @Resource
    private MessageService messageServiceImpl;

    /**
     * 新增留言
     *
     * @param messageVo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping ( "saveMessage" )
    public String saveMessage ( ParentMessageVo messageVo, HttpSession session, Model model, @RequestParam ( defaultValue = "1", value = "pageNum" ) Integer pageNum ) {
        Assert.isEmpty ( messageVo == null, "请求参数异常！" );
        messageServiceImpl.saveMessage ( messageVo, session );
        PageHelper.startPage ( pageNum, 5 );
        List< ParentMessageVo > messageVos = messageServiceImpl.listMessageVoList ( );
        PageInfo< ParentMessageVo > pageInfo = new PageInfo<> ( messageVos );
        model.addAttribute ( "messages", pageInfo );
        return "Myblog/message :: messageList";//局部刷新
    }

    //删除留言
    @RequestMapping ( "/deleteMessage/{userId}/{id}" )
    public String delete ( @PathVariable String userId, @PathVariable String id, HttpSession session, Model model ) {
        /**
         *  是否为在线用户
         */
//        User user = ( User ) session.getAttribute ( "user" );
//        if ( user != null ) {
        messageServiceImpl.deleteMessageByIdAndUserId ( id, userId );
//        }
        PageHelper.startPage ( 1, 2 );
        List< ParentMessageVo > messageVos = messageServiceImpl.listMessageVoList ( );
        PageInfo< ParentMessageVo > pageInfo = new PageInfo<> ( messageVos );
        model.addAttribute ( "messages", pageInfo );
        return "Myblog/message";
    }

}
