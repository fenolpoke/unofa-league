package pokemon;

import ability.Intimidate;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Druddigon extends Pokemon {

	public Druddigon(int level) {
		super(level);
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new Intimidate(this);
		baseExp = 173;
		setStat(77, 120, 90, 48);
	}

	public Druddigon(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		this.exp = exp;
		types[0] = types[1] =  Type.DRAGON;
		specialAbility = new Intimidate(this);
		baseExp = 173;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
