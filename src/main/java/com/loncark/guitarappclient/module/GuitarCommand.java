package com.loncark.guitarappclient.module;

import java.math.BigDecimal;

public class GuitarCommand {
    String code;

    Material body;
    Material neck;
    String name;
    BigDecimal price;
    Long stock;

    public GuitarCommand(String code, Material body, Material neck, String name, BigDecimal price, Long stock) {
        this.code = code;
        this.body = body;
        this.neck = neck;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
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
}
