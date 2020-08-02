package pokemon;

import ability.Intimidate;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Krookodile extends Pokemon {

	public Krookodile(int level) {
		super(level);
		setStat(58, 50, 145, 30);
		types[0] = types[1] =  Type.DARK;
		specialAbility = new Intimidate(this);
		baseExp = 229;
	}
	public Krookodile(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		types[0] = types[1] =  Type.DARK;
		specialAbility = new Intimidate(this);
		baseExp = 229;
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
