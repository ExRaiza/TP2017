import java.util.*;

class Card implements Comparator<Card> {
	private String name;
	private Colors color;
	private int value;

    public Card(String name, Colors c, int value) {
		this.name = name;
		this.color = c;
		this.value = value;
	}

	public Card() {
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name + " " + color.toString();
	}

	public int compare(Card c1, Card c2) {
		if (c1.value < c2.value)
			return -1;
		else {
			return 1;
		}
	}
}

enum Colors {
	Hearts,
	Diamonds,
	Clubs,
	Spades;
}