package top.tocchen.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tocchen
 * @date 2023/2/11 14:43
 * @since jdk 1.8
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<E> {

    private E data;

    private int current;

    private int pageSize;

    private int totalPage;

    private int total;

}
