package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.order.OrderException;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
import it.polimi.db2.telco.services.OrderService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderController {
    @Inject
    OrderService orderService;
    @Inject
    UserService userService;

    public OrderController(){}

    public Order getOrderById(Integer orderId) throws OrderException {
        return orderService.getOrderById(orderId);
    }

    public Order getMyOrderByOrderId(Integer orderId, Integer userId) throws OrderException {
        return orderService.getMyOrderByOrderId(orderId, userId);
    }

    public List<Order> getOrdersOfUser(Integer userId) throws OrderException, UserException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return orderService.getOrdersOfUser(user.getId());
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<Order> getRejectedUserOrder(Integer userId) throws UserException {
        List<Order> rejected = new ArrayList<>();
        List<Order> userOrders = getOrdersOfUser(userId);
        rejected = userOrders.stream().filter(o -> o.getOrderStatus().equals(2)).collect(Collectors.toList());
        return rejected;
    }

    public Integer createOrder(Order order) throws OrderException{
        Integer orderId = orderService.createOrder(order);
        if(orderId == null){
            throw new OrderException("Unable to save the order");
        }
        return orderId;
    }

    public Integer updateOrder(Order order) throws OrderException {
        Integer orderId;
        if (orderService.getOrderById(order.getId()) != null) {
            orderId = orderService.updateOrder(order);
        } else {
            throw new OrderNotFoundException();
        }
        return orderId;
    }

    public void deleteOrder(Integer orderId) throws OrderException {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            orderService.deleteOrder(order);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
