import org.junit.*;

import java.util.ArrayList;
import java.util.Collections;

public class DeckTest4 {
	Deck deck = null;

	@Before
	public void init() {
		deck = new Deck();
	}

	@Test
	public void sort_DeckSorted_True() {
		deck.sort();
		ArrayList<Card> cards = deck.getCards();
		Collections.sort(cards, new Card());
		Assert.assertEquals(deck.getCards(), cards);
	}

	@Test(timeout = 200)
	public void shuffle_DeckShuffled_True() {
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


	@Test(expected = IndexOutOfBoundsException.class)
	public void getCards_GetItemThatIsntInArray_Exception() {
		ArrayList<Card> cards = deck.getCards();

		cards.get(cards.size());
	}

	@Ignore
	public void doConflict() {
		System.out.println("Conflict!");
	}

	@Ignore
	public void doFilip() {
		System.out.println("abc");
	}

	@After
	public void close() {

	}
}
