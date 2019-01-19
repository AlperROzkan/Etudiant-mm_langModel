package langModel;


import java.util.*;


/**
 * Class NgramCounts: class implementing the interface NgramCountsInterface. 
 * 
 * @author N. Hernandez and S. Quiniou (2017)
 *
 */
public class NgramCounts implements NgramCountsInterface {
	/**
	 * The maximal order of the n-gram counts.
	 */
	protected int order;

	/**
	 * The map containing the counts of each n-gram.
	 */
	protected Map<String,Integer> ngramCounts;

	/**
	 * The total number of words in the corpus.
	 * In practice, the total number of words will be increased when parsing a corpus 
	 * or when parsing a NgramCountsInterface file (only if the ngram encountered is a unigram one).
	 */
	protected int nbWordsTotal;
	
	
	/**
	 * Constructor.
	 */
	public NgramCounts(){
		this.order = 0;
		this.ngramCounts = new HashMap<>();
		this.nbWordsTotal = 0;
	}


	/**
	 * Setter of the maximal order of the ngrams considered.
	 * 
	 * In practice, the method will be called when parsing the training corpus, 
	 * or when parsing the NgramCountsInterface file (using the maximal n-gram length encountered).
	 * 
	 * @param order the maximal order of n-grams considered.
	 */
	private void setMaximalOrder (int order) {
		this.order = order;
	}

	
	@Override
	public int getMaximalOrder() {
		return this.order;
	}

	
	@Override
	public int getNgramCounterSize() {
		return ngramCounts.size();
	}

	
	@Override
	public int getTotalWordNumber(){
		return nbWordsTotal;
	}
	
	
	@Override
	public Set<String> getNgrams() {
		Set res = ngramCounts.keySet();
		return res;
	}

	
	@Override
	public int getCounts(String ngram) {
		return ngramCounts.get(ngram);
	}
	

	@Override
	public void incCounts(String ngram) {
		int tmp;
	    if (ngramCounts.containsKey(ngram)){
            tmp = ngramCounts.get(ngram)+1;
		    ngramCounts.put(ngram, tmp);
        } else {
            ngramCounts.put(ngram,1);
        }
	}

	
	@Override
	public void setCounts(String ngram, int counts) {
		ngramCounts.put(ngram,counts);
	}


	@Override
	public void scanTextFile(String filePath, VocabularyInterface vocab, int maximalOrder) {
        List<String> mots;
        mots = MiscUtils.readTextFileAsStringList(filePath);
        String lesmots = "";
        for (String mot: mots) {
            if (vocab.getWords().contains(mot)){
                lesmots += mot+" ";
            } else {
                lesmots+= Vocabulary.OOV_TAG+" ";
            }
            nbWordsTotal += 1;
        }
        NgramUtils ngu = new NgramUtils();
        mots = ngu.generateNgrams(lesmots,1,maximalOrder);
        for (String mot: mots) {
           this.incCounts(mot);
        }
        this.order = maximalOrder;
	}

	
	@Override
	public void writeNgramCountFile(String filePath) {
        for (String key : ngramCounts.keySet()) {
            MiscUtils.writeFile(key +"\t"+ngramCounts.get(key), filePath, true);
        }
	}

	
	@Override
	public void readNgramCountsFile(String filePath) {
        List<String> mots = MiscUtils.readTextFileAsStringList(filePath);
        String[] couple;
        String[] nbngrams;
	    for (String mot: mots) {
	       couple =  mot.split("\\t");
	       this.setCounts(couple[0], Integer.parseInt(couple[1]));
	       nbngrams = couple[0].split("\\s");
	       if (nbngrams.length == 1){
               this.nbWordsTotal += Integer.parseInt(couple[1]);
           }
           if (nbngrams.length > this.order) {
	           this.order = nbngrams.length;
           }
        }
	}

}
