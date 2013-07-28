package services;

import models.Communication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 25/7/13
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommuncationSenderService {

    private ThreadPoolExecutor pool = new ThreadPoolExecutor(10 , 10 , 100, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(10000));

    @Autowired
    private EmailSenderService emailSenderService;
    public void sendCommunication(final Communication comm){
        pool.execute(new Runnable() {
            @Override
            public void run() {
                emailSenderService.sendEmail(comm);
            }
        });
    }
}
