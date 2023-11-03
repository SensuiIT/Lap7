package set_student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MyWordCount {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";

	private List<String> words = new ArrayList<>();

	public MyWordCount() {
		try {
			this.words.addAll(Utils.loadWords(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public List<WordCount> getWordCounts() {
		// TODO
		List<WordCount> result = new ArrayList<>();

		for (String word : words) {
			boolean wordFound = false;

			for (WordCount wc : result) {
				if (wc.getWord().equals(word)) {
					wc.incrementCount();
					wordFound = true;
					break;
				}
			}

			if (!wordFound) {
				result.add(new WordCount(word, 1));
			}
		}

		return result;
	}
	// Returns the words that their appearance are 1, do not consider duplidated
	// words
	public Set<String> getUniqueWords() {
		Set<String> uniqueWords = new HashSet<String>();
		Set<String> nonUniqueWords = new HashSet<String>();
		for(String word : words) {
			if(!nonUniqueWords.contains(word)) {
				if(uniqueWords.contains(word)) {
					uniqueWords.remove(word);
					nonUniqueWords.add(word);
				}
				else {
					uniqueWords.add(word);
				}
			}
		}
		return uniqueWords;
	}

	// Returns the words in the text file, duplicated words appear once in the
	// result
	public Set<String> getDistinctWords() {
		// TODO
		Set<String> DistinctWords = new HashSet<String>();
		for(WordCount wc : getWordCounts()) {
			if(wc.getCount()>1) {
				DistinctWords.add(wc.getWord());
			}
		}
		return DistinctWords;
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public Set<WordCount> printWordCounts() {
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// TODO Auto-generated method stub
				return o1.getWord().compareTo(o2.getWord());	
			}
			
		});
		re.addAll(this.getWordCounts());
		return re;
		
		
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according descending order of occurences
	// Example: Bug - 10, An - 3, Nam - 2.
	public Set<WordCount> exportWordCountsByOccurence() {
		// TODO
		Set<WordCount> re = new TreeSet<>(new Comparator<WordCount>() {

			@Override
			public int compare(WordCount o1, WordCount o2) {
				// TODO Auto-generated method stub
				int re = o2.getCount() - o1.getCount();
				if(re==0) {
					return o1.getWord().compareTo(o2.getWord());
				}
				return re;
			}
			
		});
		re.addAll(this.getWordCounts());
		return re;
	}

	// delete words begining with the given pattern (i.e., delete words begin with
	// 'A' letter
	public Set<String> filterWords(String pattern) {
		// TODO
		Set<String> filter = new HashSet<String>();
		for(String word : words)
		{
			if(!word.startsWith(pattern)) {
				filter.add(word);
			}
		}
		return filter;
	}
	public static void main(String[] args) {
		MyWordCount MWC = new MyWordCount();
		System.out.println(MWC.getWordCounts());
		System.out.println(MWC.getUniqueWords());
		System.out.println(MWC.getDistinctWords() );
		System.out.println(MWC.printWordCounts() );
		System.out.println(MWC.exportWordCountsByOccurence());
		System.out.println(MWC.filterWords("A"));
	}
}
