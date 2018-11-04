package com.isd.cep.subscriber;

import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

/**
 * Wraps Esper Statement and Listener. No dependency on Esper libraries.
 */
@Component
public class MonitorEventSubscriber implements StatementSubscriber {
	
	/** Logger */
    private static Logger LOG = LoggerFactory.getLogger(MonitorEventSubscriber.class);
    
    private EPServiceProvider epService;
    
    private int samplingCount = 1;

    /**
     * {@inheritDoc}
     */    
    
    public String getStatement() {

        // Example of simple EPL with a batch Window
        //return "select avg(CD) as avg_val from CDEvent.win:time_batch(10 sec)";
    	//return "select avg(CD) as avg_val from CDEvent#length_batch(var_sampling_Count)";
    	return "select avg(CD) as avg_val from CDEvent#length_batch(10)";
        
    }

    /**
     * Listener method called when Esper has detected a pattern match.
     */
    public void update(Map<String, Double> eventMap) throws Exception{ 

        // average temp over 5 secs
        Double avg = (Double) eventMap.get("avg_val");
        
        Random rd = new Random();
                
        StringBuilder sb = new StringBuilder();
        sb.append("==============================================================================");
        
        epService = EPServiceProviderManager.getDefaultProvider();
        String currentEvent = (String) epService.getEPRuntime().getVariableValue("CurrentEvent");
        if(currentEvent.equals("CRITICAL")) { 
        	epService.getEPRuntime().setVariableValue("CurrentEvent", "CRITICAL-COMPLETE");
        	
        }
        
        if(currentEvent.equals("CRITICAL-AFTER") || currentEvent.equals("CRITICAL-COMPLETE")) { 
        	this.samplingCount = 3;
        	sb.append("\n 1) Etching Equipment's Actual Metrology : "+ samplingCount + " Sampling Wafers");
        	sb.append("\n                                   	   Wafer #1 CD [" + (100 + rd.nextInt(250)) + " nm]");
        	sb.append("\n                                   	   Wafer #2 CD [" + (100 + rd.nextInt(250)) + " nm]");
        	sb.append("\n                                   	   Wafer #3 CD [" + (100 + rd.nextInt(250)) + " nm]");
        }
        else {
        	sb.append("\n 1) Etching Equipment's Actual Metrology : "+ samplingCount + " Sampling Wafer #1 CD [" + (100 + rd.nextInt(250)) + " nm]");
        }
        sb.append("\n 2) [VM MONITOR] Virtual Metrology [10 Wafers' Average CD = " + (Math.round(avg*100)/100.0) + " nm]");
        sb.append("\n 3) Calling Virtual Metrology Model Update in Etching Process Equipment");
        sb.append("\n==============================================================================");
        Thread.sleep(1000);
        
        
        
        LOG.debug(sb.toString());
    }
    
}