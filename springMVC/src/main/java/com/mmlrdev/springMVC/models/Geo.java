package com.mmlrdev.springMVC.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Geo {
    private BigDecimal lat;
    private BigDecimal lng;
}
