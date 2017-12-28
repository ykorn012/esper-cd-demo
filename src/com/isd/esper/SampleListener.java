package com.isd.esper.esper;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SampleListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = null;

		event = newEvents[0];
		//System.out.println("Row : " + event.get("item") + " : 갯수 = " + event.get("price"));
		System.out.println(event.get("item") + " : 갯수 = " + event.get("count(*)") + ", 평균 = " + event.get("avg(price)"));
	}
}
