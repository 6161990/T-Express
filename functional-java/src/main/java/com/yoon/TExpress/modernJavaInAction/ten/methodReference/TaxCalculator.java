package com.yoon.TExpress.modernJavaInAction.ten.methodReference;

import com.yoon.TExpress.modernJavaInAction.ten.Order;

public class TaxCalculator {
    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator withTaxRegional(){
        useRegional = true;
        return this;
    }

    public TaxCalculator withTaxGeneral(){
        useGeneral = true;
        return this;
    }

    public TaxCalculator withTaxSurcharge(){
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order){
        return calculate(order, useRegional, useGeneral, useSurcharge);
    }

    public static double calculate(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge){
        double value = order.getValue();

        if(useRegional) value = Tax.regional(value);
        if(useGeneral) value = Tax.general(value);
        if(useSurcharge) value = Tax.surcharge(value);

        return value;
    }
}
