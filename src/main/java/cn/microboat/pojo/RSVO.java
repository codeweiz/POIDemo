package cn.microboat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RSVO {
    private String typeName;
    private List<SubArrayVO> subArray;
    private Double total = 0.0;
}
