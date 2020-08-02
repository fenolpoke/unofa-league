package ability;

import pokemon.Chandelure;
import pokemon.Pokemon;

public class Moody extends Ability {

	private enum stat{ATTACK,DEFENSE,SPEED};
	
	public Moody(Pokemon pokemon) {
		super(pokemon);		
	}
public Moody() {
	super(new Chandelure(2));
	// TODO Auto-generated constructor stub
	beforeTurn();
}
	@Override
	public void endTurn() {

	}

	@Override
	public void beforeTurn() {
		stat []stats = {stat.ATTACK,stat.DEFENSE,stat.SPEED};
		stat []randomed = new stat[3];		
		for (int i = 0; i < 3; i++) {
			int j = (int)(Math.random()*(3-i));
			randomed[i] = stats[j];
			for (; j < stats.length-1; j++) {
				stats[j] = stats[j+1];
			}
		}
		for (int i = 0; i < randomed.length; i++) {
			float multiplier = (float) ((i == 0)? 0.9 : 1.2);
			switch(randomed[i]){
			case ATTACK: pokemon.setAttack((int) (pokemon.getAttack()*multiplier)); break;
			case DEFENSE: pokemon.setDefense((int) (pokemon.getDefense()*multiplier)); break;
			case SPEED: pokemon.setSpeed((int) (pokemon.getSpeed()*multiplier)); break;	
			}
		}
	}

}
