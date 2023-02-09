package com.xun.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.config.pageProperties;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.sys.dao.UserDao;
import com.xun.sys.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 14:59
 */
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao dao;

    @Autowired
    private pageProperties pp;

    @Test
    public void findAllUser ( ) {
        User user = new User ( );
        user.setUsername ( "xun" );
        int pageSize = 10;
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        int curPage = 1;
        Page< User > page = PageHelper.startPage ( curPage, pageSize );
        List< User > users = dao.findUsers ( user );
        //page.getTotal()：PageHelper对象的startPage() 会自动查找数据总条数，返回值为Long
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( users );
        JsonResult JR = new JsonResult ( pageObj );
        System.out.println ( JR );
    }
}
