package cn.microboat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouwei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSDO {
    private String typeName;
    private String unitName;
    private Double sum;
}
