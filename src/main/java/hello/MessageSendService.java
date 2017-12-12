package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by zhouhongyang@zbj.com on 12/4/2017.
 */
@Service
public class MessageSendService implements Runnable {
    @Autowired
    private SimpMessagingTemplate template;

    @PostConstruct
    public void init() {
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.setName("MessageSendService");
        t.start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                this.template.convertAndSendToUser("Aray", "/queue/trade", new Greeting("Message sent by convertAndSendToUser. i=" + i++));
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
