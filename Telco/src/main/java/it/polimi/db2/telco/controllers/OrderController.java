package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.services.OrderService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class OrderController {
    @Inject
    OrderService orderService;
    @Inject
    UserService userService;

    public OrderController(){}

    public Order getOrderById(Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    public List<Order> getOrdersOfUser(Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user != null) {
            return orderService.getOrdersOfUser(user.getId());
        } else {
            throw new Exception("");
        }
    }

    public Integer createOrder(Order order) {
        Integer orderId;
        return orderService.createOrder(order);
    }

    public Integer updateOrder(Order order) throws Exception {
        Integer orderId;
        if (orderService.getOrderById(order.getId()) != null) {
            orderId = orderService.updateOrder(order);
        } else {
            throw new Exception("");
        }
        return orderId;
    }

    public void deleteOrder(Integer orderId) throws Exception {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            orderService.deleteOrder(order);
        } else {
            throw new Exception("");
        }
    }
}
