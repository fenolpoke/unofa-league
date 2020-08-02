package pokemon;

import ability.Moody;
import ability.SheerForce;
import designPattern.PokemonFactory.Type;

public class Emboar extends Pokemon {

	public Emboar(int level) {
		super(level);
		setStat(110,123,65,65);
		types[0] = Type.FIRE;
		types[1] =  Type.FIGHTING;
		specialAbility = new SheerForce(this);	
		baseExp = 238;
	}	
	public Emboar(float attack, float defense, float speed, float hp,
			float level, float currentHp,int exp) {
		super(level);
		this.attack = attack;
		this.exp = exp;
		this.defense = defense;
		this.speed = speed;
		this.hp = hp;
		this.level = level;
		this.currentHp = currentHp;
		types[0] = Type.FIRE;
		types[1] =  Type.FIGHTING;
		specialAbility = new SheerForce(this);	
		baseExp = 238;
		}
}
