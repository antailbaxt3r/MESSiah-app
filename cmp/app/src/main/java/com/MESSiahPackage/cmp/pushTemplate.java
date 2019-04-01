package com.MESSiahPackage.cmp;

import com.google.firebase.database.PropertyName;

public class pushTemplate {
    @PropertyName("order_id")
    public String order_id;
    @PropertyName("name")
    public String name;
    @PropertyName("order")
    public String order;
    @PropertyName("paid_Rs")
    public int paid_Rs;
    @PropertyName("packed")
    public boolean packed;

    public pushTemplate(String order_id,String name,String order,int paid_Rs,boolean packed)
    {
        this.order_id=order_id;
        this.order=order;
        this.name=name;
        this.paid_Rs=paid_Rs;
        this.packed=packed;

    }

    public pushTemplate()
    {

    }




}
