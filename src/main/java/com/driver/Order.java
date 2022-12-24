package com.driver;

import java.util.StringTokenizer;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        String hour=deliveryTime.substring(0,2);
        String min=deliveryTime.substring(3,5);
        int time= Integer.parseInt(hour)*60+Integer.parseInt(min);
//        StringTokenizer st=new StringTokenizer(deliveryTime,":");
//        String hour=st.nextToken();
//        String min=st.nextToken();
//                int time= Integer.parseInt(hour)*60+Integer.parseInt(min);
        this.deliveryTime=time;
    }


    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
