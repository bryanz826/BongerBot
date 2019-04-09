package com.example.text.command_list;

import java.util.Arrays;

import com.example.text.command.Command.CommandReference;
import com.example.text.response.ResponseManager;

/*
 * Translates text by adding a [B] in front
 */
public class BTranslator
{
	public static void run(ResponseManager rm, String arguments)
	{
		/*
		 * Title Text
		 */
		String title = "Nice! Here is your result:";

		/*
		 * Translated Text
		 */
		String translatedText = "";

		// initialize important characters
		Character[] importantChars = new Character[] { 'l', 'r', 'a', 'e', 'i', 'o', 'u', 'w' };

		// initialize replacement prefixes
		String[] replacementPrefix = new String[] { "sq", "st" };

		// check if input is all caps
		boolean allCaps = true;

		String cleanInput = arguments.replaceAll("[^a-zA-Z]", "");
		final float checkPercent = 0.05f;
		int checkCount = (int) (cleanInput.length() * checkPercent);

		for (int i = 0; i < 50 && i < cleanInput.length(); i++) {
			if (!Character.isUpperCase(cleanInput.charAt(i))) {
				if (--checkCount <= 0) {
					allCaps = false;
					break;
				}
			}
		}

		// translate text
		String[] splitText = arguments.split("\\s+");

		for (String word : splitText) {
			// obtain any potential extras at the beginning
			String extras = "";
			for (int i = 0; i < word.length(); i++) {
				if (Character.isLetter(word.charAt(i))) {
					extras = word.substring(0, i);
					word = word.substring(i);
					break;
				}
			}

			// add [B] and appropriate rest of sentence
			translatedText += extras + (Character.isUpperCase(word.charAt(0)) ? "B" : "b");
			if (!Arrays.asList(importantChars).contains(Character.toLowerCase(word.charAt(0)))) { // not important
																									// character
				search: {
					for (String rp : replacementPrefix) {
						if (word.toLowerCase().startsWith(rp)) {
							translatedText += word.substring(rp.length());
							break search;
						}
					}
					translatedText += word.substring(1);
				}
			} else { // important character
				if (word.length() == 1) {
					if (!allCaps) translatedText += word.toLowerCase();
					else translatedText += word.toUpperCase();
				} else if (Character.isUpperCase(word.charAt(1))) {
					translatedText += word;
				} else {
					translatedText += word.toLowerCase();
				}

			}

			translatedText += " "; // add space between words
		}

		/*
		 * Add to UI
		 */
		rm.getUI().addField(title, translatedText, false);
	}

	public static void help(ResponseManager rm)
	{
		/*
		 * Field
		 */
		String fieldTitle = commandFormat();
		String fieldDescription = "I thoughtfully add a 'B' in front of every word.";
		String randomComment = "";
		if (Math.random() < 0.5) randomComment = "Go BONGER™ with the 'B' translator!";
		else if (Math.random() < 1) randomComment = "The 'B' character is very magical";
		fieldDescription += randomComment;

		rm.getUI().addField(fieldTitle, fieldDescription, false);
	}

	public static void error(ResponseManager rm)
	{
		/*
		 * Field
		 */
		String fieldTitle = "Did you mean:";
		String fieldDescription = commandFormat()
				+ "Please follow the format described above. See\n"
				+ "<help> for more details on this command.";

		rm.getUI().addField(fieldTitle, fieldDescription, false);
	}

	public static String commandFormat()
	{
		return String.format("``` %s [raw text]```", CommandReference.B_TRANS.toString());
	}
}
