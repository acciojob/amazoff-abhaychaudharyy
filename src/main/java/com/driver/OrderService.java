package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@Component


public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }


    public void addPartner(String partnerId , DeliveryPartner deliveryPartner){
        orderRepository.addPartner(partnerId , deliveryPartner);
    }


    public void addOrderPartnerPair(String orderId, String partnerId){
        orderRepository.addOrderPartnerPair(orderId , partnerId);

    }


    public Order getOrderById(String orderId){

        return orderRepository.getOrderById(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){

        return orderRepository.getPartnerById(partnerId);
    }


    public int getOrderCountByPartnerId(String partnerId){

        return orderRepository.getOrderCountByPartnerId(partnerId);
    }


    public List<String> getOrdersByPartnerId(String partnerId){

        return orderRepository.getOrdersByPartnerId(partnerId);
    }


    public List<String> getAllOrders(){

        return orderRepository.getAllOrders();
    }


    public int getCountOfUnassignedOrders(){

        return orderRepository.getCountOfUnassignedOrders();
    }


    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = 0;



        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }


    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = null;



        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }


    public void deletePartnerById(String partnerId){

        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){

        orderRepository.deleteOrderById(orderId);
    }
}
