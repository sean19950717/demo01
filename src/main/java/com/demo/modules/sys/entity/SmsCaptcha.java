package com.demo.modules.sys.entity;

import java.util.Date;

public class SmsCaptcha {
    private Integer smsCapachaId;

    private String smsTelephone;

    private String smsCapachaInfo;

    private String createTime;

    private String modifyTime;

    public Integer getSmsCapachaId() {
        return smsCapachaId;
    }

    public void setSmsCapachaId(Integer smsCapachaId) {
        this.smsCapachaId = smsCapachaId;
    }

    public String getSmsTelephone() {
        return smsTelephone;
    }

    public void setSmsTelephone(String smsTelephone) {
        this.smsTelephone = smsTelephone == null ? null : smsTelephone.trim();
    }

    public String getSmsCapachaInfo() {
        return smsCapachaInfo;
    }

    public void setSmsCapachaInfo(String smsCapachaInfo) {
        this.smsCapachaInfo = smsCapachaInfo == null ? null : smsCapachaInfo.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}