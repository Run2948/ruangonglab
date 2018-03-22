package com.borun.lab.utils.bean;

public enum RespStatus {
    SUCCESS(0, "success"),
    FAILURE(1,"failure");

    private int code;
    private String desc;

    RespStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
