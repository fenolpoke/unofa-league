package pokemon;

import ability.Analytic;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Bouffalant extends Pokemon {

	public Bouffalant(int level) {
		super(level);
		types[0] = types[1] =  Type.NORMAL;
		specialAbility = new SheerForce(this);
		baseExp = 172;
		setStat(95, 110, 95, 55);
	}

	public Bouffalant(float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		types[0] = types[1] =  Type.NORMAL;
		specialAbility = new SheerForce(this);
		baseExp = 172;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.exp = exp;
		this.currentHp = currentHp;
	}

}
