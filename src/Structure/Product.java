package Structure;

import java.util.HashMap;

public class Product extends AbstractNode {
	
	public Product(int id, int price, int pointsN, int pointsR) {
		d = new HashMap<String, Object>();
		d.put("id", id);
		d.put("precio", price);
		d.put("puntosNec", pointsN);
		d.put("puntosRec", pointsR);
	}
	
	@Override
	public String printData() {
		String r = d.get("id") + " " + d.get("precio") + " " + d.get("puntosNec") + " " + d.get("puntosRec");
		return r;
	}

	@Override
	public boolean isConsumer() {
		return false;
	}

	@Override
	public boolean isProduct() {
		return true;
	}
}