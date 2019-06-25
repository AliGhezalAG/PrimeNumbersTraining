import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

	static DecimalFormat df = new DecimalFormat("#.##");
	
	public static  int[] countPrimes(int prime, int[] counter) {
		switch(prime%10) {
			case 1:
				counter[0] ++;
				break;
			case 3:
				counter[1] ++;
				break;
			case 7:
				counter[2] ++;
				break;
			case 9:
				counter[3] ++;
				break;
		}
		return counter;
	}
	
	public static List<Integer> getPrimesList(File file){
		List<Integer> list = new ArrayList<Integer>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				list.add(Integer.parseInt(text));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}

		return list;
	}
	
	public static void printLine(int[] tab) {
		float total = tab[0] + tab[1] + tab[2] + tab[3];
		System.out.print(df.format(tab[0]*100/total) + "  ");
		System.out.print(df.format(tab[1]*100/total) + "  ");
		System.out.print(df.format(tab[2]*100/total) + "  ");
		System.out.println(df.format(tab[3]*100/total) + "  ");
	}

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Ali Ghezal\\eclipse-workspace\\PrimeNumbers\\primes.txt");
		List<Integer> primesList = getPrimesList(file);

		int[] endWithOneCounter = {0, 0, 0, 0};
		int[] endWithThreeCounter = {0, 0, 0, 0};
		int[] endWithSevenCounter = {0, 0, 0, 0};
		int[] endWithNineCounter = {0, 0, 0, 0};

		ListIterator<Integer> iterator = primesList.listIterator(); 

		while(iterator.hasNext()) {
			switch(iterator.next()%10) {
			case 1:
				if(iterator.hasNext()) {
					endWithOneCounter = countPrimes(iterator.next(), endWithOneCounter);
					iterator.previous();
				}
				break;
			case 3:
				if(iterator.hasNext()) {
					endWithThreeCounter = countPrimes(iterator.next(), endWithThreeCounter);
					iterator.previous();
				}
				break;
			case 7:
				if(iterator.hasNext()) {
					endWithSevenCounter = countPrimes(iterator.next(), endWithSevenCounter);
					iterator.previous();
				}
				break;
			case 9:
				if(iterator.hasNext()) {
					endWithNineCounter = countPrimes(iterator.next(), endWithNineCounter);
					iterator.previous();
				}
				break;
			default:
				iterator.next();
			}			
		}

		printLine(endWithOneCounter);
		printLine(endWithThreeCounter);
		printLine(endWithSevenCounter);
		printLine(endWithNineCounter);

	}

}
