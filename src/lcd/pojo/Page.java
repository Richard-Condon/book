package lcd.pojo;

import java.util.List;

/**
 * @athor lcdstart
 * @create 2022-07-30 16:28
 */
public class Page<T> {
    public static final Integer PAGE_SIZE =4;
    private Integer pageNo;
    private Integer pageTotal;
    private Integer pagesize= PAGE_SIZE;
    private Integer PageTotalCount;
    private List<T> items;
    //分页条的请求地址
    private String url;


    public Page(Integer pageNo, Integer pageTotal, Integer pagesize, Integer PageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pagesize = pagesize;
        this.PageTotalCount = PageTotalCount;
        this.items = items;
    }

    public Page() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {

        this.pageNo = pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPageTotalCount() {
        return PageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCiunt) {
        PageTotalCount = pageTotalCiunt;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pagesize=" + pagesize +
                ", PageTotalCount=" + PageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
