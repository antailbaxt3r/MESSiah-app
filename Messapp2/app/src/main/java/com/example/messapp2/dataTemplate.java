package com.example.messapp2;


public class dataTemplate {
    public String order_id;
    public String name;
    public String order;
    public int paid_Rs;
    public boolean packed;

    public dataTemplate(String order_id,String name,String order,int paid_Rs,boolean packed)
    {
        this.order_id=order_id;
        this.order=order;
        this.name=name;
        this.paid_Rs=paid_Rs;
        this.packed=packed;

    }



    public dataTemplate() {
    }




}




