package gr.dgk.algorithms.genomics;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to create Arrays with the DNA letter positions in the
 * sequence.
 * 
 * @author dgkelaidonis
 *
 */
public class SequenceClassifier {
	public static void main(String[] args) {
		String sequence = "ATGCAATCGTGTAAACTA";
		List<Integer> A = new LinkedList<Integer>();
		List<Integer> C = new LinkedList<Integer>();
		List<Integer> T = new LinkedList<Integer>();
		List<Integer> G = new LinkedList<Integer>();
		int index = 0;
		for (char base : sequence.toCharArray()) {
			switch (base) {
			case 'A':
				A.add(index);
				break;
			case 'C':
				C.add(index);
				break;
			case 'T':
				T.add(index);
			case 'G':
				G.add(index);
			}
		}

		System.out.println("A index: ");
		for (int Ap : A) {
			System.out.println(Ap + ",");
		}
		System.out.println("C index: ");
		for (int Cp : C) {
			System.out.println(Cp + ",");

		}
		System.out.println("T index: ");
		for (int Tp : T) {
			System.out.println(Tp + ",");
		}
		System.out.println("G index: ");
		for (int Gp : G) {
			System.out.print(Gp + ",");
		}

	}
}