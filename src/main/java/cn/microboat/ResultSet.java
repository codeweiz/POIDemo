package cn.microboat;

import cn.microboat.pojo.RSDO;
import cn.microboat.pojo.RSVO;
import cn.microboat.pojo.SubArrayVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhouwei
 */
public class ResultSet {

    public static void main(String[] args) {
        ArrayList<RSDO> arrayList = new ArrayList<>();
        RSDO rsdo = new RSDO();
        rsdo.setTypeName("饮用水");
        rsdo.setUnitName("24/箱");
        rsdo.setSum(150.0);
        arrayList.add(rsdo);
        rsdo = new RSDO();

        rsdo.setTypeName("饮用水");
        rsdo.setUnitName("12/箱");
        rsdo.setSum(130.0);
        arrayList.add(rsdo);
        rsdo = new RSDO();

        rsdo.setTypeName("方便面");
        rsdo.setUnitName("24/箱");
        rsdo.setSum(100.0);
        arrayList.add(rsdo);
        rsdo = new RSDO();

        rsdo.setTypeName("蜡烛");
        rsdo.setUnitName("24/包");
        rsdo.setSum(80.0);
        arrayList.add(rsdo);
        rsdo = new RSDO();

        rsdo.setTypeName("蜡烛");
        rsdo.setUnitName("12/包");
        rsdo.setSum(100.0);
        arrayList.add(rsdo);

        System.out.println(arrayList);

        RSVO rsvo = new RSVO();
        ArrayList<RSVO> rsvoArrayList = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (RSDO rsdoItem : arrayList) {
//            hashMap.getOrDefault(rsdoItem.getTypeName(), 1);
            if (hashMap.getOrDefault(rsdoItem.getTypeName(), 1).equals(1)) {
                rsvo.setTypeName(rsdoItem.getTypeName());
                rsvo.setTotal(rsdoItem.getSum());

                SubArrayVO subArrayVO = new SubArrayVO();
                List<SubArrayVO> subArrayVOS = new ArrayList<>();
                subArrayVO.setUnitName(rsdoItem.getUnitName());
                subArrayVO.setSubtotal(rsdoItem.getSum());
                subArrayVOS.add(subArrayVO);
                rsvo.setSubArray(subArrayVOS);

                rsvoArrayList.add(rsvo);
                rsvo = new RSVO();
                hashMap.put(rsdoItem.getTypeName(), hashMap.getOrDefault(rsdoItem.getTypeName(), 1)+1);
            } else {
                for (RSVO rsvoItem : rsvoArrayList) {
                    if (rsvoItem.getTypeName().equals(rsdoItem.getTypeName())) {
                        rsvoItem.setTotal(rsvoItem.getTotal()+rsdoItem.getSum());

                        SubArrayVO subArrayVO = new SubArrayVO();
                        subArrayVO.setUnitName(rsdoItem.getUnitName());
                        subArrayVO.setSubtotal(rsdoItem.getSum());
                        rsvoItem.getSubArray().add(subArrayVO);

//                        rsvoArrayList.add(rsvoItem);
                        hashMap.put(rsdoItem.getTypeName(), hashMap.get(rsdoItem.getTypeName())+1);
                    }
                }
            }
        }

//        for (RSDO rsdoItem : arrayList) {
//            rsvo.setTypeName(rsdoItem.getTypeName());
//            rsvo.setTotal(rsvo.getTotal() + rsdoItem.getSum());
//
//
//            SubArrayVO subArrayVO = new SubArrayVO();
//            List<SubArrayVO> subArrayVOS = new ArrayList<>();
//            subArrayVO.setUnitName(rsdoItem.getUnitName());
//            subArrayVO.setSubtotal(rsdoItem.getSum());
//            subArrayVOS.add(subArrayVO);
//            rsvo.setSubArray(subArrayVOS);
//
//            rsvoArrayList.add(rsvo);
//            rsvo = new RSVO();
//        }

        System.out.println("1111111111111");
        for (RSVO rsvo1 : rsvoArrayList) {
            System.out.println(rsvo1);
        }
//        System.out.println(rsvoArrayList);
    }
}
