package langModel;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class VocabularyTest {

    @Test
    public void getSize() {
        VocabularyInterface vocab1 = new Vocabulary();
        vocab1.readVocabularyFile("C:\\Users\\OZKAN\\Documents\\DUT\\S3\\Modelisation Mathematiques\\Projets\\Etudiant-mm_langModel\\lm\\small_corpus\\vocabulary1_in.txt");
        assertEquals(12,vocab1.getSize());
    }

    @Test
    public void getWords() {
        VocabularyInterface vocab1 = new Vocabulary();
        vocab1.readVocabularyFile("C:\\Users\\OZKAN\\Documents\\DUT\\S3\\Modelisation Mathematiques\\Projets\\Etudiant-mm_langModel\\lm\\small_corpus\\vocabulary1_in.txt");
        Set<String> verif = new HashSet<>();
        verif.add("<s>");
        verif.add("</s>");
        verif.add("antoine");
        verif.add("écoute");
        verif.add("thom");
        verif.add("denis");
        verif.add("une");
        verif.add("autre");
        verif.add("chanson");
        verif.add("elle");
        verif.add("de");
        verif.add("lionel");
        assertTrue(verif.containsAll(vocab1.getWords()));
    }

    @Test
    public void contains() {
        VocabularyInterface vocab1 = new Vocabulary();
        vocab1.readVocabularyFile("C:\\Users\\OZKAN\\Documents\\DUT\\S3\\Modelisation Mathematiques\\Projets\\Etudiant-mm_langModel\\lm\\small_corpus\\vocabulary1_in.txt");
        assertTrue(vocab1.contains("lionel"));
    }

    @Test
    public void addWord() {
        VocabularyInterface vocab1 = new Vocabulary();
        vocab1.readVocabularyFile("C:\\Users\\OZKAN\\Documents\\DUT\\S3\\Modelisation Mathematiques\\Projets\\Etudiant-mm_langModel\\lm\\small_corpus\\vocabulary1_in.txt");
        vocab1.addWord("Outatime");
        assertTrue(vocab1.contains("Outatime"));
    }

    @Test
    public void removeWord() {
        VocabularyInterface vocab1 = new Vocabulary();
        vocab1.readVocabularyFile("C:\\Users\\OZKAN\\Documents\\DUT\\S3\\Modelisation Mathematiques\\Projets\\Etudiant-mm_langModel\\lm\\small_corpus\\vocabulary1_in.txt");
        vocab1.removeWord("lionel");
        assertFalse(vocab1.contains("lionel"));
    }
}