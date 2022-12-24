package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class OrderRepository {
    HashMap<String , Order> orderMap = new HashMap<>();
    HashMap<String , DeliveryPartner> deliveryMap = new HashMap<>();
    HashMap<String , List<String>> deliveryOrderMapping = new HashMap<>();


    int unassCount = 0;


    public void addOrder(Order order){
        orderMap.put(order.getId() , order);
    }


    public void addPartner(String partnerId , DeliveryPartner deliveryPartner){
        deliveryMap.put(partnerId , deliveryPartner);
    }


    public void addOrderPartnerPair(String orderId, String partnerId){
        if(deliveryOrderMapping.containsKey(partnerId))
        {
            deliveryOrderMapping.get(partnerId).add(orderId);
        }
        else
        {
            List<String> temp = new ArrayList<>();
            temp.add(orderId);
            deliveryOrderMapping.put(partnerId , temp);
        }

    }


    public Order getOrderById(String orderId){

        return orderMap.get(orderId);
    }


    public DeliveryPartner getPartnerById(String partnerId){

        return deliveryMap.get(partnerId);
    }


    public int getOrderCountByPartnerId(String partnerId){

        return deliveryOrderMapping.get(partnerId).size();
    }


    public List<String> getOrdersByPartnerId(String partnerId){

        return  deliveryOrderMapping.get(partnerId);
    }


    public List<String> getAllOrders(){

        List<String> orders = new ArrayList<>();

        for(String o : orderMap.keySet())
        {
            orders.add(o);
        }
        return orders;
    }


    public int getCountOfUnassignedOrders(){

        List<String> res = new ArrayList<>();
        if(unassCount == 0) {

            for(String o: deliveryOrderMapping.keySet())
            {
                int size = deliveryOrderMapping.get(o).size();
                for(int i = 0; i<size; i++)
                    res.add(deliveryOrderMapping.get(o).get(i));
            }

            int len = res.size();
            unassCount = orderMap.size() - len;

        }
        return unassCount;
    }


    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){

        Integer countOfOrders = 0;



        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
    }


    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
        String time = null;



        return new ResponseEntity<>(time, HttpStatus.CREATED);
    }


    public void deletePartnerById(@PathVariable String partnerId){

        List<String> temp = new ArrayList<>();
        if(deliveryOrderMapping.containsKey(partnerId))
        {
            temp = deliveryOrderMapping.get(partnerId);
        }
        int len = temp.size();

        unassCount = unassCount + len;
        deliveryOrderMapping.remove(partnerId);
    }

    public void deleteOrderById(String orderId){

        orderMap.remove(orderId);

        for(List<String> temp : deliveryOrderMapping.values())
        {
            int len = temp.size();
            for(int i = 0; i<len; i++)
            {
                if(temp.get(i).equals(orderId))
                {
                    temp.remove(i);
                    return;
                }
            }
        }
    }
}
