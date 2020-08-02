package pokemon;

import ability.Intimidate;
import designPattern.PokemonFactory.Type;

public class Sharpedo extends Pokemon {

	public Sharpedo(int level) {
		super(level);
		setStat(70, 120, 40, 95);
		types[0] = Type.WATER;
		types[1] = Type.DARK;
		specialAbility = new Intimidate(this);
		baseExp = 161;
	}

	public Sharpedo(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		types[0] = Type.WATER;
		types[1] = Type.DARK;
		specialAbility = new Intimidate(this);
		baseExp = 161;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
