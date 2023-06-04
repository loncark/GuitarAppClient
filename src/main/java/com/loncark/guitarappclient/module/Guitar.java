package com.loncark.guitarappclient.module;

import java.math.BigDecimal;

public class Guitar {

    Long id;
    String code;

    Material body;
    Material neck;
    String name;
    BigDecimal price;
    Long stock;

    @Override
    public String toString() {
        return "Guitar{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    public Guitar(Material body, Material neck, String name, BigDecimal price, Long stock, String code) {
        this.body = body;
        this.neck = neck;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.code = code;
    }

    public Guitar() {}

    public Long getId() {
        return id;
    }

    public Material getBody() {
        return body;
    }

    public Material getNeck() {
        return neck;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getStock() {
        return stock;
    }

    public String getCode() {
        return code;
    }

}
