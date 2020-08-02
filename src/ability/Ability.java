package ability;

import java.lang.reflect.InvocationTargetException;

import main.MainProgram;
import designPattern.PokemonObserver;
import pokemon.Pokemon;

public abstract class Ability {
	
	protected float baseAttack = 0, attack = 0, baseDefense = 0, baseHp = 0, baseSpeed = 0;
	
	protected Pokemon pokemon;
	protected PokemonObserver observer;
	
	public Ability(Pokemon pokemon) {
		this.pokemon = pokemon;		
		baseAttack = pokemon.getAttack();
	}
	
	public void setObserver(PokemonObserver observer){
		this.observer = observer;
	}
	
	public float getBaseAttack() {
		return baseAttack;
	}

	public void initialize(){
		if(pokemon != null){
			baseAttack = pokemon.getAttack();
			baseDefense = pokemon.getDefense();
			baseSpeed = pokemon.getSpeed();
			baseHp = pokemon.getHp();
		}
	}
	public abstract void endTurn();
	public abstract void beforeTurn();
	public void endBattle(){
		if(pokemon != null){
			pokemon.setAttack((int) baseAttack);
			pokemon.setDefense((int) baseDefense);
			pokemon.setSpeed((int) baseSpeed);
			pokemon.setHp((int) baseHp);
		}
	}
}
