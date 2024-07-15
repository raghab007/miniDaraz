package com.Raghab.shopApp.mappers;

import com.Raghab.shopApp.entity.OrderDto;
import com.Raghab.shopApp.entity.Orders;
import com.Raghab.shopApp.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Orders toOrder(OrderDto orderDto){
        Orders orders = new Orders();
        orders.setStatus(orderDto.status());
        User user = new User();
        user.setUserName(orderDto.userId());
        orders.setUser(user);
        return  orders;
    }

    public  OrderDto orderDto(Orders orders){
        return new OrderDto(orders.getStatus(),orders.getUser().getUserName());
    }

}
