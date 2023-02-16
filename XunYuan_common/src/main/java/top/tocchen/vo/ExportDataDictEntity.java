package top.tocchen.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.tocchen.utils.exceptions.DataFormatException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tocchen
 * @date 2023/2/14 14:13
 * @since jdk 1.8
 * DataDict Excel Java Bean
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportDataDictEntity implements Serializable {

    static final long serialVersionUID = 202302131826L;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

    @ExcelProperty(value = "ID",index = 0)
    private int id;
    @ExcelProperty(value = "Parent_ID",index = 1)
    private int parentId;
    @ExcelProperty(value = "Name",index = 2)
    private String name;
    @ExcelProperty(value = "Value",index = 3)
    private String value;
    @ExcelProperty(value = "Dict_Code",index = 4)
    private String dictCode;
    @ExcelProperty(value = "CreateDateTime",index = 5)
    private Date createDateTime;


    public ExportDataDictEntity(DataDictEntity dataDictEntity){
        this(dataDictEntity.getId(),
                dataDictEntity.getParentId(),
                dataDictEntity.getName(),
                dataDictEntity.getValue(),
                dataDictEntity.getDictCode(),
                dataDictEntity.getCreateDateTime());
    }

    public ExportDataDictEntity(int id, int parentId, String name, String value, String dictCode, String createDateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.value = value;
        this.dictCode = dictCode;
        try {
            this.createDateTime = dateFormat.parse(createDateTime);
        } catch (ParseException e) {
            throw new DataFormatException();
        }
    }
}
