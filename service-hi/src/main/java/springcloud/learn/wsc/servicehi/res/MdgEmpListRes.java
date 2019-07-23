package springcloud.learn.wsc.servicehi.res;

import com.alibaba.fastjson.annotation.JSONField;
import com.longfor.gf.op.gop.mdg.dto.MdgEmployeeDetailDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author guoguangxiao
 * @date 2019/4/28 16:25:08
 */
public class MdgEmpListRes implements Serializable {

    @JSONField(name = "code")
    private String code;
    @JSONField(name = "msg")
    private String msg;


    /** 当前页数 */
    @JSONField(name = "page")
    private int page;
    /** 每页条数 */
    @JSONField(name = "size")
    private int size;
    /** 总条数 */
    @JSONField(name = "total")
    private int total;
    /** 总页数 */
    @JSONField(name = "pages")
    private int pages;


    @JSONField(name = "list")
    private List<MdgEmployeeDetailDto> mdgEmployeeDetailList;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<MdgEmployeeDetailDto> getMdgEmployeeDetailList() {
        return mdgEmployeeDetailList;
    }

    public void setMdgEmployeeDetailList(List<MdgEmployeeDetailDto> mdgEmployeeDetailList) {
        this.mdgEmployeeDetailList = mdgEmployeeDetailList;
    }
}
