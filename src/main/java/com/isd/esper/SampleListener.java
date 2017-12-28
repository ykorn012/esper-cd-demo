package com.isd.esper;

//EPL (Event Processing Engine)에서 정의된 조건을 만족할 때, 처리하는 프로그램

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SampleListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		EventBean event = null;

		event = newEvents[0];
		System.out
				.println(event.get("item") + " : 갯수 = " + event.get("count(*)") + ", 평균 = " + event.get("avg(price)"));
	}
}