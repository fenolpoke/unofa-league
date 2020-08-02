package pokemon;

import ability.Intimidate;
import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Masquerain extends Pokemon {

	public Masquerain(int level) {
		super(level);
		setStat(70, 60, 62, 60);
		types[0] = types[1] =  Type.FLYING;
		specialAbility = new Intimidate(this);
		baseExp = 145;
	}

	public Masquerain(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		types[0] = types[1] =  Type.FLYING;
		specialAbility = new Intimidate(this);
		baseExp = 145;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
