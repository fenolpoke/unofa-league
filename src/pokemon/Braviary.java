package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Braviary extends Pokemon {

	public Braviary(int level) {
		super(level);
		types[0] =  Type.NORMAL;
		types[1] = Type.FLYING;
		specialAbility = new SheerForce(this);
		baseExp = 179;
		setStat(100, 123, 75, 80);
	}

	public Braviary(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		this.attack = attack;
		types[0] =  Type.NORMAL;
		types[1] = Type.FLYING;
		specialAbility = new SheerForce(this);
		baseExp = 179;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.exp = exp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
