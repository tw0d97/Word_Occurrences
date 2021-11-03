package application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordManagerTestCase {
	
	WordManager test = new WordManager();

	@Test
	void testWordManager() {
		//Show array is empty
		Assertions.assertTrue(WordManager.wordsArray.isEmpty());
		Assertions.assertTrue(WordManager.occurrencesArray.isEmpty());
		//Call methods in WordManager class
		WordManager.countWords("https://web.ics.purdue.edu/~gchopra/class/public/pages/webdesign/05_simple.html", "body");
		//Show arrays are no longer empty
		Assertions.assertFalse(WordManager.wordsArray.isEmpty());
		Assertions.assertFalse(WordManager.occurrencesArray.isEmpty());
		//Show that the output of methods are as predicted
		Assertions.assertEquals("THE", WordManager.wordsArray.get(0));
		Assertions.assertEquals("7", WordManager.occurrencesArray.get(0));

	}

}
