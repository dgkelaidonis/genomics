package gr.dgk.algorithms.genomics;

public class DNAsequence {

	public static char[] getFiveToThree(String threeToFiveSequence) {
		char[] fiveToThreeSequence = new char[threeToFiveSequence.length()];
		int i = 0;
		for (char base : threeToFiveSequence.toCharArray()) {
			fiveToThreeSequence[i] = findComplementary(base);
		}
		return fiveToThreeSequence;
	}

	public static char[] getThreeToFive(String threeToFiveSequence) {
		char[] fiveToThreeSequence = new char[threeToFiveSequence.length()];
		int i = 0;
		for (char base : threeToFiveSequence.toCharArray()) {
			fiveToThreeSequence[i] = findComplementary(base);
		}
		return fiveToThreeSequence;
	}

//	findChunk();
//
//	readFromFastaFileAsStram();
//
//	readFromFastaFileAsString();
//
//	@Override
//	toString();
//
//	toCharArray();

	private static char findComplementary(char X) {
		return null;
//		return (base='A')?'T':(base='T')?'A':(base='C')?'G':(base='G')?'C':' ';
	}
}
