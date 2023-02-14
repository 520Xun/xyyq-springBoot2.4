package com.xun.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xun.common.config.pageProperties;
import com.xun.common.exception.ServiceException;
import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.pojo.saveExcel;
import com.xun.common.util.Assert;
import com.xun.common.util.IPUtils;
import com.xun.sys.dao.UserDao;
import com.xun.sys.dao.UserRoleDao;
import com.xun.sys.pojo.User;
import com.xun.sys.service.UserService;
import com.xun.sys.vo.countUserAddressVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-11 15:27
 */
@Service
//事务注解
@Transactional (
                isolation = Isolation.READ_COMMITTED,
                timeout = 100,
                rollbackFor = RuntimeException.class,
                readOnly = false )
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private pageProperties pp;

    public JsonResult findUsers ( User user, Integer curPage, Integer pageSize ) {
        //排除非法参数
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        pageSize = pageSize == 0 ? pp.getPageSize ( ) : pageSize;
        Page< User > page = PageHelper.startPage ( curPage, pageSize );
        List< User > users = userDao.findUsers ( user );
        Pagination pageObj = new Pagination ( curPage, ( int ) page.getTotal ( ), pageSize );
        pageObj.setPageData ( users );
        return new JsonResult ( pageObj );
    }

    @Override
    public Integer updateOnlineStatus ( Integer id, Integer onlineStatus ) {
        Assert.isEmpty ( id == null || id == 0 || onlineStatus == null, "请求参数有误！" );
        Integer userStatus = userDao.checkUserStatus ( id );
        Assert.isEmpty ( userStatus == 0, "当前账号已被禁用无法修改！" );
        //对数据的修改者
        String username = "admin";
        Integer n = userDao.updateOnlineStatus ( id, onlineStatus, username );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer updateUserStatus ( Integer id, Integer userStatus ) {
        Assert.isEmpty ( id == null || id == 0 || userStatus == null, "请求参数有误！" );
        String username = "admin";
        Integer n = userDao.updateUserStatus ( id, userStatus, username );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer insertUser ( User user, Integer[] roleIds ) {
        Assert.isEmpty ( user == null, "请填写用户信息！" );
        Assert.isEmpty ( user.getUsername ( ) == null, "必须填写用户名！" );
        Assert.isEmpty ( user.getPassword ( ) == null, "必须填写密码！" );
        Assert.isEmpty ( roleIds == null || roleIds.length == 0, "请至少选择一个角色！" );
        //UUID是uitL的包
        String salt = UUID.randomUUID ( ).toString ( );//加盐，32位的随机盐值
        // 加密方式 原密码 盐 加密次数
        SimpleHash sh = new SimpleHash ( "MD5", user.getPassword ( ), salt, 2 );
        String password = sh.toHex ( );//转为32位的字符串
        user.setPassword ( password );
        user.setSalt ( salt );
        //判断用户是否存在
        User u1 = userDao.findUserByName ( user.getUsername ( ) );
        Assert.isEmpty ( u1 != null, "用户名已存在！" );
        List< User > u2 = userDao.findUserByAuthorUser ( user.getAuthorName ( ) );
        Assert.isEmpty ( u2.size ( ) != 0, "作者名已存在！" );
        String ip = IPUtils.getIpAddr ( );
        user.setRegisterIp ( ip );
        String username = "xun";//创建者
        user.setCreatedUser ( username );
        //插入用户
        int n = userDao.insertUser ( user );
        Assert.isEmpty ( n == 0, "新增用户失败！" );
        //插入用户与角色的关系
        userRoleDao.insertUserRole ( user.getId ( ), roleIds );
        return n;
    }

    @Override
    public Integer updateUser ( User user, Integer[] roleIds ) {
        Assert.isEmpty ( user == null || user.getId ( ) == null, "请选择要修改的用户信息！" );
        Assert.isEmpty ( user.getUsername ( ) == null || user.getUsername ( ).equals ( "" ), "请填写要修改的用户信息！" );
        Assert.isEmpty ( roleIds == null || roleIds.length == 0, "请至少选择一个角色！" );
//        List< User > u2 = userDao.findUserByAuthorUser ( user.getAuthorName ( ) );
//        Assert.isEmpty ( u2.size ( ) != 0, "作者名已存在！" );
        user.setModifiedUser ( "admin" );
        userRoleDao.deteleUserRoleByUserId ( user.getId ( ) );
        userRoleDao.insertUserRole ( user.getId ( ), roleIds );
        int n = userDao.updateUser ( user );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    @Override
    public Integer deleteUser ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要删除的数据" );
        int n = userDao.deleteUseByIds ( ids );
        Assert.isEmpty ( n == 0, "数据已被加入回收站！" );
        return n;
    }

    @Override
    public Integer chealUser ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要删除的数据" );
        int n = userDao.chealUser ( ids );
        Assert.isEmpty ( n == 0, "数据已被删除！" );
        return n;
    }

    @Override
    public Integer recoverUser ( Integer[] ids ) {
        Assert.isEmpty ( ids == null || ids.length == 0, "请选择要恢复的数据" );
        int n = userDao.recoverUser ( ids );
        Assert.isEmpty ( n == 0, "数据已被恢复！" );
        return n;
    }

    @Override
    public void exportAllUser ( ) {
        List< User > list = userDao.exportAllUser ( );
        Workbook workbook = new XSSFWorkbook ( );
        Sheet sheet = workbook.createSheet ( "用户表" );
        Row row = sheet.createRow ( 0 );
        handlerRowTitle ( row );
        for ( int i = 0; i < list.size ( ); i++ ) {
            row = sheet.createRow ( i + 1 );
            handlerRow ( list.get ( i ), row );
        }
        try ( FileOutputStream fos = new FileOutputStream ( "E:\\excel\\user.xlsx" ) ) {
            workbook.write ( fos );
            System.out.println ( "导出完毕！" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
    }

    @Override
    public void exportByUserId ( Integer[] ids ) {
        List< User > list = userDao.exportByUserId ( ids );
        Workbook workbook = new XSSFWorkbook ( );
        Sheet sheet = workbook.createSheet ( "用户表" );
        Row row = sheet.createRow ( 1 );
        handlerRowTitle ( row );
        for ( int i = 0; i < list.size ( ); i++ ) {
            row = sheet.createRow ( i + 1 );
            handlerRow ( list.get ( i ), row );
        }
        try ( FileOutputStream fos = new FileOutputStream ( "E:\\excel\\user.xlsx" ) ) {
            workbook.write ( fos );
            System.out.println ( "导出完毕！" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
    }

    @Override
    public saveExcel handlerSaveExcelUser ( MultipartFile file ) {
        saveExcel se = new saveExcel ( );
        try {
            InputStream in = file.getInputStream ( );//获取输入流
            Map< String, List< ? > > map = getListUserExcel ( in );//获取excel所有car对象
            System.out.println ( map.get ( "userData" ) );
            System.out.println ( map.get ( "numList" ) );
            int n = 0;//插入数据库
            System.out.println ( map.get ( "userData" ).size ( ) );
            if ( map.get ( "userData" ).size ( ) != 0 ) {
                System.out.println ( "确定不插入嘛" );
                n = userDao.insertUserList ( ( List< User > ) map.get ( "userData" ) );
            }
            if ( n == 0 ) {
                throw new ServiceException ( "导入失败！" );
            }
            se.setSuccessNumber ( n );
            se.setErrorNumber ( ( List< Integer > ) map.get ( "numList" ) );
        } catch ( Exception e ) {
            e.printStackTrace ( );
            throw new ServiceException ( "数据导入失败！" );
        }
        return se;
    }

    @Override
    public List< countUserAddressVo > countUserAddress ( ) {
        List< countUserAddressVo > vo = userDao.countUserAddress ( );
        Assert.isEmpty ( vo == null, "当前没有用户！" );
        return vo;
    }

    /**
     * 处理导入的数据
     *
     * @param is
     * @return
     */
    private Map< String, List< ? > > getListUserExcel ( InputStream is ) throws Exception {
        //获取Workbook对象
        Workbook workbook = new XSSFWorkbook ( is );
        //获取有效sheet的数量
        int sheetNumber = workbook.getNumberOfSheets ( );
        List< User > userList = new ArrayList<> ( );
        List< Integer > numList = new ArrayList<> ( );
        for ( int i = 0; i < sheetNumber; i++ ) {
            //获取sheet对象
            Sheet sheet = workbook.getSheetAt ( i );
            //如果sheet表为空则跳过
            if ( sheet == null ) {
                continue;
            }
            //获取初始行号
            int startRowNum = sheet.getFirstRowNum ( );
            //获取结束行号
            int endRowNum = sheet.getLastRowNum ( );
            for ( int j = startRowNum + 1; j <= endRowNum; j++ ) {
                //获取行
                Row row = sheet.getRow ( j );
                Cell cell = row.getCell ( 0 );//单元格
                int id = ( int ) cell.getNumericCellValue ( );//获取数字
                Cell cell1 = row.getCell ( 1 );
                String username = cell1.getStringCellValue ( );
                Cell cell2 = row.getCell ( 2 );
                String authorName = cell2.getStringCellValue ( );//获取数字
                Cell cell3 = row.getCell ( 3 );
                String sex = cell3.getStringCellValue ( );
                Cell cell4 = row.getCell ( 4 );
                String picture = cell4.getStringCellValue ( );
                Cell cell5 = row.getCell ( 5 );
                String email = cell5.getStringCellValue ( );
                Cell cell6 = row.getCell ( 6 );
                String phone = cell6.getStringCellValue ( );
                Cell cell7 = row.getCell ( 7 );
                int onlineStatus = ( int ) cell7.getNumericCellValue ( );
                Cell cell8 = row.getCell ( 8 );
                Date registerTime = cell8.getDateCellValue ( );
                Cell cell9 = row.getCell ( 9 );
                String createIp = cell9.getStringCellValue ( );
                Cell cell10 = row.getCell ( 10 );
                Date modifiedTime = cell10.getDateCellValue ( );
                Cell cell11 = row.getCell ( 11 );
                int userState = ( int ) cell11.getNumericCellValue ( );
                Cell cell12 = row.getCell ( 12 );
                String createUser = cell12.getStringCellValue ( );
                Cell cell13 = row.getCell ( 13 );
                String updateUser = cell13.getStringCellValue ( );
                Cell cell14 = row.getCell ( 14 );
                int isDelete = ( int ) cell14.getNumericCellValue ( );
                Cell cell15 = row.getCell ( 15 );
                String address = cell15.getStringCellValue ( );
                User user = new User ( id, username, "", "", authorName, null, sex, email, phone, picture,
                                "", registerTime, createIp, modifiedTime, address, onlineStatus, userState, createUser, updateUser, isDelete );
                User u = userDao.findUserByNameAndAuthorName ( username, authorName );//查询用户名和作者名是否存在
                if ( u == null && ! userList.contains ( user ) ) {
                    userList.add ( user );
                } else {
                    numList.add ( j + 1 );//重复的数据的行号
                }
            }
        }
        Map< String, List< ? > > map = new HashMap<> ( );
        map.put ( "userData", userList );//需要存入数据库的Car集合
        map.put ( "numList", numList );//重复行号集合
        return map;
    }

    /**
     * 处理行数据
     *
     * @param user
     * @param row
     */
    private void handlerRow ( User user, Row row ) {
        Cell cell0 = row.createCell ( 0 );
        cell0.setCellValue ( user.getId ( ) );
        Cell cell1 = row.createCell ( 1 );
        cell1.setCellValue ( user.getUsername ( ) );
        Cell cell2 = row.createCell ( 2 );
        cell2.setCellValue ( user.getAuthorName ( ) );
        Cell cell3 = row.createCell ( 3 );
        cell3.setCellValue ( user.getSex ( ) );
        Cell cell4 = row.createCell ( 4 );
        cell4.setCellValue ( user.getPicture ( ) );
        Cell cell5 = row.createCell ( 5 );
        cell5.setCellValue ( user.getEmail ( ) );
        Cell cell6 = row.createCell ( 6 );
        cell6.setCellValue ( user.getPhone ( ) );
        Cell cell7 = row.createCell ( 7 );
        cell7.setCellValue ( user.getOnlineStatus ( ) );
        Cell cell8 = row.createCell ( 8 );
        cell8.setCellValue ( user.getRegisterTime ( ) );
        Cell cell9 = row.createCell ( 9 );
        cell9.setCellValue ( user.getRegisterIp ( ) );
        Cell cell10 = row.createCell ( 10 );
        cell10.setCellValue ( user.getModifiedTime ( ) );
        Cell cell11 = row.createCell ( 11 );
        cell11.setCellValue ( user.getUserStatus ( ) );
        Cell cell12 = row.createCell ( 12 );
        cell12.setCellValue ( user.getCreatedUser ( ) );
        Cell cell13 = row.createCell ( 13 );
        cell13.setCellValue ( user.getModifiedUser ( ) );
        Cell cell14 = row.createCell ( 14 );
        cell14.setCellValue ( user.getDeleteState ( ) );
        Cell cell15 = row.createCell ( 15 );
        cell15.setCellValue ( user.getAddress ( ) );
    }

    /**
     * 处理表头
     *
     * @param row
     */
    private void handlerRowTitle ( Row row ) {
        String[] titles = { "id", "用户名", "作者名", "性别", "头像", "邮箱", "手机号", "在线状态(1为在线，0为隐身)", "创建时间", "创建ip", "修改时间", "账号状态(1为正常，0为禁用)", "创建者", "修改者", "回收站(1为正常,2为被回收,0已被删除)", "地址" };
        for ( int i = 0; i < titles.length; i++ ) {//遍历titles数组，拿到里面的值进行操作
            //创建一个单元格
            Cell cell = row.createCell ( i );
            //设置单元格的值
            cell.setCellValue ( titles[ i ] );
        }
    }
}
