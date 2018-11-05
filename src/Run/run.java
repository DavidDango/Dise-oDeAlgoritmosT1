package Run;

import java.io.IOException;
import java.util.Random;

import Structure.Database;

public class run {

	public static void main(String[] args) throws IOException {
		Database d;
		Random rand = new Random(8);
		for(int j = 1; j < 8; j++) {
			long timeRunC = 0;
			long timeRunP = 0;
			long timeRunSortC = 0;
			long timeRunSortP = 0;
			int temp = (int) Math.pow(10, j);
			for(int k = 0; k < 5; k++) {
				d = new Database("c" + j, "p" + j);
				final long timeA = System.currentTimeMillis();
				for(int i = 0; i < temp; i++) {
					d.AddElement(i + " " + rand.nextInt(1000000) + "-" + rand.nextInt(10) + " " + rand.nextInt(1000000));
				}
				final long timeB = System.currentTimeMillis();
				timeRunC = timeRunC + timeB - timeA;
				final long timeC = System.currentTimeMillis();
				for(int i = 0; i < temp; i++) {
					d.AddElement(i + " " + rand.nextInt(1000000) + " " + rand.nextInt(1000000) + " " + rand.nextInt(1000000));
				}
				final long timeD = System.currentTimeMillis();
				timeRunP = timeRunP + timeD - timeC;
				d.EndWriting();
				final long timeE = System.currentTimeMillis();
				d.SortConsumer("rut");
				final long timeF = System.currentTimeMillis();
				timeRunSortC = timeRunSortC + timeF - timeE;
				final long timeG = System.currentTimeMillis();
				d.SortProduct("precio");
				final long timeH = System.currentTimeMillis();
				timeRunSortP = timeRunSortP + timeH - timeG;
			}
			System.out.println("Average times (5 runs): " + temp);
			System.out.println("Average time insertion C: " + timeRunC/5);
			System.out.println("Average time insertion P: " + timeRunP/5);
			System.out.println("Average time sort C: " + timeRunSortC/5);
			System.out.println("Average time sort P: " + timeRunSortP/5);
			System.gc();
		}
	}
}
