package pokemon;

import main.MainProgram;
import designPattern.PokemonFactory;
import designPattern.PokemonFactory.Type;
import ability.Ability;

public abstract class Pokemon{
	protected float attack, defense, speed, hp, level,currentHp, baseExp, exp = 0;
	protected int evolve = 0;
	protected Ability specialAbility;
	protected Type types[] = new Type[2];

	public Pokemon(float level) {
		this.level = level;
	}
	public boolean evolves(){
		if((int)this.level == evolve)
			return true;
		return false;
		
	}
	public Class getEvolution(){
		return null;
	}
	public float getBaseExp() {
		return baseExp;
	}

	public void setBaseExp(float baseExp) {
		this.baseExp = baseExp;
	}

	public float getExp() {
		return exp;
	}

	public void setExp(float exp, float level) {
		int hp = (int)Math.random()*2+1;
		int attack = (int)Math.random()*2+1;
		int defense = (int)Math.random()*2+1;
		int speed = (int)Math.random()*2+1;
		if(exp * level/this.level > this.baseExp - this.exp){
			MainProgram.message = MainProgram.message + "\n" + this.getClass().getSimpleName()+" leveled up!\n"
					+ "Hp went up by "+hp+"\n"
					+ "Attack went up by "+attack+"\n"
					+ "Defense went up by "+defense+"\n"
					+ "Speed went up by "+speed+"\n";
			this.level++;
			this.hp += hp;
			this.currentHp += hp;
			this.attack += attack;
			this.defense += defense;
			this.speed += speed;
			this.exp = 0;			
			setExp(exp * level/this.level - this.baseExp + this.exp, level);
		}else this.exp += exp* level/this.level;
	}

	public Pokemon attack(Pokemon foe){

		float power = PokemonFactory.getPower(this, foe);
		float damage = 0;
		
		if(power < 0){
			MainProgram.message = MainProgram.message +"\nAttack miss!";
		}else{

			damage = (float) ((attack/foe.defense * specialAbility.getBaseAttack()+2)
					* power * (Math.random()*0.15f+0.5f));

			if(Math.random()*100 > 90 && power != 0){
				MainProgram.message = MainProgram.message +"\nCritical hit!";
				damage = (int) (damage * 1.5f);
			}
		}

		if(damage > foe.currentHp) damage = foe.currentHp;		

		if(damage > 0){
			MainProgram.message = MainProgram.message +"\n"+this.getClass().getSimpleName()+" attacked "+foe.getClass().getSimpleName()+" by "+(int)damage+" point(s)!";
		}		

		foe.currentHp = (foe.currentHp > damage)? foe.currentHp - damage : 0;

		if(foe.currentHp <= 0){			
			return this;
		}

		return null;
	}

	public Type[] getTypes() {
		return types;
	}

	public float getCurrentHp(){
		return currentHp;
	}
	
	protected void setStat(int hp, int attack, int defense, int speed){
		this.attack = attack + (int)(Math.random()*level + level);
		this.defense = defense + (int)(Math.random()*level + level);
		this.speed = speed + (int)(Math.random()*level + level);
		this.hp = hp + (int)(Math.random()*level + level);
		this.currentHp = this.hp;
	}

	public boolean isAlive(){
		if(currentHp <= 0) return false;
		return true;
	}
	
	public Ability getSpecialAbility() {
		return specialAbility;
	}

	public float getAttack() {
		return attack;
	}

	public float getDefense() {
		return defense;
	}

	public float getSpeed() {
		return speed;
	}

	public float getHp() {
		return hp;
	}

	public float getLevel() {
		return level;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setSpecialAbility(Ability specialAbility) {
		this.specialAbility = specialAbility;
	}

	public void setTypes(Type[] types) {
		this.types = types;
	}
	public void restoreHealth(){
		currentHp = hp;
	}

}
