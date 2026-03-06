package com.example.E_seva_kendra.service.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_seva_kendra.dto.OrderRequest;
import com.example.E_seva_kendra.model.Order;
import com.example.E_seva_kendra.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest request){

        Order order = new Order();

        order.setName(request.getName());
        order.setMobile(request.getMobile());
        order.setServiceName(request.getServiceName());
        order.setExtraData(request.getExtraData());

        order.setStatus("Pending");

        return orderRepository.save(order);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public String updateOrderStatus(Long id, String status){

        Order order = orderRepository.findById(id).orElse(null);

        if(order == null){
            return "Order not found";
        }

        order.setStatus(status);

        orderRepository.save(order);

        return "Order status updated successfully";
    }
}