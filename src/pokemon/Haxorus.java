package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Haxorus extends Pokemon {

	public Haxorus(int level) {
		super(level);
		setStat(76, 147, 90, 97);
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new SheerForce(this);
		baseExp = 243;
	}

	public Haxorus(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new SheerForce(this);
		baseExp = 243;
		this.level = level;
		this.currentHp = currentHp;
	}
}
