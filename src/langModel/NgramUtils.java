package langModel;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class NgramUtils: class containing useful functions to deal with n-grams.
 *
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class NgramUtils {

	/**
	 * Method counting the number of words in a given sequence
	 * (the sequence can be a n-gram or a sentence).
	 *
	 * @param sequence the sequence to consider.
	 * @return the number of words of the given sequence.
	 */
	public static int getSequenceSize (String sequence) {
		String[] decoupe = sequence.split("\\s+"); // Decoupe sequence dans un tableau
		return decoupe.length; // Retourne la taille du tableau decoupe
	}


	/**
	 * Method parsing a n-gram and returning its history, i.e. the n-1 preceding words.
	 *
	 * Example:
	 *   let the ngram be "l' historique de cette phrase";
	 *   the history will be given for the last word of the ngram, here "phrase":
	 *   if the order is 2 then the history will be "cette";
	 *   if the order is 3 then it will be "de cette".
	 *
	 * @param ngram the n-gram to consider.
	 * @param order the order to consider for the n-gram.
	 * @return history of the given n-gram (the length of the history is order-1).
	 */
	public static String getHistory (String ngram, int order) {
		// Si l'ordre est de 1 on renvoie une chaine de caractères vide
		if (order==1)
			return "";

		String[] ngramTab = ngram.split("\\s+"); // On decoupe le ngram dans un tableau
		String historique[] = new String[order-1];
		int indexHist = 0;
		// On parcourt le Ngramme a partir des mots a prendre.
		for (int i = ngramTab.length-order; i<ngramTab.length-1;i++ ) {
		    historique[indexHist] = ngramTab[i];
		    indexHist++;
        }
		return String.join(" ", historique);
	}


	/**
	 * Method decomposing the given sentence into n-grams of the given order.
	 *
	 * This method will be used in the LanguageModelInterface class for computing
	 * the probability of a sentence as the product of the probabilities of its n-grams.
	 *
	 * Example
	 * given the sentence "a b c d e f g", with order=3,
	 * it will result in the following list:
	 * [a, a b, a b c, b c d, c d e, d e f, e f g]
	 *
	 * @param sentence the sentence to consider.
	 * @param order the maximal order for the n-grams to create from the sentence.
	 * @return the list of n-grams constructed from the sentence.
	 */
	public static List<String> decomposeIntoNgrams (String sentence, int order) {
		// On decompose la phrase dans un tableau de String
		String[] tabSentence = sentence.split("\\s+");

		// On cree la liste des ngrammes a retourner selon l'ordre
		List<String> ngramList = new ArrayList<>();
		int posj;

		// On parcourt le tableau contenant la phrase
		for(int i = 1; i <= tabSentence.length; i++) {
			// On regarde les mots avant
			posj = 1;
			for (int j = i-(order); j<=i; j++) {
				if (!(j<0)) {
					posj=j;
					break;
				}
			}
			ngramList.add(String.join(" ", Arrays.copyOfRange(tabSentence,posj, i))); // On ajoute les mots
		}
		return ngramList;
	}


	/**
	 * Method parsing the given sentence and generate all the combinations of ngrams,
	 * by varying the order n between the given minOrder and maxOrder.
	 *
	 * This method will be used in the NgramCount class for counting the ngrams
	 * occurring in a corpus.
	 *
	 * Algorithm (one possible algo...)
	 * initialize list of ngrams
	 * for n = minOrder to maxOrder (for each order)
	 * 	 for i = 0 to sentence.length-n (parse the whole sentence)
	 *     initialize ngram string parsedSentence
	 *     for j = i to i+n-1 (create a ngram made of the following sequence of words starting from i to i + the order size)
	 *       ngram = ngram + " " + sentence[j]
	 *     add ngramm to list ngrams
	 * return list ngrams
	 *
	 * Example
	 * given the sentence "a b c d e f g", with minOrder=1 and maxOrder=3, it will result in the following list:
	 * [a, b, c, d, e, f, g, a b, b c, c d, d e, e f, f g, a b c, b c d, c d e, d e f, e f g]
	 *
	 * @param sentence the sentence from which to generate n-grams.
	 * @param minOrder the minimal order of the n-grams to create.
	 * @param maxOrder the maximal order of the n-grams to create.
	 * @return a list of generated n-grams from the sentence.
	 */
	public static List<String> generateNgrams (String sentence, int minOrder, int maxOrder) {
		// initialize list of ngrams
		String[] parsedSentence = sentence.split("\\s+"); // On parse la phrase dans un tableau pour avoir les mots individuellement (initialize ngram string parsedSentence)
		List<String> ngrams = new ArrayList<>(); // Grosse liste contenant tous les ngrammes
		String subNgrams = "";

		//for n = minOrder to maxOrder (for each order)
		for (int n = minOrder; n<=maxOrder; n++) {
			//for i = 0 to sentence.length-n (parse the whole sentence)
			for (int i = 0; i<=parsedSentence.length-n; i++) {
				// for j = i to i+n-1 (create a ngram made of the following sequence of words starting from i to i + the order size)
				for (int j = i; j<i+n; j++) {
					// ngram = ngram + " " + sentence[j]
					subNgrams = subNgrams + " " + parsedSentence[j];
				}
				// add ngramm to list ngrams
				ngrams.add(subNgrams.trim());
				subNgrams = "";
			}
		}
		return ngrams;
	}

	/**
	 * Method parsing a sequence of words and returning the modified string where
	 * out-of-vocabulary words are replaced with the OOV tag.
	 *
	 * @param s the string to consider.
	 * @param vocab the vocabulary to consider.
	 * @return the sequence of words with OOV tags according to the vocabulary.
	 */
	public static String getStringOOV(String s, VocabularyInterface vocab) {
	    String[] tabS = s.split("\\s+");

	    for (int i = 0; i<tabS.length; i++) {
	        if (!vocab.contains(tabS[i])) {
	            tabS[i] = vocab.OOV_TAG;
            }
        }
		return String.join(" ", tabS);
	}

}
