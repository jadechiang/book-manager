package ealen.bookmanager.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 10:03
 */
@Getter
@Setter
@NoArgsConstructor
public class PageParam implements Serializable {
    private static final long serialVersionUID = 6297178964005032338L;
    // 当前页数
    private int pageNum;
    // 每页记录数
    private int numPerPage;
    private String order;
    public PageParam(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }
    /**
     * 获取分页查询参数对象,计算并设置了pageFirst和pageSize.
     * @return paramMap.
     */
    public HashMap<String, Object> getPageParamMap() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageFirst", (this.getPageNum() - 1) * this.getNumPerPage());
        paramMap.put("pageSize", this.getNumPerPage());
        return paramMap;
    }
}
