package mancala;

public class PitNotFoundException extends Exception {
    public PitNotFoundException() {
        super("The specified pit does not exist.");
    }

    public PitNotFoundException(String message) {
        super(message);
    }
}
