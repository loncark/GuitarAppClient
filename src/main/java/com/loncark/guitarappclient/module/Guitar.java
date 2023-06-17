package com.loncark.guitarappclient.module;

import java.math.BigDecimal;

public class Guitar {

    private Long id;
    private String code;

    private Material body;
    private Material neck;
    private String name;
    private BigDecimal price;
    private Long stock;

    public Guitar() {}

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
