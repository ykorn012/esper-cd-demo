package com.isd.cep.util;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isd.cep.event.CDEvent;
import com.isd.cep.handler.CDEventHandler;

/**
 * Just a simple class to create a number of Random CDEvents and pass them off to the
 * CDEventHandler.
 */
@Component
public class RandomCDEventGenerator {

    /** Logger */
    private static Logger LOG = LoggerFactory.getLogger(RandomCDEventGenerator.class);

    /** The CDEventHandler - wraps the Esper engine and processes the Events  */
    @Autowired
    private CDEventHandler CDEventHandler;

    /**
     * Creates simple random CD events and lets the implementation class handle them.
     */
    public void startSendingCDReadings(final long noOfCDEvents) {

        ExecutorService xrayExecutor = Executors.newSingleThreadExecutor();

        xrayExecutor.submit(new Runnable() {
            public void run() {

                LOG.debug(getStartingMessage());
                
                int count = 1;
                while (count < noOfCDEvents) {
                    CDEvent ve = new CDEvent(count, (100 + new Random().nextInt(250)), new Date());
                    try {
                    	CDEventHandler.handle(ve);
                    	count++;
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        LOG.error("Thread Interrupted", e);
                    }                    
                }
                System.exit(0);

            }
        });
    }

    
    private String getStartingMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n************************************************************************\"");
        sb.append("\n* STARTING - Etching Equipment #1 APC (Advanced Process Control) DEMO");
        sb.append("\n* PLEASE WAIT - CDs ARE 100 RANDOM");
        sb.append("\n* A WHILE TO SEE WARNING AND CRITICAL EVENTS!");
        sb.append("\n************************************************************************\n");
        
//        sb.append("============================================================================");
//        sb.append("\n* Etching Equipment's Actual Metrology : 1 Sampling Wafer #1 CD [" + new Random().nextInt(500) + " nm]");
//        sb.append("\n* Calling Virtual Metrology in Etching Process Equipment");
//        sb.append("\n============================================================================");
        
        return sb.toString();
    }
}