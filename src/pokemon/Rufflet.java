package pokemon;

import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Rufflet extends Pokemon {

	public Rufflet(int level) {
		super(level);
		setStat(70, 83, 50, 60);
		types[0] =  Type.NORMAL;
		types[1] = Type.FLYING;
		specialAbility = new Moody(this);
		baseExp = 70;
		evolve = 54;
	}

	@Override
	public Class getEvolution() {
		return Braviary.class;
	}
	
	public Rufflet(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		types[0] =  Type.NORMAL;
		types[1] = Type.FLYING;
		specialAbility = new Moody(this);
		baseExp = 70;
		evolve = 54;
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
