package langModel;


import static langModel.NgramUtils.getHistory;

/**
 * Class LaplaceLanguageModel: class inheriting the class NaiveLanguageModel by creating 
 * a n-gram language model using a Laplace smoothing.
 * 
 * @author ... (2017)
 *
 */
public class LaplaceLanguageModel extends NaiveLanguageModel {

	/**
	 * Constructor.
	 *
	 * @param ngramCounts
	 * @param vocabulary
	 */
	public LaplaceLanguageModel(NgramCounts ngramCounts, Vocabulary vocabulary) {
		super(/*ngramCounts, vocabulary*/);
	}

	@Override
	public Double getNgramProb(String ngram) {
		String newNgram = "";
		String[] mots = ngram.split("\\s+");
		double res = 0;

		for (String mot : mots  ) {
			if (vocabulary.getWords().contains(mot)){
				newNgram += mot+" ";
			} else {
				newNgram += Vocabulary.OOV_TAG+" ";
			}
		}

		newNgram.trim();

		if (mots.length == 1 && newNgram == "<s>"){
			res = 1;
		} else if (mots.length == 1 && newNgram != "<s>"){
			res = ngramCounts.getCounts(newNgram)+1/ngramCounts.getTotalWordNumber()+mots.length-1;
		} else if (mots.length > 1){
			res = ngramCounts.getCounts(newNgram)+1/ngramCounts.getCounts(getHistory(newNgram, mots.length))+mots.length-1;
		}


		return res;
	}
}
