package ability;

import pokemon.Pokemon;

public class Analytic extends Ability {
	
	public Analytic(Pokemon pokemon) {
		super(pokemon);
	}

	@Override
	public void endTurn() {
		pokemon.setAttack((int) baseAttack);
	}

	@Override
	public void beforeTurn() {
		for (Pokemon pokemon : observer.getPokemons()) {
			if(!pokemon.equals(this.pokemon) && pokemon.getSpeed() > this.pokemon.getSpeed()){
				this.pokemon.setAttack((int) (baseAttack*1.3f));
				break;
			}
		}
	}

}
