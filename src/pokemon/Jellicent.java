package pokemon;

import ability.Analytic;
import ability.Intimidate;
import designPattern.PokemonFactory.Type;

public class Jellicent extends Pokemon {

	public Jellicent(int level) {
		super(level);
		types[0] = Type.WATER;
		types[1] = Type.GHOST;
		specialAbility = new Analytic(this);
		baseExp = 168;
		setStat(100, 60, 70, 60);
	}

	public Jellicent(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		types[0] = Type.WATER;
		types[1] = Type.GHOST;
		specialAbility = new Analytic(this);
		baseExp = 168;
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
