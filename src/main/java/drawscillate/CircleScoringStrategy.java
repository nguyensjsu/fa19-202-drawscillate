package drawscillate;

public class CircleScoringStrategy implements IScoringStrategy {
    @Override
    public int calculateScore(int hits, String difficulty) {
        switch (difficulty){
            case "Easy":
                return hits/670;
            case "Normal":
                return hits/950;
            case "Hard":
                return hits/1050;
        }
        return 0;
    }
}
