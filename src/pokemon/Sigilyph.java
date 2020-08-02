package pokemon;

import ability.Analytic;
import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Sigilyph extends Pokemon {

	public Sigilyph (int level) {
		super(level);
		setStat(72,58,80,97);
		types[0] = Type.PSYCHIC;
		types[1] = Type.FLYING;
		specialAbility = new Analytic(this);
		baseExp = 172;
	}
	public Sigilyph (float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		types[0] = Type.PSYCHIC;
		types[1] = Type.FLYING;
		specialAbility = new Analytic(this);
		baseExp = 172;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.PSYCHIC;
		specialAbility = new Analytic(this);
	}

}
