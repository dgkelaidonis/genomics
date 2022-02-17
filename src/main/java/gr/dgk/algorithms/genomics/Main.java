package gr.dgk.algorithms.genomics;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Main {

	// TODO:
	public static char[] getDnaThreeToFiveFromFiveToThree(String fiveToThreeSequence) {
		// 5'-3': A T C G
		// 3'-5': T A G C
		Consumer<String> sequenceConsumer = sequenceLine -> {
			if (sequenceLine.length() != 0 && !sequenceLine.contains("ATGC")) {
				String threeToFiveSequence = "";
				for (char c : fiveToThreeSequence.toCharArray()) {
					threeToFiveSequence+=(c=='A')?"T":(c=='T')?"A":(c=='C')?
				}
			}
		};
		return null;
	}

	// TODO:
	public static char[] get5to3From3to5(String _3to5sequence) {
		// 3'-5': T A G C
		// 5'-3': A T C G
		return null;
	}

	// TODO:
	public static char[] getRNAFrom5to3(String _5to3sequence) {
		// 5'-3': A T C G
		// RNA : U A G C
		return null;
	}

	/**
	 * Input: ATTACGGC
	 * 
	 * Encoding pattern: a OR t = 0 cOR g = 1 |AT|TA|CG|GC| = |0.0|0.1|2|3| AT=0
	 * TA=1 CG=2 GC=3
	 * 
	 * AA, AT, AC, AG, TT, TA, TC, TG, CC, CA, CT, CG, GG, GA, GT, GC
	 * 
	 * 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F
	 * 
	 * PATTERN=1F (ATGC)
	 */

	/**
	 * A[1,23,34,61,71] T[2,3,15,18,28,29]
	 * 
	 */
	public static void main(String[] args) {
		String pattern = "ATTAAAGGTTTATACCTTCCCAGGTAACAAACCAACCAACTTTCGATCTCTTGTAGATCTGTTCTCTAAA";
		String fileName = "data/Sars-Cov2-FastData.txt";
		String encodedPattern = "";
		String encodedSequence = "";

		/*
		 * consume data: input validation for FASTA data format (i.e., ignore '>' or
		 * ';')
		 */
		Consumer<String> fastaConsumer = fastaLine -> {
			if (fastaLine.length() != 0 && !fastaLine.startsWith(">") && !fastaLine.startsWith(";")) {
				char[] fastaLineChars = fastaLine.toCharArray();
				int i = 0;
				while (i + 1 < fastaLine.length()) {
					encodedSequence += Character.toString(encodeBases(
							Character.toString(fastaLineChars[i]) + Character.toString(fastaLineChars[i + 1])));
					i++;
				}
			}
		};

		/* Stream data, consume & process */
		try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			stream.forEach(fastaConsumer);

		} catch (IOException e) {
			e.printStackTrace();
		}

		char[] patternChars = pattern.toCharArray();
		int i = 0;
		while (i < pattern.length()) {
			String toBeEncoded = Character.toString(patternChars[i]) + Character.toString(patternChars[i + 1]);
			System.out.print(toBeEncoded);
			String encoded = Character.toString(encodeBases(toBeEncoded));
			encodedPattern += encoded;
			System.out.print(" --> " + encoded + "\n");
			i += 2;
		}

		/* search */
		System.out.println(pattern.length() + " vs. " + encodedPattern.length());
		char[] key = new char[] { '6', '6' };
		int occ = 0;
		for (int k = 0; k < encodedPattern.length(); k++) {
			if (encodedPattern.length() >= k + key.length) {
				if (Arrays.compare(encodedPattern.toCharArray(), k, k + key.length, key, 0, key.length) == 0) {
					occ++;
				}
			}
		}
		System.out.println("occ=" + occ);
	}

	/**
	 * encoder; Business logic:
	 * 
	 * AA, AT, AC, AG, TT, TA, TC, TG, CC, CA, CT, CG, GG, GA, GT, GC
	 * 
	 * 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F
	 * 
	 * @param bases: base[n]+base[n+1]
	 * @return The character that corresponds to the two bases
	 */
	public static char encodeBases(String bases) {
		char c = '\n';
		switch (bases) {
		case "AA":
			c = '0';
			break;
		case "AT":
			c = '1';
			break;
		case "AC":
			c = '2';
			break;
		case "AG":
			c = '3';
			break;
		case "TT":
			c = '4';
			break;
		case "TA":
			c = '5';
			break;
		case "TC":
			c = '6';
			break;
		case "TG":
			c = '7';
			break;
		case "CC":
			c = '8';
			break;
		case "CA":
			c = '9';
			break;
		case "CT":
			c = 'A';
			break;
		case "CG":
			c = 'B';
			break;
		case "GG":
			c = 'C';
			break;
		case "GA":
			c = 'D';
			break;
		case "GT":
			c = 'E';
			break;
		case "GC":
			c = 'F';
			break;
		default:
			break;
		}
		return c;
	}
}