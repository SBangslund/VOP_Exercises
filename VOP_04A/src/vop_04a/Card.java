package vop_04a;

/**
 * VOP eksamen F2014
 * Tom klasse til opgave 3
 * @author erso
 */
public class Card implements CardInterface {
    private String face;
    private String suit;
    
    public Card(int face, int suit) {
        this.face = checkFace(face);
        this.suit = checkSuit(suit);
    }
    
    private String checkFace(int number) {
        switch(number) {
            case ACE:
                return ACE_NAME;
            case JACK:
                return JACK_NAME;
            case QUEEN:
                return QUEEN_NAME;
            case KING:
                return KING_NAME;
            default:
                return number > ACE && number < JACK ? String.valueOf(number) : null;
        }
    }
    
    private String checkSuit(int number) {
        switch(number) {
            case CLUBS:
                return CLUBS_NAME;
            case DIAMONDS:
                return DIAMONDS_NAME;
            case HEARTS:
                return HEARTS_NAME;
            case SPADES:
                return SPADES_NAME;
            default:
                return null;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", suit, face);
    }
}
