package pokemon;

import ability.Analytic;
import ability.Moody;
import designPattern.PokemonFactory.Type;

public class Gothitelle extends Pokemon {


	public Gothitelle (int level) {
		super(level);
		setStat(70,55,95,65);
		types[0] = types[1] = Type.PSYCHIC;
		specialAbility = new Moody(this);
		baseExp = 221;
	}
	public Gothitelle (float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		types[0] = types[1] = Type.PSYCHIC;
		specialAbility = new Moody(this);
		baseExp = 221;
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = types[1] = Type.PSYCHIC;
		specialAbility = new Moody(this);
	}
}
