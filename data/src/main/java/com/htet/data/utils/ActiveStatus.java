package com.htet.data.utils;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

import java.util.Objects;


@RequiredArgsConstructor
@Getter
public enum ActiveStatus {

    ACTIVE (1,"Active"), INACTIVE(2,"Inactive");

    private final Integer code;
    private final String desc;

    public static ActiveStatus convertCodeIntoStatus(int code) {
        return Objects.equals(ActiveStatus.valueOf("ACTIVE").code, code) ? ActiveStatus.ACTIVE : ActiveStatus.INACTIVE;
    }


    public static ActiveStatus convertStringIntoStatus(String status) {
        return ActiveStatus.ACTIVE.toString().equals(status) ? ActiveStatus.ACTIVE : ActiveStatus.INACTIVE;
    }

}