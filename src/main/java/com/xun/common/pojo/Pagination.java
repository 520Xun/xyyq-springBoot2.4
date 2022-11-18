package com.xun.common.pojo;

import lombok.Data;

import java.util.List;

//分页对象
@Data
public class Pagination {
	private int curPage;//当前页
	private int pageSize;//每页条数
	private int countSize;//总条数
	private int countPage;//总页数

	private List<?> pageData;//分页数据
	/**
	 * 分页数据处理
	 * @param curPage	当前页参数
	 * @param countSize	总条数参数
	 * @param pageSize	每页条数参数
	 */
	public Pagination(int curPage, int countSize, int pageSize) {
		//this.countPage = countSize % pageSize == 0 ? countSize % pageSize : countSize % pageSize + 1;
		this.countPage = (countSize + pageSize - 1) / pageSize;
		this.countSize = countSize;
		this.pageSize = pageSize;
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
		this.pageSize = this.pageSize >= 50 ? 50 : this.pageSize;
		this.curPage = curPage < 1 ? 1 : curPage;
		this.curPage = this.curPage > this.countPage ? this.countPage : this.curPage;
	}
}
