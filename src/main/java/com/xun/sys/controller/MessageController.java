package com.xun.sys.controller;


import com.xun.common.pojo.JsonResult;
import com.xun.sys.pojo.Message;
import com.xun.sys.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Message)表控制层
 *
 * @author xun
 * @since 2023-02-07 09:09:29
 */
@RestController
@RequestMapping ( "message" )
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * 分页查询
     *
     * @param message  筛选条件
     * @param curPage  当前页
     * @param pageSize 页数
     * @return 查询结果
     */
    @RequestMapping ( "queryByPage" )
    public JsonResult queryByPage ( Message message, @RequestParam ( required = false, defaultValue = "1" ) Integer curPage, @RequestParam ( required = false, defaultValue = "5" ) Integer pageSize ) {
        return messageService.queryByPage ( message, curPage, pageSize );
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping ( "{id}" )
    public JsonResult queryById ( @PathVariable ( "id" ) Integer id ) {
        return new JsonResult ( messageService.queryById ( id ) );
    }

    @RequestMapping ( "updateMessageState" )
    public JsonResult updateMessageState ( Integer id, Integer messageState ) {
        Integer n = messageService.updateMessageState ( id, messageState );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( "修改成功！" );
        return jr;
    }

    /**
     * 编辑数据
     *
     * @param message 实体
     * @return 编辑结果
     */
//    @PutMapping
//    public ResponseEntity< Message > edit ( Message message ) {
//        return ResponseEntity.ok ( this.messageService.update ( message ) );
//    }

    /**
     * 删除数据
     *
     * @param ids 主键集合
     * @return 删除是否成功
     */
//    @DeleteMapping
//    public ResponseEntity< Boolean > deleteById ( Integer id ) {
//        return ResponseEntity.ok ( this.messageService.deleteById ( id ) );
//    }
    @RequestMapping ( "deleteMessage" )
    public JsonResult deleteMessage ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = messageService.deleteMessage ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已删除！" );
        return jr;
    }

}

