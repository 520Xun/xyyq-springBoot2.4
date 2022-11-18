package com.xun.common.pojo;

import lombok.Data;

@Data
//转json工具包
public class JsonResult {
	private Integer state = 1;//状态，1：ok	0：no
	private Object data;//成功数据
	private String msg;//异常文本描述
	public JsonResult(Throwable e) {
		this.state = 0;//状态码改为0
		this.msg = e.getMessage();
	}
	public JsonResult(Object data) {
		this.data = data;
	}
}
