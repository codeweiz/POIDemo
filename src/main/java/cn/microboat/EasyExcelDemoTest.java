package cn.microboat;

import cn.microboat.pojo.DemoData;
import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhouwei
 */
public class EasyExcelDemoTest {

    final String PATH = "/Users/zhouwei/Projects/IdeaProjects/POIDemo";

    /**
     * @return List
     * description 返回一个列表
     * */
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setString("字符串" + i);
            demoData.setDate(new Date());
            demoData.setDoubleData(0.56);
            demoData.setIgnore("ignore" + i);
            list.add(demoData);
        }
        return list;
    }

    @Test
    public void test01() {
        for (DemoData demoData : data()) {
            System.out.println(demoData);
        }
    }

    @Test
    public void simpleWrite() {
        String fileName = PATH + "easyexcel.xlsx";

        // write 方法传入 文件名 和 实体类class，文件名包含文件路径
        // sheet 方法传入 sheet名
        // doWrite 方法 传入 列表数据
        EasyExcel.write(fileName, DemoData.class).sheet("模版").doWrite(data());
    }
}
