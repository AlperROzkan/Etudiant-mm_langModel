package langModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Class Vocabulary: class implementing the interface VocabularyInterface.
 * 
 * @author ... (2017)
 *
 */
public class Vocabulary implements VocabularyInterface {
	/**
	 * The set of words corresponding to the vocabulary.
	 */
	protected Set<String> vocabulary;

	
	/**
	 * Constructor.
	 */
	public Vocabulary(){
		this.vocabulary = new HashSet<>();
	}
	
	
	@Override
	public int getSize() {
		return this.vocabulary.size();
	}

	@Override
	public Set<String> getWords() {
		Set<String> renvoi = new HashSet<>();
		for (String s : this.vocabulary) {
			renvoi.add(s);
		}
		return renvoi;
	}

	@Override
	public boolean contains(String word) {
		return this.vocabulary.contains(word);
	}

	@Override
	public void addWord(String word) {
		this.vocabulary.add(word);
	}

	@Override
	public void removeWord(String word) {
		this.vocabulary.remove(word);
	}

	@Override
	/*
	 * On recupere le vocabulaire de l'ensemble de ngramme
	 * On ne l'implemente pas
	 */
	public void scanNgramSet(Set<String> ngramSet) {
		// TODO Auto-generated method stub
	}

	@Override
	public void readVocabularyFile(String filePath) {
		List<String> save = MiscUtils.readTextFileAsStringList(filePath);
		this.vocabulary.addAll(save);
	}

	@Override
	public void writeVocabularyFile(String filePath) {
		for (String s : this.vocabulary) {
			MiscUtils.writeFile(s,filePath,true);
		}
		
	}

}
