package pokemon;

import ability.Analytic;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Samurott extends Pokemon {
	public Samurott(int level) {
		super(level);
		setStat(95,100,85,70);
		types[0] = types[1] = Type.WATER;
		specialAbility = new Analytic(this);
		baseExp = 238;
	}
	public Samurott(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		types[0] = types[1] = Type.WATER;
		specialAbility = new Analytic(this);
		baseExp = 238;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.WATER;
		specialAbility = new Analytic(this);
	}
}
