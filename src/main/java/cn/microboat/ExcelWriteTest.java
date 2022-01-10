package cn.microboat;

import cn.microboat.pojo.InventoryDetails;
import cn.microboat.pojo.RSVO;
import cn.microboat.pojo.SubArrayVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouwei
 */
public class ExcelWriteTest {

    final String PATH = "/Users/zhouwei/Projects/IdeaProjects/POIDemo";

    @Test
    public void test03() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("03统计");
        Row row1 = sheet.createRow(0);
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日新增观众");
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue(30);

        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "03统计.xls");
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();

    }

    @Test
    public void test07() throws IOException {

        List<InventoryDetails> list = new LinkedList<>();


        for (int i = 0; i < 100; i++) {
            InventoryDetails inventoryDetails = new InventoryDetails();
            inventoryDetails.setSerialNumber(i+1);
            inventoryDetails.setCode("ST"+"20211110"+(i+1));
            inventoryDetails.setOrderNumber("0000"+(i+1));
            inventoryDetails.setMaterialType("生活物资");
            inventoryDetails.setMaterialName("毛巾"+(i+1));
            inventoryDetails.setUnitName("条");
            inventoryDetails.setDept("警察总局");
            inventoryDetails.setStoreZone("A区");
            inventoryDetails.setOldNumber(10*i);
            inventoryDetails.setNewNumber(10*i+10);
            inventoryDetails.setUpdateTime(new DateTime());
            inventoryDetails.setUpdater("manager");
            inventoryDetails.setNote("备注"+i);
            list.add(inventoryDetails);
        }

        for (InventoryDetails details : list) {
            System.out.println(details);
        }

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("测试");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("序号");
        row0.createCell(1).setCellValue("编码");
        row0.createCell(2).setCellValue("凭单编号");
        row0.createCell(3).setCellValue("资源类型");
        row0.createCell(4).setCellValue("物品名称");
        row0.createCell(5).setCellValue("单位");
        row0.createCell(6).setCellValue("所属部门");
        row0.createCell(7).setCellValue("库存区域");
        row0.createCell(8).setCellValue("原库存数量");
        row0.createCell(9).setCellValue("新库存数量");
        row0.createCell(10).setCellValue("记录时间");
        row0.createCell(11).setCellValue("经手人");
        row0.createCell(12).setCellValue("备注");

        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(list.get(i).getSerialNumber());
            row.createCell(1).setCellValue(list.get(i).getCode());
            row.createCell(2).setCellValue(list.get(i).getOrderNumber());
            row.createCell(3).setCellValue(list.get(i).getMaterialType());
            row.createCell(4).setCellValue(list.get(i).getMaterialName());
            row.createCell(5).setCellValue(list.get(i).getUnitName());
            row.createCell(6).setCellValue(list.get(i).getDept());
            row.createCell(7).setCellValue(list.get(i).getStoreZone());
            row.createCell(8).setCellValue(list.get(i).getOldNumber());
            row.createCell(9).setCellValue(list.get(i).getNewNumber());
            row.createCell(10).setCellValue(list.get(i).getUpdateTime().toString("yyyy-MM-dd HH:mm:ss"));
            row.createCell(11).setCellValue(list.get(i).getUpdater());
            row.createCell(12).setCellValue(list.get(i).getNote());
        }

        Row row1 = sheet.createRow(list.size()+1);
        row1.createCell(0).setCellValue("汇总");

        Row row2 = sheet.createRow(list.size()+2);


        List<RSVO> rsvoList = new LinkedList<>();

        RSVO rsvo = new RSVO();
        rsvo.setTypeName("帐篷");
        rsvo.setTotal(100.0);
        List<SubArrayVO> lists = new LinkedList<>();
        lists.add(new SubArrayVO("顶", 50.0));
        lists.add(new SubArrayVO("篷", 50.0));
        rsvo.setSubArray(lists);
        rsvoList.add(rsvo);

        rsvo = new RSVO();
        rsvo.setTypeName("方便面");
        rsvo.setTotal(200.0);
        List<SubArrayVO> lists1 = new LinkedList<>();
        lists1.add(new SubArrayVO("箱", 200.0));
        rsvo.setSubArray(lists1);
        rsvoList.add(rsvo);

        StringBuilder stringBuilder = new StringBuilder();

        for (RSVO rsvo1 : rsvoList) {
            stringBuilder.append(rsvo1.getTypeName()).append(" —— ");
            for (SubArrayVO subArrayVO : rsvo1.getSubArray()) {
                stringBuilder.append(subArrayVO.getSubtotal()).append(" ").append(subArrayVO.getUnitName()).append("；");
            }
            stringBuilder.append("  ");
        }

        System.out.println(stringBuilder);


        String str = "帐篷——100顶  方便面——55箱  饮用水——10箱/24";
        row2.createCell(2).setCellValue(stringBuilder.toString());

        CellRangeAddress cellAddresses = new CellRangeAddress(list.size()+1, list.size()+1, 0, 1);
        sheet.addMergedRegion(cellAddresses);
        cellAddresses = new CellRangeAddress(list.size()+2, list.size()+2, 2, 12);
        sheet.addMergedRegion(cellAddresses);


        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "盘点明细.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();


//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("07统计");
//        Row row1 = sheet.createRow(0);
//        Cell cell11 = row1.createCell(0);
//        cell11.setCellValue("今日新增观众");
//        Cell cell12 = row1.createCell(1);
//        cell12.setCellValue(30);
//
//        Row row2 = sheet.createRow(1);
//        Cell cell21 = row2.createCell(0);
//        cell21.setCellValue("统计时间");
//        Cell cell22 = row2.createCell(1);
//        cell22.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
//
//        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "07统计.xls");
//        workbook.write(fileOutputStream);
//
//        fileOutputStream.close();
//        workbook.close();

    }
}
