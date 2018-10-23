package Structure;

import java.util.Map;

public abstract class AbstractNode implements Nodo {
	protected Map<String, Object> d;

	@Override
	public Object getData(String request) {
		return d.get(request);
	}
}