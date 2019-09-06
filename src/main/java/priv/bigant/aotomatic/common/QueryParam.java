package priv.bigant.aotomatic.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class QueryParam<T> extends Page<T> {

    private String beginTime;
    private String endTime;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
