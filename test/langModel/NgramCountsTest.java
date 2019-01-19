package langModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

import static org.junit.Assert.*;

public class NgramCountsTest {
    @Test
    public void getMaximalOrder() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour OMalley", 2);
        ngramCounts.setCounts("n'agressez point </s>",1);

        assertEquals(3,ngramCounts.getMaximalOrder());

    }

    @Test
    public void getNgramCounterSize() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);

        assertEquals(2, ngramCounts.getNgramCounterSize());
    }


    @Test
    public void getTotalWordNumber() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);

        assertEquals(4, ngramCounts.getTotalWordNumber());
    }

    @Test
    public void getNgrams() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);
        Set<String> ngramCountsSet = ngramCounts.getNgrams();

        Set<String> monSet = new HashSet<>();
        monSet.add("<s> Bonjour");
        monSet.add("n'agressez point");

        assertTrue(monSet.containsAll(ngramCountsSet));
    }

    @Test
    public void getCounts() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);

        assertEquals(2, ngramCounts.getCounts("<s> Bonjour"));
        assertEquals(0, ngramCounts.getCounts("Im gonna say the B word"));
    }

    @Test
    public void incCounts() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);

        ngramCounts.incCounts("<s> Bonjour");
        assertEquals(3, ngramCounts.getCounts("<s> Bonjour"));
    }

    @Test
    public void setCounts() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);

        assertEquals(2, ngramCounts.getCounts("<s> Bonjour"));
    }


    @Test
    public void scanTextFile() {
        NgramCounts ngramCounts = new NgramCounts();

        VocabularyInterface vocabulary = new Vocabulary();
        vocabulary.addWord("a");
        vocabulary.addWord("b");
        vocabulary.addWord("c");
        vocabulary.addWord("d");
        vocabulary.addWord("e");
        vocabulary.addWord("f");
        vocabulary.addWord("g");

        ngramCounts.scanTextFile("langModel/scanTextFile.txt",vocabulary, 2);

        assertEquals(7, ngramCounts.getTotalWordNumber());
    }

    /**
     * Method saving the current n-gram counts in a file.
     * The file should contain one n-gram per line, each line being made of the n-gram and its count
     * separated by a tabulation character '\t'.
     *
     * @param filePath the path of the file used to save the counts of the n-grams.
     */
    @Test
    public void writeNgramCountFile() {
        NgramCounts ngramCounts = new NgramCounts();
        ngramCounts.setCounts("<s> Bonjour", 2);
        ngramCounts.setCounts("n'agressez point",1);
        ngramCounts.writeNgramCountFile("langModel/ngramCounts.txt");

        List<String> ngramCount = MiscUtils.readTextFileAsStringList("langModel/ngramCounts.txt");
        assertEquals(2, Integer.parseInt(ngramCount.get(0)));
        assertEquals(1, Integer.parseInt(ngramCount.get(1)));
    }


    /**
     * Method reading the current n-gram counts from a file.
     * The file should contain one n-gram per line, each line being made of the n-gram and its count
     * separated by a tabulation character '\t'.
     * The method should also set the maximum encountered n-gram length (i.e. the NgramCountsInterface order).
     *
     * @param filePath the path of the file in which the counts of the n-grams are saved.
     */
    @Test
    public void readNgramCountsFile() {

    }
}