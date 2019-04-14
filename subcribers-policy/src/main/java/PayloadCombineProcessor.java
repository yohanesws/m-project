import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class PayloadCombineProcessor implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub
		Map payload = new HashMap();
		if (eventContext.getMessage().getPayload() instanceof List) {
			for(Object backendReplay:(List)eventContext.getMessage().getPayload()) {
				payload.putAll((Map)backendReplay);
			}
		}else if (eventContext.getMessage().getPayload() instanceof Map){
			payload=(Map) eventContext.getMessage().getPayload();
		}
		Map finalResponse = new HashMap();
		finalResponse.put("response", payload);
		return finalResponse;
	}

}
