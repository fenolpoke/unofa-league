package pokemon;

import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Mienfoo extends Pokemon {

	public Mienfoo(int level) {
		super(level);
		setStat(45, 85, 50, 65);
		types[0] = types[1] =  Type.FIGHTING;
		specialAbility = new Moody(this);
		baseExp = 70;
		evolve = 50;
	}

	@Override
	public Class getEvolution() {
		return Mienshao.class;
	}
	
	public Mienfoo(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		types[0] = types[1] =  Type.FIGHTING;
		specialAbility = new Moody(this);
		baseExp = 70;
		evolve = 50;
		this.exp = exp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
