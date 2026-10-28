package com.myblog.myblog.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

public class PageResult<T> {
    private List<T> items;
    private long total;
    private long page;
    private long size;

    //静态工厂方法，把MyBatis‑Plus IPage转为我们的PageResult
    public static <T> PageResult<T> of(IPage<T> page) {
        PageResult<T> res = new PageResult<>();
        res.items = page.getRecords();
        res.total = page.getTotal();
        res.page = page.getCurrent();
        res.size = page.getSize();
        return res;
    }

    // getter setter
    public List<T> getItems() { return items; }
    public void setItems(List<T> items) { this.items = items; }
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
    public long getPage() { return page; }
    public void setPage(long page) { this.page = page; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
}