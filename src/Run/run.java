package Run;

import java.io.IOException;
import java.util.Random;

import Structure.Database;

public class run {

	public static void main(String[] args) throws IOException {
		Database d = new Database("consumidor", "producto");
		Random rand = new Random(8);
		for(int i = 0; i < 100000; i++) {
			d.AddElement(i + " " + rand.nextInt(1000000) + "-" + rand.nextInt(10) + " " + rand.nextInt(1000000));
		}
		for(int i = 0; i < 100000; i++) {
			d.AddElement(i + " " + rand.nextInt(1000000) + " " + rand.nextInt(1000000) + " " + rand.nextInt(1000000));
		}
		d.EndWriting();
		d.SortConsumer("rut");
		d.SortProduct("id");
	}
}
