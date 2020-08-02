package designPattern;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import main.MainProgram;
import pokemon.Pokemon;

public class PokemonObserver {
	private Vector<Pokemon> pokemons = new Vector<Pokemon>();

	public static enum State{START,BEFORE_TURN,END_TURN,END_BATTLE};

	public PokemonObserver(Pokemon player, Pokemon foe) {
		this.pokemons.add(player);
		this.pokemons.add(foe);
		for (Pokemon pokemon : pokemons) {
			pokemon.getSpecialAbility().setObserver(this);
		}
	}
	
	public Vector<Pokemon> getPokemons(){
		return pokemons;
	}
	
	public void changeState(State state){
		switch(state){
		case START:
			initialize();
			break;
		case BEFORE_TURN:
			beforeTurn();
			break;
		case END_TURN:
			endTurn();
			break;
		case END_BATTLE:
			endBattle();
			break;
		}
	}
	private void endBattle() {
		for (Pokemon pokemon : pokemons) {
			pokemon.getSpecialAbility().endBattle();
			
		}
	}
	private void endTurn() {
		for (Pokemon pokemon : pokemons) {
			pokemon.getSpecialAbility().endTurn();			
		}
	}

	private void beforeTurn() {
		for (Pokemon pokemon : pokemons) {
			pokemon.getSpecialAbility().beforeTurn();
		}
	}

	private void initialize() {
		for (Pokemon pokemon : pokemons) {
			pokemon.getSpecialAbility().initialize();
		}
	}
	
}