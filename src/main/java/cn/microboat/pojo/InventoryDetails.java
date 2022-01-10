package cn.microboat.pojo;

import lombok.Data;
import org.joda.time.DateTime;

/**
 * @author zhouwei
 */
@Data
public class InventoryDetails {

    private Integer serialNumber;

    private String code;

    private String orderNumber;

    private String materialType;

    private String materialName;

    private String unitName;

    private String dept;

    private String storeZone;

    private Integer oldNumber;

    private Integer newNumber;

    private DateTime updateTime;

    private String updater;

    private String note;
}
