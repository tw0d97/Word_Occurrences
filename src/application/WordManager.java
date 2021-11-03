package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WordManager {
	
	static ArrayList<String> wordsArray = new ArrayList<String>();
	static ArrayList<String> occurrencesArray = new ArrayList<String>();
	
	public static void makeLabel() {
		
		Main.wordsLabel.setText(wordsArray.get(0));
		wordsArray.remove(0);
		Main.occurrencesLabel.setText(occurrencesArray.get(0) + " Times");
		occurrencesArray.remove(0);
		
	}
	
	public static void showTop20() {
		
			int totalOccurrences = 0;
			Main.wordsLabel.setText("1: " + wordsArray.get(0) + " \n2: " + wordsArray.get(1) + " \n3: " + wordsArray.get(2) + " \n4: " + wordsArray.get(3) + " \n5: " + wordsArray.get(4) + "\n6: " + wordsArray.get(5) + " \n7: " + wordsArray.get(6) + " \n8: " + wordsArray.get(7) + " \n9: " + wordsArray.get(8) + " \n10: " + wordsArray.get(9) + "\n11:  " + wordsArray.get(10) + " \n12: " + wordsArray.get(11) + " \n13: " + wordsArray.get(12) + " \n14: " + wordsArray.get(13) + " \n15: " + wordsArray.get(14) + "\n16:  " + wordsArray.get(15) + " \n17: " + wordsArray.get(16) + " \n18: " + wordsArray.get(17) + " \n19: " + wordsArray.get(18) + " \n20: " + wordsArray.get(19));
			for(int i = 0; i < occurrencesArray.size(); i++) {
				totalOccurrences += Integer.parseInt(occurrencesArray.get(i));
			}
			String occurrencesString = Integer.toString(totalOccurrences);
			Main.occurrencesLabel.setText(occurrencesString + " In Total");
	}
	
	  
	public static void countWords(String url, String element) {
			
			//Passes url to wordMap method
			Map<String, Integer> wordMap = wordMap(url, element); 
			// Passes wordMap to sort method
			List<Entry<String, Integer>> list = sort(wordMap); 
		
			// Loop through each entry in sorted list
	        for (Map.Entry<String, Integer> entry : list) {
	        	wordsArray.add(entry.getKey());
	        	occurrencesArray.add(entry.getValue().toString());
	        }
	}
	
	public static Map<String, Integer> wordMap(String url, String element) {
		
		Map<String, Integer> wordMap = new HashMap<>();
		String line;
		Document doc = null;
		Pattern pattern = Pattern.compile("\\s+");
		//Jsoup library to connect to given URL
		try {
			doc = Jsoup.connect(url).timeout(60000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements body = doc.select(element);
		
		// Loops through each element in body and selects text in each paragraph html tag named poem
		for(Element e : body.select("p")) {
			line = e.select("p").text();
			// Changes all text to upper case to account for case sensitivity
			line = line.toUpperCase();
			// I know this is bad code but it gave me errors whenever trying to replace multiple RegEx in one line
			// Please let me know how I could improve these lines
			line = line.replace("—", " ");
			line = line.replace("!", "");
			line = line.replace("”", "");
			line = line.replace("“", "");
			line = line.replace(".", "");
			line = line.replace(",", "");
			line = line.replace(";", "");
			line = line.replace("’", "");
			// Splits all words separated by spaces
			String[] words = pattern.split(line);
			// Loops through each word, adds 1 to frequency if exists, else adds word to map
			for (String word : words) {
				if(wordMap.containsKey(word)) {
					wordMap.put(word, (wordMap.get(word) + 1));
				} else {
					wordMap.put(word, 1);
				}
			}
		}
		return wordMap;
	}

	public static List<Entry<String, Integer>> sort(Map<String, Integer> wordMap) {
		// Puts wordMap into entries set
		Set<Entry<String, Integer>> entries = wordMap.entrySet();
		// Puts entries set into an ArrayList
		List<Entry<String, Integer>> list = new ArrayList<>(entries);
		// Sorts list in descending order
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	        @Override
	        public int compare(Map.Entry<String, Integer> object1, 
	                           Map.Entry<String, Integer> object2) {
	            return (object2.getValue()).compareTo(object1.getValue());
	        }
	    });
		return list;
}
}
