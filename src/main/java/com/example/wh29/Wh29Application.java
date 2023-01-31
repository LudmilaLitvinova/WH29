package com.example.wh29;

import com.example.wh29.order.CoffeeOrderBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
/**
 * @author Ludmila Litvinova on 31.01
 */
@SpringBootApplication
@Slf4j
public class Wh29Application {

    public static void main(String[] args) {
        SpringApplication.run(Wh29Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        CoffeeOrderBoard board=new CoffeeOrderBoard();
        board.draw();
        board.deliver(1);
        board.add("Petr");
        board.add("Viktor");
        board.add("Andrew");

        board.deliver();

        board.add("Dan");
        board.add("Max");

        board.deliver(4);

        board.draw();
    }
}
