import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	private String[] cardsNames = {"ACE", "KING", "QUEEN", "JACK", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
	private Colors[] colors = {Colors.Hearts, Colors.Diamonds, Colors.Clubs, Colors.Spades};

	public Deck(int size) {
		cards = new ArrayList<Card>();

		switch (size) {
			case 24:
				addCards(24);
				break;
			case 32:
				addCards(32);
				break;
			case 52:
				addCards(52);
				break;
			default:
				addCards(52);
		}
	}

	public Deck() {
		cards = new ArrayList<Card>();
		addCards(52);
	}

	public void sort() {
		Collections.sort(cards, new Card());
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card getTopCard() {
		return cards.get(cards.size() - 1);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void write() {
		for (Card c : cards) {
			System.out.println(c.getName());
		}
	}

	private void addCards(int size) {
		int i = 0;
		for (int j = 0; j < size / 4; j++) {
			for (int k = 0; k < 4; k++, i++) {
				cards.add(new Card(cardsNames[j], colors[k], 52 - i));
			}
		}
	}
}

class First {
	public static void main(String[] args) {
		Deck deck = new Deck(52);
		deck.write();
		System.out.println();
		deck.shuffle();
		deck.write();
		System.out.println();
	}
}
