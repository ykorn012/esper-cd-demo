package com.isd.cep.subscriber;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.isd.cep.event.CDEvent;

/**
 * Wraps Esper Statement and Listener. No dependency on Esper libraries.
 */
@Component
public class MonitorEventSubscriber implements StatementSubscriber {
	
	/** Logger */
    private static Logger LOG = LoggerFactory.getLogger(MonitorEventSubscriber.class);
    private static int samplingCount = 10;

    /**
     * {@inheritDoc}
     */
    public String getStatement() {

        // Example of simple EPL with a Time Window
        //return "select avg(CD) as avg_val from CDEvent.win:time_batch(10 sec)";
    	
    	return "select avg(CD) as avg_val from CDEvent#length_batch(" + samplingCount + ")";
        
    }

    /**
     * Listener method called when Esper has detected a pattern match.
     */
    public void update(Map<String, Double> eventMap) throws Exception{ 

        // average temp over 5 secs
        Double avg = (Double) eventMap.get("avg_val");
        
        this.samplingCount = 15;
        
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================================================================================");
        sb.append("\n- [10 Sampling Wafers Metrology Monitoring] Etching Equipment #1 - [Wafers' Average CD = " + avg + "]");
        sb.append("\n- [Call Virtual Metrology Model] Etching Equipment #1");
        sb.append("\n=========================================================================================================");
        Thread.sleep(1000);

        LOG.debug(sb.toString());
    }
    
}