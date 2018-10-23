package Structure;

import java.util.HashMap;

public class Consumer extends AbstractNode {
	
	public Consumer(int id, String rut, int pointsA){
		d = new HashMap<String, Object>();
		d.put("id", id);
		d.put("rut", rut);
		d.put("puntosAcumulados", pointsA);
	}

	@Override
	public String printData() {
		String r = d.get("id") + " " + d.get("rut") + " " + d.get("puntosAcumulados");
		return r;
	}

	@Override
	public boolean isConsumer() {
		return true;
	}

	@Override
	public boolean isProduct() {
		return false;
	}
}