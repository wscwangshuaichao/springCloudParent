package springcloud.learn.wsc.core.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamBooleanValue;
import uk.co.jemos.podam.common.PodamCollection;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamLongValue;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 对分页结果进行包装
 * <p/>
 * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
 * 改类主要针对调用一些使用 mybatis_pagehelper 做分页的项目, 可以避免导入不必要的 mybatis 相关依赖
 *
 * @author liuzh/abel533/isea533
 * @version 3.3.0
 * @date 3.2.2
 * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked", "squid:S1068"})
@Setter
@Getter
@NoArgsConstructor
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    @PodamIntValue(numValue = "1")
    private int pageNum;
    //每页的数量
    @PodamIntValue(numValue = "20")
    private int pageSize;
    //当前页的数量
    @PodamIntValue(numValue = "20")
    private int size;

    //由于startRow和endRow不常用，这里说个具体的用法
    //可以在页面中"显示startRow到endRow 共size条数据"

    //当前页面第一个元素在数据库中的行号
    @PodamIntValue(numValue = "1")
    private int startRow;
    //当前页面最后一个元素在数据库中的行号
    @PodamIntValue(numValue = "20")
    private int endRow;
    //总记录数
    @PodamLongValue(numValue = "200")
    private long total;
    //总页数
    @PodamIntValue(numValue = "10")
    private int pages;
    //结果集
    @PodamCollection(nbrElements = 20)
    private List<T> list;

    //前一页
    @PodamIntValue(numValue = "0")
    private int prePage;
    //下一页
    @PodamIntValue(numValue = "2")
    private int nextPage;

    //是否为第一页
    @PodamBooleanValue(boolValue = true)
    @JsonIgnore
    private boolean firstPage = false;
    //是否为最后一页
    @PodamBooleanValue(boolValue = false)
    @JsonIgnore
    private boolean lastPage = false;
    //是否有前一页
    @PodamBooleanValue(boolValue = false)
    private boolean hasPreviousPage = false;
    //是否有下一页
    @PodamBooleanValue(boolValue = true)
    private boolean hasNextPage = false;
    //导航页码数
    @PodamIntValue(numValue = "8")
    private int navigatePages;
    //所有导航页号
    @PodamCollection(nbrElements = 10)
    private int[] navigatepageNums;
    //导航条上的第一页
    @PodamIntValue(numValue = "1")
    private int navigateFirstPage;
    //导航条上的最后一页
    @PodamIntValue(numValue = "10")
    private int navigateLastPage;

    // 下面几个方法是 pageHelper 的坑, 由于它的 boolean 对象用 isXXX 命名, lombok 默认生成的方法是 boolean isXXX 和 void setXXX,
    // 而 pageHelper 中的方法确是 isIsXXX 和 setIsXXX, 在做对象 copy的时候会丢失数据.
    public boolean isIsFirstPage() {
        return firstPage;
    }

    public void setIsFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isIsLastPage() {
        return lastPage;
    }

    public void setIsLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        this(list, 8);
    }

    /**
     * 包装Page对象
     *
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    @Generated
    public PageInfo(List<T> list, int navigatePages) {
        if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = this.pageSize > 0 ? 1 : 0;
            this.list = list;
            this.size = list.size();
            this.total = list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            this.navigatePages = navigatePages;
            //计算导航页
            calcNavigatepageNums();
            //计算前后页，第一页，最后一页
            calcPage();
            //判断页面边界
            judgePageBoudary();
        }
    }

    /**
     * 计算导航页
     */
    @Generated
    private void calcNavigatepageNums() {
        //当总页数小于或等于导航页码数时
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
    }

    /**
     * 计算前后页，第一页，最后一页
     */
    private void calcPage() {
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            navigateFirstPage = navigatepageNums[0];
            navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
            if (pageNum > 1) {
                prePage = pageNum - 1;
            }
            if (pageNum < pages) {
                nextPage = pageNum + 1;
            }
        }
    }

    /**
     * 判定页面边界
     */
    private void judgePageBoudary() {
        firstPage = pageNum == 1;
        lastPage = pageNum == pages || pages == 0;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }
}

