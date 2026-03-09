package com.example.E_seva_kendra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_seva_kendra.dto.OrderRequest;
import com.example.E_seva_kendra.model.Order;
import com.example.E_seva_kendra.repository.OrderRepository;
import com.example.E_seva_kendra.service.NotificationService;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NotificationService notificationService;

    public Order createOrder(OrderRequest request){

        Order order = new Order();

        order.setName(request.getName());
        order.setMobile(request.getMobile());
        order.setServiceName(request.getServiceName());
        order.setExtraData(request.getExtraData());

        order.setStatus("Pending");

        Order savedOrder = orderRepository.save(order);

        // 🔔 Notification for new order
        notificationService.createNotification(
                savedOrder.getId(),
                savedOrder.getId(),
                "Order Created",
                "Your order for " + savedOrder.getServiceName() + " has been created successfully.",
                "ORDER_CREATED"
        );

        return savedOrder;
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

        // 🔔 Notification when admin updates order status
        notificationService.createNotification(
                order.getId(),
                order.getId(),
                "Order Status Updated",
                "Your order status is now: " + status,
                "ORDER_STATUS_UPDATE"
        );

        return "Order status updated successfully";
    }

    public long getTotalOrders(){
        return orderRepository.count();
    }

    public long getPendingOrders(){
        return orderRepository.countByStatus("Pending");
    }

    public long getInProgressOrders(){
        return orderRepository.countByStatus("In Progress");
    }

    public long getCompletedOrders(){
        return orderRepository.countByStatus("Completed");
    }
}