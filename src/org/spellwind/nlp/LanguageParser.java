package org.spellwind.nlp;

/**
 * A class which parses simple command sentences sent by the player and turn
 * them into a format which the server can understand.
 * @author Graham Edgecombe
 */
public final class LanguageParser {
	
	/**
	 * The sentence currently being parsed.
	 */
	private final String sentence;
	
	/**
	 * Creates the language parser.
	 * @param sentence The sentence to parse.
	 */
	public LanguageParser(String sentence) {
		this.sentence = sentence;
	}
	
	/**
	 * Parses the sentence into output understandable by the MUD server.
	 */
	public void parse() {
		String[] words = sentence.split(" ");
		for(String word : words) {
			
		}
	}
	
}
