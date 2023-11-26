package com.mad.g1.bui_minh_hieu.code_mobile.Model;
import java.io.Serializable;

public class MemberStatistic implements Serializable {

    private int total;
    private String status;

    public MemberStatistic() {
    }

    public MemberStatistic(int total, String status) {
        this.total = total;
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}