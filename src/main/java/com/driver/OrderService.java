package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


    public void addOrder(Order o){
        orderRepository.addOrder(o);
    }
    public void addDeliveryPartner(String pid){
        orderRepository.addDeliveryPartner(pid);
    }

    public void addOrderPartnerPair(String oid,String pid){
        orderRepository.addOrderPartnerPair(oid,pid);
    }

    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }
    public DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartnerById(id);
    }
    public int getOrderCountByPartnerId(String id){
        return orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String pid){
        return  orderRepository.getOrdersByPartnerId(pid);
    }
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public  int getOrdersLeftAfterGivenTimeByPartnerId(String time,String pid){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,pid);
    }

    public String getLastDeliveryTimeByPartnerId(String pid){
        return orderRepository.getLastDeliveryTimeByPartnerId(pid);
    }

    public void deletePartnerById(String pid){
        orderRepository.deletePartnerById(pid);
    }

    public  void deleteOrderById(String oid){
        orderRepository.deleteOrderById(oid);
    }
}
