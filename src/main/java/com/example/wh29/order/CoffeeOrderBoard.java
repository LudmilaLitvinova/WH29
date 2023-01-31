package com.example.wh29.order;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ludmila Litvinova on 31.01
 */
@Slf4j
public class CoffeeOrderBoard {

    static Integer lastNumber = 0;
    private final List<Order> orders = new ArrayList<>();

    public void add(String name) {
        lastNumber++;
        orders.add(new Order(lastNumber, name));
            log.info("Замовлення номер " + lastNumber + " взято в роботу");
        }


    public void deliver() {

        if (orders.size() > 0) {
            log.info(
                    "Замовлення номер " + orders.get(0).getNumberOfOrder()
                            + " готово до видачі");
            orders.remove(0);
        } else {
            try {
                throw new RuntimeException("Немає готових до видачі замовлень. ");
            } catch (RuntimeException e) {
                log.error("Помилка на стадії видачі", e);
            }
        }
    }

    public void deliver(int number) {
        if (orders.size() > 0 && orders.stream().map(Order::getNumberOfOrder)
                .toList().stream().anyMatch(n -> n == number)) {
            for (Order order : orders) {
                if (order.getNumberOfOrder() == number) {
                    log.info("Замовлення номер " + number + " готово до видачі");
                    orders.remove(order);
                    break;
                }
            }
        } else {
            try {
                throw new RuntimeException("Немає замовлення з номером - " + number);
            } catch (RuntimeException e) {
                log.error("Помилка на стадії видачі", e);
            }
        }
    }


    public void draw() {
        if (orders.size() > 0) {
            log.info("Стан черги на даний момент: ");
            for (Order order : orders) {
                MDC.put("number", String.valueOf(order.getNumberOfOrder()));
                MDC.put("name", String.valueOf(order.getName()));
                log.info(" очікує у черзі.");
            }
        } else {
            log.info("Замовлення відсутні ");
        }
    }
}
