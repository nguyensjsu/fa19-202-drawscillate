package drawscillate;

public class RectangleScoringStrategy implements IScoringStrategy {
    @Override
    public int calculateScore(int hits, String difficulty) {
        switch (difficulty){
            case "Easy":
                return hits/580;
            case "Normal":
                return hits/870;
            case "Hard":
                return hits/980;
        }
        return 0;
    }
}
