package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Fraxure extends Pokemon {

	public Fraxure(int level) {
		super(level);
		setStat(66, 117, 70, 67);
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new SheerForce(this);
		baseExp = 144;
		evolve = 48;
	}
	@Override
	public Class getEvolution() {
		return Haxorus.class;
	}
	public Fraxure(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		this.exp = exp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new SheerForce(this);
		baseExp = 144;
		evolve = 48;
	}
}
