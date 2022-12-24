package com.driver;


import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Component
public class OrderRepository {

    HashMap<String, Order>orders=new HashMap<>();
    HashMap<String,DeliveryPartner> deliverPartner=new HashMap<>();

    HashMap<String, List<String>>orderDeliveryPair=new HashMap<>();
    HashSet<String> assigned=new HashSet<>();
    HashSet<String> Unassigned=new HashSet<>();

    void addOrder(Order O) {
        if (!orders.containsKey(O.getId()))
            orders.put(O.getId(), O);

    }

    void addDeliveryPartner(String pid){
            deliverPartner.put(pid,new DeliveryPartner(pid));

    }

    void addOrderPartnerPair(String orderId,String partnerId){
        List<String> orderList=new ArrayList<>();
        if(orderDeliveryPair.containsKey(partnerId)){
            orderList=orderDeliveryPair.get(partnerId);
            orderList.add(orderId);
            orderDeliveryPair.put(partnerId,orderList);
        }
        else{
            orderList.add(orderId);
            orderDeliveryPair.put(partnerId,orderList);
        }
        assigned.add(orderId);
    }


    Order getOrderById(String id){
        if(orders.containsKey(id)) {
            return orders.get(id);
        }
        return null;
    }


    DeliveryPartner getPartnerById(String id){
        if(deliverPartner.containsKey(id)){
        return deliverPartner.get(id);}
        return  null;
    }

    public int getOrderCountByPartnerId(String Id){
        return orderDeliveryPair.get(Id).size();
    }

    public List<String> getOrdersByPartnerId(String pId){

        return orderDeliveryPair.get(pId);
    }


     public List<String>  getAllOrders(){
            List<String>allOrders=new ArrayList<>();
            for(Order o:orders.values()){
                allOrders.add(o.getId());
            }
            return allOrders;
     }


  public int getCountOfUnassignedOrders(){
        int count=0;
        for(String id:orders.keySet()){
            if(!assigned.contains(id)){
                count++;
            }
        }
        return count;
  }
public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
    String hour=time.substring(0,2);
    String min=time.substring(3,5);
    int Time= Integer.parseInt(hour)*60+Integer.parseInt(min);
    List<String>orderId=new ArrayList<>();
    orderId=getOrdersByPartnerId(partnerId);
    int count=0;
    for(int i=0;i<orderId.size();i++){
        int t=orders.get(orderId.get(i)).getDeliveryTime();
        if(t>Time){
            count++;
        }
    }
    return count;
}
   public String getLastDeliveryTimeByPartnerId(String partnerId){
       List<String>orderId=new ArrayList<>();
       orderId=getOrdersByPartnerId(partnerId);
       List<Integer>time=new ArrayList<>();
       for(int i=0;i<orderId.size();i++) {
           int t = orders.get(orderId.get(i)).getDeliveryTime();
           time.add(t);
       }
       int maxTime=0;
       for(int i=0;i<time.size();i++){
           maxTime=Math.max(time.get(i),maxTime);
       }
       int hour=maxTime/60;
       int min=maxTime%60;

       String hour1= Integer.toString(hour);
       String min1=Integer.toString(min);

       if(hour1.length()==1){
           hour1="0"+hour1;
       }
       if(min1.length()==1){
           min1="0"+min1;
       }

       String finalTime=hour1+ ":" +min1;
       return finalTime;
   }


   public void deletePartnerById(String pid){
       List<String>orderIds=new ArrayList<>();
       orderIds=orderDeliveryPair.get(pid);
       for(int i=0;i<orderIds.size();i++){
           assigned.remove(orderIds.get(i));
       }
       orderDeliveryPair.remove(pid);
        deliverPartner.remove(pid);
   }

   public void deleteOrderById(String orderId){
        for(String partnerId:orderDeliveryPair.keySet()){
            List<String>allOrders= orderDeliveryPair.get(partnerId);
            for (int i=0;i<allOrders.size();i++){
                if(allOrders.get(i).equals(orderId)){
                    allOrders.remove(orderId);
                }
            }
        }
        assigned.remove(orderId);
        orders.remove(orderId);

   }
}
