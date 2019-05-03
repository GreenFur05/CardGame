package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	
	private Suit suit;
	private Value value;

	public PlayingCardImpl(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}
	
	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public int getScore() {
		switch (value) {
		case ACE :
			return 1;
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case TEN:
			return 10;
		case JACK:
			return 10;
		case QUEEN:
			return 10;
		case KING:
			return 10;
		default:
			return 0;
		}
	}

	@Override
	public boolean equals(PlayingCard card) {
		if (value.equals(card.getValue()) && suit.equals(card.getSuit())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayingCardImpl other = (PlayingCardImpl) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public String toString() {
		return "Suit: " + suit + ", Value: " + value + ", Score: " + getScore();
	}

}
