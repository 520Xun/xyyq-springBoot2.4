package com.xun.sys.service.impl;

import com.xun.common.pojo.JsonResult;
import com.xun.common.pojo.Pagination;
import com.xun.common.util.Assert;
import com.xun.sys.dao.RoleDao;
import com.xun.sys.pojo.Role;
import com.xun.sys.service.RoleService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version: java version 1.8
 * @Author: xun
 * @description:
 * @date: 2022-11-16 20:50
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    public List< Role > findAllRole ( ) {
        List< Role > list = roleDao.findAllRole ( );
        return list;
    }

    public int insertRole ( String name, String note, String user ) {
        Assert.isEmpty ( name == null || name == "", "角色名为空！" );
        Assert.isEmpty ( note == null || note == "", "角色描述为空！" );
        Assert.isEmpty ( user == null || user == "", "创建用户为空！" );
        int s = roleDao.findInsertByName ( name );
        int n = roleDao.insertRole ( name, note, user );
        Assert.isEmpty ( n == 0, "添加失败！" );
        return n;
    }

    public JsonResult updateFindById ( Integer[] ids ) {
        List< Role > list = roleDao.updateFindById ( ids );
        return new JsonResult ( list );
    }

    public int updateRole ( Integer id, String name, String note, String user ) {
        Assert.isEmpty ( name == null || name == "", "角色名为空！" );
        Assert.isEmpty ( note == null || note == "", "角色描述为空！" );
        Assert.isEmpty ( user == null || user == "", "修改用户为空！" );
        int n = roleDao.updateRole ( id, name, note, user );
        Assert.isEmpty ( n == 0, "修改失败！" );
        return n;
    }

    public int deleteRole ( Integer[] ids ) {
        Assert.isEmpty ( ids.length == 0, "请选择要删除的信息" );
        int n = roleDao.deleteRole ( ids );
        Assert.isEmpty ( n == 0, "数据已被删除" );
        return n;
    }

    public int handlerSaveExcelRole ( MultipartFile file ) {
        try {
            InputStream in = file.getInputStream ( );//获取输入流
            List< Role > roleList = getListRoleExcel ( in );//获取excel所有car对象
            int n = roleDao.insertRoleList ( roleList );//插入数据库
            Assert.isEmpty ( n == 0, "导入失败" );
            return n;
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
        return 0;
    }

    /**
     * 处理导入的数据
     *
     * @param in
     * @return
     */
    private List< Role > getListRoleExcel ( InputStream in ) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
        List< Role > userList = new ArrayList<> ( );
        Workbook workbook = new XSSFWorkbook ( in );
        int sheetCount = workbook.getNumberOfSheets ( );//得到sheet数
        for ( int i = 0; i < sheetCount; i++ ) {
            Sheet sheet = workbook.getSheetAt ( i );//得到sheet
            if ( sheet == null ) {
                continue;
            }
            //获取第一个实际行的下标
            int firstNum = sheet.getFirstRowNum ( );
            //获取总行数
            int lastRowNum = sheet.getLastRowNum ( );
            for ( int j = firstNum + 1; j <= lastRowNum; j++ ) {
                Row row = sheet.getRow ( j );//获取行
                Cell cell = row.getCell ( 0 );//单元格
                int id = ( int ) cell.getNumericCellValue ( );//获取数字
                Cell cell1 = row.getCell ( 1 );
                String roleName = cell1.getStringCellValue ( );
                Cell cell2 = row.getCell ( 2 );
                String note = cell2.getStringCellValue ( );//获取数字
                Cell cell3 = row.getCell ( 3 );
                Date createdTime = cell3.getDateCellValue ( );
                Cell cell4 = row.getCell ( 4 );
                Date modifiedTime = cell4.getDateCellValue ( );
                Cell cell5 = row.getCell ( 5 );
                String createdUser = cell5.getStringCellValue ( );
                Cell cell6 = row.getCell ( 6 );
                String modifiedUser = cell6.getStringCellValue ( );
                Role role = new Role ( null, roleName, note, null, modifiedTime, createdUser, modifiedUser );
                userList.add ( role );
            }
        }
        return userList;
    }

    /**
     * 导出角色
     */
    public int exportsRole ( Integer[] ids ) {
        List< Role > list = roleDao.updateFindById ( ids );
        Workbook workbook = new XSSFWorkbook ( );
        Sheet sheet = workbook.createSheet ( "角色表" );
        Row row = sheet.createRow ( 0 );
        handlerRowTitle ( row );
        for ( int i = 0; i < list.size ( ); i++ ) {
            row = sheet.createRow ( i + 1 );
            handlerRow ( list.get ( i ), row );
        }
        try ( FileOutputStream fos = new FileOutputStream ( "D:\\role.xlsx" ) ) {
            workbook.write ( fos );
            System.out.println ( "导出完毕！" );
        } catch ( Exception e ) {
            e.printStackTrace ( );
        }
        return 0;
    }

    /**
     * 展示到分页列表
     *
     * @param roleName
     * @param curPage
     * @param pageSize
     * @return
     */
    @Override
    public JsonResult FindAllRole ( String roleName, Integer curPage, Integer pageSize ) {
        Assert.isEmpty ( curPage == null || pageSize == null, "查询参数异常！！！" );
        int userCount = roleDao.getRoleCount ( roleName );
        Pagination pageObj = new Pagination ( curPage, userCount, pageSize );
        // 跳过多少条=(当前页-1)*每页条数
        int start = ( pageObj.getCurPage ( ) - 1 ) * pageObj.getPageSize ( );
        System.out.println ( "start:" + start + "," + pageSize );
        List< Role > roles = roleDao.FindAllRole ( roleName, start, pageSize );
        Assert.isEmpty ( roles == null || roles.size ( ) == 0, "数据不存在或已被删除" );
        pageObj.setPageData ( roles );
        return new JsonResult ( pageObj );
    }

    /**
     * 处理表头
     *
     * @param row
     */
    private void handlerRowTitle ( Row row ) {
        Cell cell0 = row.createCell ( 0 );
        cell0.setCellValue ( "id" );
        Cell cell1 = row.createCell ( 1 );
        cell1.setCellValue ( "角色名" );
        Cell cell2 = row.createCell ( 2 );
        cell2.setCellValue ( "角色描述" );
        Cell cell3 = row.createCell ( 3 );
        cell3.setCellValue ( "修改时间" );
        Cell cell4 = row.createCell ( 4 );
        cell4.setCellValue ( "创建时间" );
        Cell cell5 = row.createCell ( 5 );
        cell5.setCellValue ( "修改用户" );
        Cell cell6 = row.createCell ( 6 );
        cell6.setCellValue ( "创建用户" );
    }

    /**
     * 处理行数据
     *
     * @param role
     * @param row
     */
    private void handlerRow ( Role role, Row row ) {
        Cell cell0 = row.createCell ( 0 );
        cell0.setCellValue ( role.getId ( ) );
        Cell cell1 = row.createCell ( 1 );
        cell1.setCellValue ( role.getRoleName ( ) );
        Cell cell2 = row.createCell ( 2 );
        cell2.setCellValue ( role.getNote ( ) );
        Cell cell3 = row.createCell ( 3 );
        cell3.setCellValue ( role.getCreatedTime ( ) );
        Cell cell4 = row.createCell ( 4 );
        cell4.setCellValue ( role.getModifiedTime ( ) );
        Cell cell5 = row.createCell ( 5 );
        cell5.setCellValue ( role.getCreatedUser ( ) );
        Cell cell6 = row.createCell ( 6 );
        cell6.setCellValue ( role.getModifiedUser ( ) );
    }
}
