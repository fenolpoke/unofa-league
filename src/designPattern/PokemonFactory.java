package designPattern;

import java.util.Vector;

import main.MainProgram;
import pokemon.*;

public final class PokemonFactory {	
	public static enum Type{GHOST,DARK,FIGHTING,DRAGON,NORMAL,WATER,FIRE,GRASS,PSYCHIC,FLYING};

	private PokemonFactory(){}

	public static Pokemon getPokemon(Class pokemon, int level){
		try {
			return (Pokemon) (Class.forName(pokemon.getCanonicalName()).getConstructor(int.class).newInstance(level));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Pokemon getPokemon(Class pokemon, float attack,float defense,float  speed,float hp,float  level,float currentHp, float exp){
		try {
			return (Pokemon) (Class.forName(pokemon.getCanonicalName()).getConstructor(float.class,float.class,float.class,float.class,float.class,float.class,int.class).newInstance(attack, defense, speed, hp, level,currentHp,(int)exp));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static float getPower(Pokemon player, Pokemon foe){
		float power = -1;
		for (int i = 0; i < player.getTypes().length; i++) {
			if(i == 0 || !player.getTypes()[i].equals(player.getTypes()[i-1])){
				for (int j = 0; j < foe.getTypes().length; j++) {
					if(j == 0 || !foe.getTypes()[j].equals(foe.getTypes()[j-1])){
						switch(player.getTypes()[i]){
						case GHOST:
							if(foe.getTypes()[j].equals(Type.GHOST) || foe.getTypes()[j].equals(Type.PSYCHIC)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.DARK)) power = (power < 0) ? 0.5f : power*0.5f;
							else if(foe.getTypes()[j].equals(Type.NORMAL)) power = 0;
							else power = (power < 0) ? 1 : power;
							break;
						case DARK:
							if(foe.getTypes()[j].equals(Type.GHOST) || foe.getTypes()[j].equals(Type.PSYCHIC)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.DARK) || foe.getTypes()[j].equals(Type.FIGHTING)) power = (power < 0) ? 0.5f : power*0.5f;
							else power = (power < 0) ? 1 : power;
							break;
						case FIGHTING:
							if(foe.getTypes()[j].equals(Type.DARK) || foe.getTypes()[j].equals(Type.NORMAL)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.PSYCHIC)) power = (power < 0) ? 0.5f : power*0.5f;
							else if(foe.getTypes()[j].equals(Type.GHOST)) power = 0;
							else power = (power < 0) ? 1 : power;
							break;
						case DRAGON:
							if(foe.getTypes()[j].equals(Type.DRAGON)) power = (power < 0) ? 2 : power*2;
							else power = (power < 0) ? 1 : power;
							break;
						case NORMAL:
							if(foe.getTypes()[j].equals(Type.GHOST)) power = 0;
							else power = (power < 0) ? 1 : power;
							break;
						case WATER:
							if(foe.getTypes()[j].equals(Type.FIRE)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.DRAGON) || foe.getTypes()[j].equals(Type.GRASS) || foe.getTypes()[j].equals(Type.WATER)) power = (power < 0) ? 0.5f : power*0.5f;
							else power = (power < 0) ? 1 : power;
							break;
						case FIRE:
							if(foe.getTypes()[j].equals(Type.GRASS)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.DRAGON) || foe.getTypes()[j].equals(Type.FIRE) || foe.getTypes()[j].equals(Type.WATER)) power = (power < 0) ? 0.5f : power*0.5f;
							else power = (power < 0) ? 1 : power;
							break;
						case GRASS:
							if(foe.getTypes()[j].equals(Type.WATER)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.DRAGON) || foe.getTypes()[j].equals(Type.FIRE) || foe.getTypes()[j].equals(Type.GRASS)) power = (power < 0) ? 0.5f : power*0.5f;
							else power = (power < 0) ? 1 : power;
							break;
						case PSYCHIC:
							if(foe.getTypes()[j].equals(Type.FIGHTING)) power = (power < 0) ? 2 : power*2;
							else if(foe.getTypes()[j].equals(Type.PSYCHIC)) power = (power < 0) ? 0.5f : power*0.5f;
							else if(foe.getTypes()[j].equals(Type.DARK)) power = 0;
							else power = (power < 0) ? 1 : power;
							break;
						case FLYING:
							if(foe.getTypes()[j].equals(Type.FIGHTING) || foe.getTypes()[j].equals(Type.GRASS)) power = (power < 0) ? 2 : power*2;
							else power = (power < 0) ? 1 : power;
							break;
						default:
							break;
						}
					}
				}
			}
			
		}
		if(power == 0){
			MainProgram.message = MainProgram.message +"\n"+player.getClass().getSimpleName()+"'s attack doesn't affect foe "+foe.getClass().getSimpleName()+"...";
	
		}else if(power >= 2){
			MainProgram.message = MainProgram.message +"\nIt's super effective!";
		}

		return power;
	}
	public static Pokemon getStarter(int index){
		switch(index){
		case 1: return new Emboar(50);
		case 2: return new Samurott(50);
		case 3: return new Serperior(50);
		}
		return null;
	}
	public static void getFoes(Vector<Pokemon> shauntal,Vector<Pokemon> grimsley,Vector<Pokemon> caitlin,Vector<Pokemon> marshall, Vector<Pokemon> alder){
		shauntal.addElement(new Golurk(48)); shauntal.addElement(new Chandelure(50));
		grimsley.addElement(new Krookodile(48)); grimsley.addElement(new  Bisharp(50));
		caitlin.addElement(new Sigilyph(48)); caitlin.addElement(new Gothitelle(50));
		marshall.addElement(new Conkeldurr(48)); marshall.addElement(new Mienshao(50));
		alder.addElement(new Bouffalant(75)); alder.addElement(new Druddigon(75)); alder.addElement(new Volcarona(77));
	}

	public static Pokemon randomForest(){
		if(Math.random()*100 < 50){
			return new Rufflet(45);
		}else return new Masquerain(45);
	}
	public static Pokemon randomCave(){
		if(Math.random()*100 < 50){
			return new Mienfoo(45);
		}else return new Fraxure(45);
	}
	public static Pokemon randomFishing(){
		if(Math.random()*100 < 50){
			return new Sharpedo(45);
		}else return new Jellicent(45);
	}
}
