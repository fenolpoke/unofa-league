package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Volcarona extends Pokemon {

	public Volcarona(int level) {
		super(level);
		setStat(85, 60, 65, 100);
		types[0] = types[1] =  Type.FIRE;
		specialAbility = new Moody(this);
		baseExp = 248;
	}

	public Volcarona(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.exp = exp;
		this.attack = attack;
		this.defense = defense;
		types[0] = types[1] =  Type.FIRE;
		specialAbility = new Moody(this);
		baseExp = 248;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
	}
}
