package ealen.bookmanager.util;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 09:22
 * 自定义的分页对象
 */
@NoArgsConstructor
@Getter
@Setter
@JSONType
public class PageBean implements Serializable {

    //指定的或是页面参数,当前页
    private int currentPage;
    //每页显示多少条记录
    private int numPerPage;
    //当前查询条件下的总记录数, 查询数据库
    private long totalCount;
    //本页的数据列表
    private List<Object> recordList;
    //计算,总页数
    private int pageCount;
    //页码列表的开始索引（包含）
    private int beginPageIndex;
    //页码列表的结束索引（包含）
    private int endPageIndex;
    //当前分页条件下的统计结果
    private Map<String, Object> countResultMap;

    //只接受前4个必要的属性，会自动的计算出其他3个属生的值
    public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;
        // 计算总页码
        pageCount = (totalCount + numPerPage - 1) / numPerPage;
        //计算 beginPageIndex 和 endPageIndex
        if (pageCount <= 10) {                  //总页数不多于10页，则全部显示
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {                                //总页数多于10页，则显示当前页附近的共10个页码
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }

    //只接受前5个必要的属性，会自动的计算出其他3个属生的值
    public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList, Map<String, Object> countResultMap) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;
        this.countResultMap = countResultMap;
        // 计算总页码
        this.pageCount = (totalCount + numPerPage - 1) / numPerPage;
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        } else {
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }

}
