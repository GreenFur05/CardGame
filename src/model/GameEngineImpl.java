package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
		
	private Collection<Player> players = new ArrayList<Player>();
	private Collection<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private Deque<PlayingCard> shuffledDeck = new ArrayDeque<PlayingCard>();

	
	@Override
	public void dealPlayer(Player player, int delay) {
		// Player must be within collection to be able to play
		if (players.contains(player)) {
			dealPlayerAndHouse(player, delay);
		}
	}
	
	@Override
	public void dealHouse(int delay) {
		Player nullPlayer = null;
		dealPlayerAndHouse(nullPlayer, delay);
	}

	@Override
	public void addPlayer(Player player) {
		for (Player existingPlayer : players) {
			if ((player.getPlayerId()).equals(existingPlayer.getPlayerId())) {
				players.remove(existingPlayer);
				break;
			}
		}
		
		players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : players) {
			if ((player.getPlayerId()).equals(id)) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return gameEngineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return players;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				PlayingCard card = new PlayingCardImpl(suit, value);
				shuffledDeck.add(card);
			}
		}
		
		// Parses shuffledDeck into a list to shuffle elements. List then parsed back into deque.
		List<PlayingCard> tempList = new ArrayList<PlayingCard>(shuffledDeck);
		Collections.shuffle(tempList);
		shuffledDeck = new ArrayDeque<PlayingCard>(tempList);
		return shuffledDeck;
	}

	private void dealPlayerAndHouse(Player player, int delay) {
		// The most convenient approach for this method would be the use of lambda expressions, to pass a method as a parameter, however since they are asked
		// to be avoided in the spec, this is the least expensive process.
		
		int score = 0;
		while (score <= GameEngine.BUST_LEVEL) {
			if (shuffledDeck.isEmpty()) {
				shuffledDeck = getShuffledDeck();
			}
			
			PlayingCard card = shuffledDeck.pollFirst();
			score += card.getScore();
			
			if (score > GameEngine.BUST_LEVEL) {
				// This checks to see if the calling method is dealPlayer or dealHouse. This if statement could be placed inside of the for loop
				// to reduce the amount of code duplication, however would require the if statement to be called excessively. 
				
				// dealHouse (bust)
				if (player == null) {
					int result = score - card.getScore();
					
					// Checks if player has won or lost to alter amount of points correspondingly
					for (Player checkPlayer : this.getAllPlayers()) {
						if (checkPlayer.getResult() > result) {
							checkPlayer.setPoints(checkPlayer.getPoints() + checkPlayer.getBet());
						}
						else if (checkPlayer.getResult() < result) {
							checkPlayer.setPoints(checkPlayer.getPoints() - checkPlayer.getBet());
						}
					}
					for (GameEngineCallback callback : gameEngineCallbacks) {	
						callback.houseBustCard(card, this);
						callback.houseResult(result, this);
					}
					
					for (Player activePlayer : players) {
						activePlayer.resetBet();
					}
					
				}
				// dealPlayer (bust)
				else {
					for (GameEngineCallback callback : gameEngineCallbacks) {
						player.setResult(score - card.getScore());
						callback.bustCard(player, card, this);
						callback.result(player, player.getResult(), this);
					}
				}
				
			}
			else {
				// dealHouse (next card)
				if (player == null) {
					for (GameEngineCallback callback : gameEngineCallbacks) {
						callback.nextHouseCard(card, this);
					}
				}
				// dealPlayer (next card)
				else {
					for (GameEngineCallback callback : gameEngineCallbacks) {
						callback.nextCard(player, card, this);
					}
				}
			
			}
			
			// Pause loop for specified delay time
			try {
				Thread.sleep(delay);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
