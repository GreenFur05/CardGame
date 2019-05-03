package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String playerId, playerName;
	private int points, bet, result;
	private final int INITIAL_BET = 0;

	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return playerId;
	}

	@Override
	public boolean placeBet(int bet) {
		if (bet >= INITIAL_BET && bet <= points) {
			this.bet = bet;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		bet = INITIAL_BET;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

}
