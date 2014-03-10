package tcrawley.examples;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.Message;


public class JavaHandler implements Handler<Message> {

    public void handle(Message msg) {
        System.out.println("Got message in java: " + 
                           msg.body() + " " + 
                           msg.body().getClass().getName());
    }
}
