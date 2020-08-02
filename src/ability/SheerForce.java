package ability;

import pokemon.Pokemon;

public class SheerForce extends Ability {
	
	public SheerForce(Pokemon pokemon) {
		super(pokemon);
	}

	@Override
	public void initialize() {
		super.initialize();
		attack = 1.3f;
		pokemon.setAttack((int)(baseAttack*attack));
	}

	@Override
	public void endTurn() {
		attack = (attack > 1.1f) ? attack - 0.1f : 1;
		pokemon.setAttack((int)(baseAttack*attack));
	}

	@Override
	public void beforeTurn() {
	}

}
