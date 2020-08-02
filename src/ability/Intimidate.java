package ability;

import pokemon.Pokemon;

public class Intimidate extends Ability {

	private Pokemon foe;
	
	public Intimidate(Pokemon pokemon) {
		super(pokemon);
	}

	@Override
	public void initialize() {
		super.initialize();
		attack = 0.7f;
		for (Pokemon pokemon : observer.getPokemons()) {
			if(!pokemon.equals(this.pokemon)){
				foe = pokemon;
				foe.setAttack((int) (baseAttack*attack));
				break;
			}
		}
	}

	@Override
	public void endTurn() {
		attack = (attack < 1.1f) ? attack + 0.1f : 1;
		foe.setAttack((int)(baseAttack*attack));
	}

	@Override
	public void beforeTurn() {
	}

}
