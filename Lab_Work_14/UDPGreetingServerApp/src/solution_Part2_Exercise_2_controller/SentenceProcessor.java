package solution_Part2_Exercise_2_controller;

/**
 * 
 * @Solution  Part2 Exercise_2
 * 
 * The SentenceProcessor class handles sentence operations like 
 * character, vowel, consonant, and punctuation counting, and integer
 * to byte array conversion.
 * 
 * @author Khalid
 */
public class SentenceProcessor {

    private static final int MAX_SIZE = 100000;
    private String sentence;

    /**
     * Constructs a SentenceProcessor object with the provided byte array as the input data.
     * The byte array is converted to a string representation of the sentence.
     *
     * @param byteData the byte array representing the sentence
     */
    public SentenceProcessor(byte[] byteData) {
        this.sentence = new String(byteData);
    }

    /**
     * Retrieves the sentence stored in the SentenceProcessor object.
     *
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * Converts the provided integer value to a byte array representation.
     *
     * @param value the integer value to convert
     * @return the byte array representation of the value
     */
    public byte[] convertToByteArray(int value) {
        String stringValue = String.valueOf(value);
        return stringValue.getBytes();
    }

    /**
     * Counts the total number of characters in the sentence.
     *
     * @return the total number of characters
     */
    public int countCharacters() {
        return sentence.length();
    }

    /**
     * Counts the total number of vowels in the sentence.
     *
     * @return the total number of vowels
     */
    public int countVowels() {
        int counter = 0;
        String lowercaseSentence = sentence.toLowerCase();
        for (int index = 0; index < lowercaseSentence.length(); index++) {
            char currentCharacter = lowercaseSentence.charAt(index);
            if (isVowel(currentCharacter)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts the total number of consonants in the sentence.
     *
     * @return the total number of consonants
     */
    public int countConsonants() {
        int counter = 0;
        String lowercaseSentence = sentence.toLowerCase();
        for (int index = 0; index < lowercaseSentence.length(); index++) {
            char currentCharacter = lowercaseSentence.charAt(index);
            if (isConsonant(currentCharacter)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Counts the total number of punctuation marks in the sentence.
     *
     * @return the total number of punctuation marks
     */
    public int countPunctuations() {
        int counter = 0;
        String punctuations = ".?!,:;-[]{}()`/\"'";
        for (int index = 0; index < sentence.length(); index++) {
            char currentCharacter = sentence.charAt(index);
            if (punctuations.indexOf(currentCharacter) != -1) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Checks if the provided character is a vowel.
     *
     * @param character the character to check
     * @return true if the character is a vowel, false otherwise
     */
    private boolean isVowel(char character) {
        character = Character.toLowerCase(character);
        return character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u';
    }

    /**
     * Checks if the provided character is a consonant.
     *
     * @param character the character to check
     * @return true if the character is a consonant, false otherwise
     */
    private boolean isConsonant(char character) {
        character = Character.toLowerCase(character);
        return character >= 'a' && character <= 'z' && !isVowel(character);
    }
}
