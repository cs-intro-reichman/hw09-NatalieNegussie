import java.util.HashMap;
import java.util.Random;

public class LanguageModel {

    // The map of this model.
    // Maps windows to lists of charachter data objects.
    HashMap<String, List> CharDataMap;
    
    // The window length used in this model.
    int windowLength;
    
    // The random number generator used by this model. 
	private Random randomGenerator;

    /** Constructs a language model with the given window length and a given
     *  seed value. Generating texts from this model multiple times with the 
     *  same seed value will produce the same random texts. Good for debugging. */
    public LanguageModel(int windowLength, int seed) {
        this.windowLength = windowLength;
        randomGenerator = new Random(seed);
        CharDataMap = new HashMap<String, List>();
    }

    /** Constructs a language model with the given window length.
     * Generating texts from this model multiple times will produce
     * different random texts. Good for production. */
    public LanguageModel(int windowLength) {
        this.windowLength = windowLength;
        randomGenerator = new Random();
        CharDataMap = new HashMap<String, List>();
    }

    /** Builds a language model from the text in the given file (the corpus). */
	public void train(String fileName) {
		// Your code goes here
	}

    // Computes and sets the probabilities (p and cp fields) of all the
	// characters in the given list. */
	public void calculateProbabilities(List probs) {				
    int totalChars = probs.getSize();
    Node current = probs.first;
    double cumulativeProbability = 0.0;
    
    while (current != null) {
        // Calculate probability for each character
        double probability = (double) current.cp.getCount() / totalChars;
        
        // Set the probability and cumulative probability for the current CharData object
        current.cp.setP(probability);
        cumulativeProbability += probability;
        current.cp.setCp(cumulativeProbability);
        
        // Move to the next node
        current = current.next;
    }
}

    // Returns a random character from the given probabilities list.
	public char getRandomChar(List probs) {
    double rand = randomGenerator.nextDouble(); // Generate a random number between 0 and 1
    Node current = probs.first;
    
    while (current != null) {
        if (rand < current.cp.getCp()) {
            return current.cp.getChr(); // Return the character if the random number is less than its cumulative probability
        }
        current = current.next;
    }
    
    // If no character is found (should not happen if probabilities are correctly calculated)
    return '\0'; // Or handle this case according to your application's logic
}

    /**
	 * Generates a random text, based on the probabilities that were learned during training. 
	 * @param initialText - text to start with. If initialText's last substring of size numberOfLetters
	 * doesn't appear as a key in Map, we generate no text and return only the initial text. 
	 * @param numberOfLetters - the size of text to generate
	 * @return the generated text
	 */
	public String generate(String initialText, int textLength) {
		// Your code goes here
	}

    /** Returns a string representing the map of this language model. */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String key : CharDataMap.keySet()) {
			List keyProbs = CharDataMap.get(key);
			str.append(key + " : " + keyProbs + "\n");
		}
		return str.toString();
	}

    public static void main(String[] args) {
		// Your code goes here
    }
}
