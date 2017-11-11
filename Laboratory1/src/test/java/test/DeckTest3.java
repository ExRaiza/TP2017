import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class DeckTest3 extends TestCase {
	Deck deck;

	@Override
	public void setUp() {
		deck = new Deck();
	}

	public void testSort() {
		deck.sort();
		ArrayList<Card> cards = deck.getCards();
		Collections.sort(cards, new Card());
		Assert.assertEquals(deck.getCards(), cards);
	}

	public void testShuffle() {
		boolean valueB = true;
		boolean nameB = true;

		deck.sort();
		Deck deckShuffled = new Deck();
		deckShuffled.shuffle();

		for (int i = 0; i < 52; i++) {
			if (deck.getCards().get(i).getValue() != deckShuffled.getCards().get(i).getValue())
				valueB = false;
			if (!(deck.getCards().get(i).getName().equals(deckShuffled.getCards().get(i).getName())))
				nameB = false;
			if (!valueB && !nameB)
				break;
		}

		Assert.assertFalse(valueB);
		Assert.assertFalse(nameB);
	}

	public void testGetTopCard() {
		deck.sort();
		Card card = new Card("ACE", Colors.Hearts, 52);
		Assert.assertEquals(deck.getTopCard().getValue(), card.getValue());
		Assert.assertEquals(deck.getTopCard().getName(), card.getName());
	}

	public void testTopCardIsNotNull() {
		Assert.assertNotNull(deck.getTopCard());
	}

	public void testTopCardReference() {
		Card card = deck.getCards().get(deck.getCards().size() - 1);
		Assert.assertSame(deck.getTopCard(), card);
	}
}
