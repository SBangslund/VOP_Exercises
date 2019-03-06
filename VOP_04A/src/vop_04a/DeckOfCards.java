package vop_04a;

public class DeckOfCards implements CardInterface {

    private final Card[] deck;

    // Opg 3c. Initialiser deck, dan de 52 lovlige kort og saet dem i deck-arrayet
    public DeckOfCards() {
        deck = new Card[NUMBER_OF_CARDS];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < KING; j++) {
                deck[i * KING + j] = new Card(j + 1, i + 1);
            }
        }
    }

    // Opgave 3d) Bland kortene
    public void shuffle(int swaps) {
        for (int i = 0; i < swaps; i++) {
            int randCard1 = (int) Math.round(Math.random() * (NUMBER_OF_CARDS - 1));
            int randCard2 = (int) Math.round(Math.random() * (NUMBER_OF_CARDS - 1));
            Card temp = deck[randCard1];
            deck[randCard1] = deck[randCard2];
            deck[randCard2] = temp;
        }
    }

    // Faerdiskrevet metode til "paen" udskrift af kortbunken
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deck.length; i++) {
            if (i != 0 && i % 4 == 0) {
                sb.append("\n");
            }
            sb.append(deck[i]);
            if (i != deck.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
