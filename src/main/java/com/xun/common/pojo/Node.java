package com.xun.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//菜单id
	private String name;//菜单名
	private int parentId;//父菜单id
}
