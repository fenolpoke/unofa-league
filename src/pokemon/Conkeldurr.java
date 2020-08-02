package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Conkeldurr extends Pokemon {


	public Conkeldurr (int level) {
		super(level);
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);

		baseExp = 227;
		setStat(105,140,95,45);
	}
	public Conkeldurr (float attack, float defense, float speed, float hp,
			float level, float currentHp, int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);

		baseExp = 227;		
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.FIGHTING;
		specialAbility = new SheerForce(this);
	}

}
