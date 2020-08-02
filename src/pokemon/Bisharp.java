package pokemon;

import ability.Analytic;
import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Bisharp extends Pokemon {

	public Bisharp (int level) {
		super(level);
		types[0] = types[1] = Type.DARK;
		specialAbility = new Moody(this);
		baseExp = 172;
		setStat(65,125,100,70);
	}
	public Bisharp (float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		this.exp = exp;
		types[0] = types[1] = Type.DARK;
		specialAbility = new Moody(this);
		baseExp = 172;
	}
}
