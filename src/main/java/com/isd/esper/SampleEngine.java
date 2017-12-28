package com.isd.esper;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.isd.esper.SampleEvent;
import com.isd.esper.SampleListener;

public class SampleEngine {
	public static void main(String[] args) {
		Configuration config = null;
		EPServiceProvider service = null;
		EPStatement stat = null;
		String epl = null;
		SampleListener listener = null;
		EPRuntime runtime = null;

		// --- 처리할 Event를 등록하여 Esper용 서비스를 생성 합니다.
		config = new Configuration();
		config.addEventType("SampleEvent", SampleEvent.class.getName());
		service = EPServiceProviderManager.getDefaultProvider(config);

		// --- EPL(Event Processing Language)을 사용하여 Statement를 생성 합니다.
		// --- 지난 3초 동안 발생한 이벤트로 전체 갯수와 가격 평균을 구합니다.
		epl = "select item, count(*), avg(price) from SampleEvent.win:time(3 sec)";
		stat = service.getEPAdministrator().createEPL(epl);

		listener = new SampleListener();
		stat.addListener(listener);

		// --- Event를 발생시켜 봅니다.
		runtime = service.getEPRuntime();
		for (int i = 1; i <= 20; i++) {
			runtime.sendEvent(new SampleEvent("item_" + i, 10.0 * i));
			try {
				Thread.sleep(300);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
