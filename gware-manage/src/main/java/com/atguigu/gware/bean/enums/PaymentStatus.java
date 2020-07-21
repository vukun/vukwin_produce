package com.atguigu.gware.bean.enums;

import java.io.Serializable;

/**
 * @param
 * @return
 */
public enum PaymentStatus implements Serializable {

    UNPAID("支付中"),
    PAID("已支付"),
    PAY_FAIL("支付失败"),
    ClOSED("已关闭");

    private String name ;

    PaymentStatus(String name) {
        this.name=name;
    }
}
