package com.xun.sys.controller;

import com.xun.common.pojo.JsonResult;
import com.xun.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController//相当于@Controller + @ResponseBody
@RequestMapping ( "/sysLog" )
public class SysLogController {

    @Autowired
    private SysLogService service;

    @RequestMapping ( "findLogObjects" )
    public JsonResult findLogObjects ( String username, String operation, @RequestParam ( defaultValue = "5" ) Integer pageSize, @RequestParam ( defaultValue = "1" ) Integer curPage ) {
        return service.findLogByUsername ( username, operation, curPage, pageSize );
    }

    @RequestMapping ( "deleteLog" )
    public JsonResult doDeleteLog ( @RequestParam ( "ids[]" ) Integer[] ids ) {
        int n = service.doDeleteLog ( ids );
        JsonResult jr = new JsonResult ( n );
        jr.setMsg ( n + "条数据已删除！" );
        return jr;
    }
}
