package ealen.bookmanager.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-10 10:09
 */
@SuppressWarnings("unchecked")
public class PageUtils {
    //返回拥有共同参数deleted=false的 Map
    public static Map<String, Object> getCommonParam() {
        Map<String, Object> param = new HashMap<>();
        param.put("deleted", "false");
        return param;
    }

    //根据传入的参数开始进行分页和排序,这个要执行在进行数据库查询前面
    public static void paging(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getNumPerPage());
        if (StringUtils.isNotEmpty(pageParam.getOrder())) {
            PageHelper.orderBy(pageParam.getOrder());
        }
    }

    // 根据页码的参数和从数据库中得到的页码对象，返回封装好的PageBean对象
    public static PageBean getPageBean(PageParam pageParam, Page page) {
        List<Object> list = page.getResult();
        return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), Integer.parseInt(String.valueOf(page.getTotal())), list);
    }

    //从请求中获取到参数返回参数对象
    public static PageParam setPageParam(HttpServletRequest request) {
        String pageNum = request.getParameter("pageNum");
        String numPerPage = request.getParameter("numPerPage");
        String order = request.getParameter("order");
        PageParam pageParam = new PageParam();
        if (StringUtils.isNotEmpty(pageNum)) {
            pageParam.setPageNum(Integer.parseInt(pageNum));
        } else {
            pageParam.setPageNum(1);
        }
        if (StringUtils.isNotEmpty(numPerPage)) {
            pageParam.setNumPerPage(Integer.parseInt(numPerPage));
        } else {
            pageParam.setNumPerPage(3);         //设置每页多少条数据
        }
        if (StringUtils.isNotEmpty(order)) {
            pageParam.setOrder(order);
        }
        return pageParam;
    }
}
