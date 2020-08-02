package pokemon;

import ability.Intimidate;
import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Serperior extends Pokemon {

	public Serperior(int level) {
		super(level);
		setStat(75,75,95,113);
		types[0] = types[1] =  Type.GRASS;
		specialAbility = new Moody(this);
		baseExp = 238;
	}
	public Serperior(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		types[0] = types[1] =  Type.GRASS;
		specialAbility = new Moody(this);
		baseExp = 238;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] =  Type.GRASS;
		specialAbility = new Moody(this);
	}

}
