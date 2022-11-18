package com.xun.common.exception;

import com.xun.common.pojo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局异常处理类
@ControllerAdvice//全局异常处理注解
//@RestControllerAdvice//相当于@ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    //统一处理运行时异常
    @ExceptionHandler (RuntimeException.class)
    //@ResponseBody//把返回值当数据处理
    public String doHandleRuntimeException (RuntimeException e) {
        e.printStackTrace ();//控制台的打印机
        e = new RuntimeException ("服务器繁忙！请联系管理员");
        return "/admin/error/500";
//        ModelAndView mv = new ModelAndView ();
//        mv.setViewName ("500.html");
//        return mv;
    }

    @ExceptionHandler (ServiceException.class)
    @ResponseBody
    public JsonResult handlerServiceException (ServiceException e) {
        return new JsonResult (e);
    }
}
