package pokemon;

import ability.Analytic;
import designPattern.PokemonFactory.Type;

public class Chandelure extends Pokemon {

	public Chandelure(int level) {
		super(level);
		types[0] = Type.GHOST;
		types[1] = Type.FIRE;
		setStat(60, 55, 90, 80);
		specialAbility = new Analytic(this);
		baseExp = 234;
	}

	public Chandelure(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		types[0] = Type.GHOST;
		types[1] = Type.FIRE;
		setStat(60, 55, 90, 80);
		specialAbility = new Analytic(this);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = Type.GHOST;
		types[1] = Type.FIRE;
		specialAbility = new Analytic(this);
	}

}
