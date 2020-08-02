package pokemon;

import designPattern.PokemonFactory.Type;
import ability.Analytic;
import ability.SheerForce;

public class Golurk extends Pokemon {

	public Golurk(int level) {
		super(level);
		setStat(58, 50, 145, 30);
		types[0] = types[1] = Type.GHOST;
		specialAbility = new SheerForce(this);
		baseExp = 169;
	}

	public Golurk(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		this.exp = exp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		types[0] = types[1] = Type.GHOST;
		specialAbility = new SheerForce(this);
		baseExp = 169;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.GHOST;
		specialAbility = new SheerForce(this);
	}
}
