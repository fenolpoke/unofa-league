package pokemon;

import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Mienshao extends Pokemon {

	public Mienshao (int level) {
		super(level);
		setStat(65,125,60,105);
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);
		baseExp = 179;
	}
	public Mienshao (float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);
		baseExp = 179;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);
	}

}
