package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.Vector;

import designPattern.*;
import designPattern.PokemonObserver.State;
import pokemon.*;

public class MainProgram {

	public static String message = "";
	private final int MAX_HP = 40;
	public static void printMessage(){
		for(int i = 0; i < message.length(); i++){
			System.out.print(message.charAt(i));
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		message = "";
	}

	public Scanner scan = new Scanner(System.in);
	private Vector<Pokemon> player = new Vector<Pokemon>();

	private boolean battle(Vector<Pokemon> player, Vector<Pokemon> foe, boolean league){
		int playerIndex=0, foeIndex = 0;

		for(int i = 0; i < player.size(); i++){
			if(player.get(i).isAlive()) {
				playerIndex = i;
				break;
			}
		}

		PokemonObserver battle = new PokemonObserver(player.get(playerIndex), foe.get(foeIndex));
		Pokemon win = null;

		battle.changeState(State.START);
		do{
			drawHp(player.get(playerIndex),foe.get(foeIndex));			
			if(win != null){
				win.getSpecialAbility().endBattle();
				if(win.equals(player.get(playerIndex))){
					player.get(playerIndex).setExp(foe.get(foeIndex).getBaseExp(),foe.get(foeIndex).getLevel());
					if(isAlive(foe)){foeIndex++;
					message = message+foe.get(foeIndex-1).getClass().getSimpleName()+" lose\nFoe sent out "+foe.get(foeIndex).getClass().getSimpleName()+"!";
					printMessage();
					scan.nextLine();
					battle = new PokemonObserver(player.get(playerIndex), foe.get(foeIndex));
					foe.get(foeIndex).getSpecialAbility().initialize();
					}
					win = null;
				}else if(isAlive(player)){
					int index = changePokemon(league);
					if(index < 0) return false;
					message = player.get(playerIndex).getClass().getSimpleName()+" lose\nGo "+player.get(index).getClass().getSimpleName()+"!";
					printMessage();
					scan.nextLine();
					playerIndex = index;
					battle = new PokemonObserver(player.get(playerIndex), foe.get(foeIndex));	
					player.get(playerIndex).getSpecialAbility().initialize();			
					win = null;
				}else break;
			}else{
				int input = 0;
				do {			
					System.out.print("1. Attack\n"
							+ "2. Switch Pokemon\n");
					if(!league) System.out.println("3. Catch\n4. Escape");
					System.out.print("==================\n"
							+ "Choose: ");
					input = scanInt();		
					if(input > 0) {
						if(league && input < 3 || (!league && input < 5))
							break;
					}
				} while (true);
				battle.changeState(State.BEFORE_TURN);
				if(input == 1){

					if(player.get(playerIndex).getSpeed() < foe.get(foeIndex).getSpeed()){
						win = foe.get(foeIndex).attack(player.get(playerIndex));
						if(win == null) win = player.get(playerIndex).attack(foe.get(foeIndex));
					}else{
						win = player.get(playerIndex).attack(foe.get(foeIndex));
						if(win == null) win = foe.get(foeIndex).attack(player.get(playerIndex));
					}
				}else if(input == 3){
					splash(new String[]{""}, 0, true);
					if(player.size() >= 6){
						message = "Maximum pokemons is 6!";
						printMessage();
						scan.nextLine();
						continue;
					}
					if(Math.random()*100 < 30){
						message = "You caught "+foe.get(foeIndex).getClass().getSimpleName()+"!";
						printMessage();
						scan.nextLine();
						player.addElement(foe.get(foeIndex));

						return false;
					}else{
						message = "You missed!";
						printMessage();
						scan.nextLine();
						win = foe.get(foeIndex).attack(player.get(playerIndex));						
					}
				}else if(input == 4) return false;
				else{
					int index = changePokemon(true);

					if(index != playerIndex){
						message = player.get(playerIndex).getClass().getSimpleName()+" returned\nGo "+player.get(index).getClass().getSimpleName()+"!";
						printMessage();
						scan.nextLine();
						playerIndex = index;
						battle = new PokemonObserver(player.get(playerIndex), foe.get(foeIndex));
						player.get(playerIndex).getSpecialAbility().initialize();
						win = foe.get(foeIndex).attack(player.get(playerIndex));
					}else continue;

				}	
				battle.changeState(State.END_TURN);
			}
			drawHp(player.get(playerIndex),foe.get(foeIndex));
			if(!message.isEmpty()) {
				printMessage();
				scan.nextLine();
			}
		}while(isAlive(foe) && isAlive(player));

		if(isAlive(player) && foe.size()<=1) player.get(playerIndex).setExp(foe.get(foeIndex).getBaseExp(),foe.get(foeIndex).getLevel());		

		battle.changeState(State.END_BATTLE);

		evolve();

		splash(new String[]{""},0,true);
		message = message+((!isAlive(player)) ? "You lose!" : "You win!");		
		printMessage();
		scan.nextLine();
		return !isAlive(player);
	}

	private void evolve(){
		for(int i = 0; i < player.size(); i++){
			if(player.get(i).evolves()){
				try {
					MainProgram.message = MainProgram.message+player.get(i).getClass().getSimpleName()+" evolves into "+ player.get(i).getEvolution().getSimpleName()+"!\n";
					player.set(i, (Pokemon) Class.forName(player.get(i).getEvolution().getCanonicalName()).getConstructor(int.class).newInstance((int)player.get(i).getLevel()));
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void drawHp(Pokemon player, Pokemon foe){

		int playerHp = (int) (Math.floor((float)player.getCurrentHp()/player.getHp()*MAX_HP));
		int foeHp = (int) (Math.floor((float)foe.getCurrentHp()/foe.getHp()*MAX_HP));

		for (int i = 0; i < 30; i++) {System.out.println();}

		System.out.println(foe.getClass().getSimpleName() + " lv."+(int)foe.getLevel());
		System.out.printf("%.0f|",foe.getCurrentHp());
		for (int j = 0; j < foeHp; j++) {
			System.out.printf("%c",219);
		}
		for (int j = 1; j < MAX_HP-foeHp; j++) {
			System.out.printf(" ");
		}
		System.out.printf("|"+(int)foe.getHp());

		System.out.println("\n\n\n\n\n\n");

		System.out.printf("%80s",player.getClass().getSimpleName()+ " lv."+(int)player.getLevel());
		for (int i = 0; i < 35 - new String(""+(int)player.getCurrentHp()).length(); i++) {
			System.out.print(" ");
		}
		System.out.printf("%.0f|",player.getCurrentHp());

		for (int j = 0; j < playerHp; j++) {
			System.out.printf("%c",219);
		}
		for (int j = 0; j < MAX_HP - playerHp; j++) {
			System.out.printf(" ");
		}
		System.out.printf("|"+(int)player.getHp());		
		System.out.println();

		System.out.println("================================================================================");

	}

	private int changePokemon(boolean league) {
		int index = 0;
		do {
			cls();
			System.out.println("Choose Pokemon to battle:");
			for (int i = 0; i < player.size(); i++) {
				System.out.println((i + 1) + ". "
						+ player.get(i).getClass().getSimpleName()+ " lv. "+ (int)player.get(i).getLevel());
			}
			if(!league) System.out.println((player.size()+1)+". Escape");
			System.out.print("================================================================================\n"
					+ "Choose: ");
			index = scanInt();			
			if(!league && index == player.size()+1) return -1;
			if(index > 0 && index <= player.size()){
				if(!player.get(index-1).isAlive()){
					message = "Pokemon isn't in the condition to battle!";
					printMessage();
					scan.nextLine();
				}else return index-1;
			}
		} while (true);


	}

	private void cls() {
		for(int i = 0; i < 30; i++) System.out.println();
	}	
	private void splash(String[] message, int millisecond, boolean line) {

		cls();
		if(millisecond <= 10){
			for (String string : message) {
				System.out.printf("%-80s",string);
			}
		}else{
			for (int i = 0; i < message.length/2; i++) {

				cls();
				for (int j = 0; j <= i; j++) {
					System.out.printf("%-80s",message[j]);
				}
				for (int j = i+1; j < message.length-i-2; j++) {
					System.out.println();
				}
				for (int j = message.length-i-1; j < message.length; j++) {
					System.out.printf("%-80s",message[j]);
				}
				try {
					Thread.sleep(millisecond);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(line)System.out.println("================================================================================");		

	}
	private MainProgram() {
		String[] title = {
				"@@ @@@            ,+@@@@@@@@   @      `@@                                      ",
				" @@  @@             @@@@@@@    @     ,@@@                                      ",
				" @@  @@             @@@@@@;   @@     #,@@                                      ",
				" @@  @@             @@@      `@@       @@                 ;                    ",
				" @@  @@@;@@@    ,#;  @@      @@@+      @@               #@     '@@+            ",
				" @@  @@@@@@@#`@@@@@.@@#@@  `@@ @       @@     +@@@@  @@@@@ @@@@@@@@  @@  +@@@@ ",
				" @@  @@@@@'@@@@'.@'@@@@@@  @@@ @.      @@    @@@@@@:@.@@@@@@ @@@@@  @@,@@@@@@. ",
				" @@  @@@@  @@@@  `#@@@@@, .@@@@@@      @@    @@; @@'@@ #@@@@  @@@@@  @@@@; @@' ",
				" #@+ @@@@  @@@@,  @@@@.   @@@@@@@      @@    @@ #@# @@  @@@@` '@@@@  @@@@ #@#  ",
				" .@@@@@@@  ;@@@@@@@@@@.  .@@@  @@@     @@    @@,@@ @@@@@@@@@@@@@@@@ '@@@@,@@ @ ",
				"  @@@@#@@  `@@@@@@@.@@.  @@@#  @@@     @@    @@@@.@@@@@@@@@@@@@@;@@@@@@@@@@.@@@",
				"  .@@@;@@   @@@@@@' @@. ,@@@   @@@'    @@    @@@@@@@@@@@@@@@@@@@ @@@@@ @@@@@@@@",
				"  ,#@,`,`   @ @@@   @@. @@@@    @@@    @@@@@@@@@@@ #@@@@@.@@@@@@ ;@@@  @@@@@   ",
				"                    @@.,@@@     @@@`   @@@@@@@;              @@@               ",
				"                    ##`@@@@     `@@@   @@@@@@@;         `@   #@@               ",
				"                                  @@@                    #@@  @@               ",
				"                                 ;@+,                   @@@@`@@@               ",
				"                                                        .@@@@@@,               ",
				"                                                         +@@@@;                "
		};

		int input = 0;

		splash(title,500, false);

		do{
			splash(title,0, true);

			System.out.print(
					"1. Play\n"
							+ "2. About\n"
							+ "3. Exit\n"
							+ "========\n"
							+ "Choose: ");
			input = scanInt();

			switch(input){
			case 1: play(title); break;
			case 2: about(title); break;
			case 3: System.exit(0);
			}

		}while(true);

	}
	private void about(String []title) {
		splash(title, 0, true);
		System.out.println(
				"         You are a pokemon trainer striving to be the very best.\n" +
				"      One of the way of proving it is by being the pokemon champion.\n" +
				"    You have beaten all gym leader, and can go fight the Elite Four \n" +
				"                          to challenge the champion.\n"+
				"              Do you have what it takes to be the champion?");
		scan.nextLine();
	}
	private void play(String []title) {
		Vector<String> menuInput = new Vector<String>();
		int input = 0;

		menuInput.add("New Game");
		menuInput.add("Back");

		if(!player.isEmpty() || readFile() ){
			menuInput.add(1, "Continue");
		}

		do{
			splash(title, 0, true);

			for (int i = 0; i < menuInput.size(); i++) {
				System.out.println((i+1)+". "+menuInput.get(i));
			}
			System.out.println("============");
			System.out.print("Choose: ");

			input = scanInt();

			if(input > 0  && input <= menuInput.size()) break;
		}while(true);

		switch(input){
		case 1: doAdventure(true); break;
		case 2: if(input < menuInput.size()) doAdventure(false); else return; break;
		}

	}
	private int scanInt(){
		int input = 0;
		try {
			input = scan.nextInt();
			scan.nextLine();
		} catch (Exception e) {
			scan.nextLine();
			return -1;
		}
		return input;
	}
	private void doAdventure(boolean newGame) {		
		String []banner = {"     `:ohmmNNNNmho:`            `:ohmmNNNNmho:`            `:ohmmNNNNmho:`     ",
				"   -yNmhyssssssyydmNy-        -yNmhyssssssyydmNy-        -yNmhyssssssyydmNy-   ",
				" `sNdyssssssssssssyydNy`    `sNdyssssssssssssyydNy`    `sNdyssssssssssssyydNy` ",
				"`dNyssssssyyhyyssssyyhNd`  `dNyssssssyyhyyssssyyhNd`  `dNyssssssyyhyyssssyyhNd`",
				"sMysssssymdsosdmysssyyhMy  sMysssssymdsosdmysssyyhMy  sMysssssymdsosdmysssyyhMy",
				"NNddddddMs`shy`hNddddddNN  NNddddddMs`shy`hNddddddNN  NNddddddMs`shy`hNddddddNN",
				"Nd//////my`shs.hm///+++dN  Nd//////my`shs.hm///+++dN  Nd//////my`shs.hm///+++dN",
				"yN.     .yhssyhs.   ..-Ny  yN.     .yhssyhs.   ..-Ny  yN.     .yhssyhs.   ..-Ny",
				"`dd.      `...`    `.-dd`  `dd.      `...`    `.-dd`  `dd.      `...`    `.-dd`",
				" `ym+`            `-omy`    `ym+`            `-omy`    `ym+`            `-omy` ",
				"   -ymy/.      `-+ymy-        -ymy/.      `-+ymy-        -ymy/.      `-+ymy-   ",
		"     `:oydddddddyo:`            `:oydddddddyo:`            `:oydddddddyo:`     "}; 
		String [] center = {
				"                                 `-//+////+//-`                                ",
				"                              `-+/o//+:--:+//o/+-`                             ",
				"                       `--::-:oo+/o//+:--:+//o/+oo-::-:-`                      ",
				"                     `-::-:+-+oo+/o//+:--:+//o/++o-:+-+//-`                    ",
				"                    -:+//-:+-+oo+/o//+:--:+//o/++o-:+-+//+/-                   ",
				"                    s/+//-:+-+oo+/ooossssssooo/++o-:+-+//+/s                   ",
				"                    s/+//-:+-+oo                oo-:+-+//+/s                   ",
				"                     s/+//++o+o        oo         ++o+o//+/s                   ",
				"                     s/oo+          o:----:o           +oo/s                   ",
				"                     y++o          o:/:--:/:o           o++y                   ",
				"                     yso++         o///::///o           ++osy                  ",
				"                     o:oso          o::::::o           oso:o                   ",
				"                     s--:oyyyyyyyyyyyyyssssyyyyyyyyyyyyyo:--s                  ",
				"                     s``.://///////////////////::::::::/:.``s                  ",
				"                     s`         ++++++++      :////////:-  `s                  ",
				"                     s.         ++++++++      .+/:``:/.`-  .s                  ",
				"                     o..`       ++++++++       +:.:::/:.-`..o                  ",
				"                     s:..:///// //////// ///////////////:..:o                  ",
				"                     ./o::-----o--------o---------------::o/.                  ",
				"                       ./ooooooyyyyyyyyyyoooooooooooooooo/.                    "
		};
		int input = 0;
		if(newGame){
			do {
				splash(banner, 0, true);
				System.out.print("Choose your starter type:\n" 
						+ "1. Fire\n"
						+ "2. Water\n" 
						+ "3. Grass\n"
						+ "========\n"
						+ "Choose: ");
				input = scanInt();
				if(input > 0 && input < 4) break;
			} while (true);			
			player = new Vector<Pokemon>();
			player.add(PokemonFactory.getStarter(input));
			splash(new String[]{""},0,true);
			message = "Your first ever pokemon is "+player.firstElement().getClass().getSimpleName()+"...";
			printMessage();
			scan.nextLine();
		}
		splash(new String[]{""},0,true);
		message = "You arrived at pokemon center..";
		printMessage();		
		scan.nextLine();

		do {
			do {
				splash(center, 0, true);
				System.out.print("What would you like to do:\n"
						+ "1. Catch Pokemon\n" + "2. Fight Elite Four\n"
						+ "3. Restore Pokemon's Health\n"
						+ "4. Manage Pokemons\n"
						+ "5. Save\n" 
						+ "6. Back to Main Menu\n"
						+ "====================\n" + "Choose: ");
				input = scanInt();
				if (input > 0 && input < 7)
					break;
			} while (true);
			switch (input) {
			case 1:
				if(catchPokemon()) restorePokemonHealth(center);
				break;
			case 2:
				if(fightEliteFour()) restorePokemonHealth(center);
				break;
			case 3:
				restorePokemonHealth(center);
				break;
			case 4: managePokemons(); break;
			case 5: save(); break;
			case 6: return;
			}
		} while (true);

	}
	private void save() {
		String input = "";
		do{
			splash(new String[]{""},0,true);
			System.out.print("Are you sure you want to save? This will overwrite existing data[Y/N]! ");
			input = scan.nextLine();
			if(input.equalsIgnoreCase("Y")){
				try {

					FileWriter fprintf = new FileWriter(new File("data.txt"),false);
					for(Pokemon pokemon : player){

						fprintf.write(pokemon.getClass().getCanonicalName()
								+((char)176)+pokemon.getAttack()
								+((char)176)+pokemon.getDefense()
								+((char)176)+pokemon.getSpeed()
								+((char)176)+pokemon.getHp()
								+((char)176)+pokemon.getLevel()
								+((char)176)+pokemon.getCurrentHp()
								+((char)176)+pokemon.getExp()+"\n");

					}
					fprintf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message = "Your adventure has been saved succesfully.";
				printMessage();
				scan.nextLine();
				break;
			}else if(input.equalsIgnoreCase("N")) break;
		}while(true);
	}

	private void managePokemons() {
		int input = 0;
		do {
			do {
				splash(new String[] { "" }, 0, true);
				System.out.print("1. See Pokemon\n" + "2. Switch Pokemon\n" +
						"3. Free Pokemon\n"
						+ "4. Back\n" + "===============\n" + "Choose: ");
				input = scanInt();
				if (input > 0 && input < 5)
					break;
			} while (true);
			if(input == 4) return;
			else if(input == 1){
				int index = 0;

				do {
					cls();
					System.out.println("Choose Pokemon to see:");
					for (int i = 0; i < player.size(); i++) {
						System.out.println((i + 1) + ". "
								+ player.get(i).getClass().getSimpleName()
								+ " lv. " + (int) player.get(i).getLevel());
					}
					System.out.println((player.size() + 1) + ". Back");
					System.out
					.print("================================================================================\n"
							+ "Choose: ");
					index = scanInt();
					if(index == player.size()+1) break;
					else if(index > 0 && index <= player.size()){
						index--;
						cls();
						System.out.println(player.get(index).getClass().getSimpleName());
						System.out.print("================================================================================\n");
						System.out.println("Type: "+player.get(index).getTypes()[0].toString()+((player.get(index).getTypes()[0] == player.get(index).getTypes()[1])?"":" & "+player.get(index).getTypes()[1].toString()));
						System.out.println("Ability: "+player.get(index).getSpecialAbility().getClass().getSimpleName());
						System.out.println("Level: "+(int)player.get(index).getLevel());
						System.out.println("Attack: "+(int)player.get(index).getAttack());
						System.out.println("Defense: "+(int)player.get(index).getDefense());
						System.out.println("Speed: "+(int)player.get(index).getSpeed());
						System.out.println("Exp: "+(int)Math.abs(player.get(index).getExp()));
						System.out.print("\nPress enter to go back...");
						scan.nextLine();
						continue;
					}
				} while (true);
			}else if (input == 2) {
				int before = 0, after = 0;
				do{
					cls();
					System.out.println("Choose Pokemon to switch from:");
					for (int i = 0; i < player.size(); i++) {
						System.out.println((i + 1) + ". "
								+ player.get(i).getClass().getSimpleName()
								+ " lv. " + (int)player.get(i).getLevel());
					}
					System.out.println((player.size()+1)+". Back");
					System.out.print("================================================================================\n"
							+ "Choose: ");
					before = scanInt();
					if(before == player.size()+1) break;
					if(before > 0 && before <= player.size()){
						do {
							cls();
							System.out.println("Choose Pokemon to switch into:");
							for (int i = 0; i < player.size(); i++) {
								System.out.println((i + 1)
										+ ". "
										+ player.get(i).getClass()
										.getSimpleName() + " lv. "
										+(int) player.get(i).getLevel()
										+ ((i == before-1) ? "<--" : ""));
							}
							System.out.println((player.size()+1)+". Back");
							System.out.print("================================================================================\n"
									+ "Choose: ");
							after = scanInt();
							if(after== player.size()+1) break;
							if(after > 0 && after <= player.size()){
								Pokemon temp = player.get(after-1);
								player.set(after-1, player.get(before-1));
								player.set(before-1,temp);
								message = "Succesfully switched!";
								printMessage();
								scan.nextLine();
								break;
							}
							break;
						} while (true);
					}
				}while(true);
			}else{
				if(player.size() <= 1){
					splash(new String[]{""},0,true);
					message = "You must have at least one pokemon!";
					printMessage();
					scan.nextLine();
					continue;
				}
				do{
					if(player.size() <= 1) break;
					cls();
					System.out.println("Choose Pokemon to free:");
					for (int i = 0; i < player.size(); i++) {
						System.out.println((i + 1) + ". "
								+ player.get(i).getClass().getSimpleName()
								+ " lv. " + (int)player.get(i).getLevel());
					}
					System.out.println((player.size()+1)+". Back");
					System.out.print("================================================================================\n"
							+ "Choose: ");
					input = scanInt();
					if(input == player.size()+1) break;
					if(input > 0 && input <= player.size()){
						String answer = "";
						do {
							cls();
							System.out.print("Are you sure to free "+player.get(input-1).getClass().getSimpleName()+"[Y/N]? ");
							answer = scan.nextLine();
							if(answer.equalsIgnoreCase("Y")){
								message = "Goodbye, "+player.get(input-1).getClass().getSimpleName()+"!";
								printMessage();
								scan.nextLine();
								player.remove(input-1);
								break;
							}else if(answer.equalsIgnoreCase("N")) break;
						} while (true);
					}
				}while(true);

			}
		} while (true);
	}

	private void restorePokemonHealth(String []center) {
		for (Pokemon pokemon : player) {
			pokemon.restoreHealth();
		}
		splash(center,0,true);
		message = "Pokemon center restored your pokemon to full health";
		printMessage();
		scan.nextLine();
	}
	private boolean fightEliteFour() {
		String []league = {
				"                                                      NdN                       ",
				"                               N dddd             Ndhssy                        ",
				"                            dyo////////ohdN   Ndhs//./y                         ",
				"                          ho:/oyhddddhhs+/+hhs///:o//yN                         ",
				"                         y/:yddddddddddhy+`-/::-`://yN                          ",
				"                        h//hddddho++oo:-:::-`   ://yN                           ",
				"                       Ns.hdddho::--:/-s`      ://y                             ",
				"                       No:dddho.o    -/+.     ://yN                             ",
				"                       Ns.o/--:/::..-/-o     ://yN                              ",
				"                        y/-+.` .//+///:`    ://yN                               ",
				"                         y/::`             ://y                                 ",
				"                          ho///:.`        ://yN                                 ",                    
				"                           Ndyo/++/+:    ://yN                                  ",
				"                               N dy-+   ://yN                                   ",
				"                                  do:- ://y                                     ",
				"                                   h-+://yN                                     ",
				"                                   ho-//yN                                      ",
				"                                    s-/yN                                       ",
				"                                    dsy                                         ",
				"                                     dN                                         "
		};

		if(!isAlive(player)){
			splash(new String[]{""}, 0, true);
			message = "Restore pokemon's health first!";
			printMessage();
			scan.nextLine();
			return false;
		}
		String []name = {"Shauntal","Grimsley","Caitlin","Marshall", "Alder"};
		Vector<Vector<Pokemon>> foes = new Vector<Vector<Pokemon>>();
		for (int i = 0; i < 5; i++) {
			foes.add(new Vector<Pokemon>());	
		}
		PokemonFactory.getFoes(foes.get(0), foes.get(1), foes.get(2), foes.get(3), foes.get(4));

		splash(league, 0, true);
		message = "You have challenged the elite four!";
		printMessage();
		scan.nextLine();

		for (int i = 0; i < 4; i++) {
			splash(league, 0, true);
			message = "Your "+(i+1)+"th battle is against "+name[i]+"!";
			printMessage();
			scan.nextLine();
			if(battle(player, foes.get(i), true)){
				message = "You hurried to the pokemon center!";
				printMessage();
				scan.nextLine();
				return true;
			}
		}

		splash(league, 0, true);
		message = "You beat the elite four!\n";
		printMessage();
		scan.nextLine();
		String input="";
		do{
			splash(league, 0, true);
			System.out.print("Would you like to restore pokemon's health[Y/N]? ");	
			input = scan.nextLine();
			if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")) {
				if (input.equalsIgnoreCase("Y")) {
					restorePokemonHealth(new String[] { "" });
				} 
				splash(league, 0, true);
				message = "Your champion battle is against " + name[4]
						+ "!";
				printMessage();
				scan.nextLine();
				if (battle(player, foes.get(4), true)) {
					message = "You hurried to the pokemon center!";
					printMessage();
					scan.nextLine();
					return true;

				}
				break;
			}
		}while(true);
		String answer = "";
		splash(league,0,true);
		message = "You beat the champion!";
		printMessage();
		message = "Do you want to save[Y/N]? ";
		do{
			answer = scan.nextLine();
			if(answer.equalsIgnoreCase("Y")) {
				save();
				break;
			}
			else if(answer.equalsIgnoreCase("N"))break;
			else {
				splash(league,0,true);
				System.out.println("You beat the champion!");
				System.out.print("Do you want to save[Y/N]? ");
			}
		}while(true);
		return false;
	}
	private boolean isAlive(Vector<Pokemon> trainer) {
		for (int i = 0; i < trainer.size(); i++) {
			if(trainer.get(i).isAlive()) return true;
		}
		return false;
	}

	private boolean catchPokemon() {		
		int input = 0;
		do{
			splash(new String[]{""}, 0, true);
			System.out.print("1. White Forest\n"
					+ "2. Challenger's Cave\n"
					+ "3. Fishing\n"
					+ "4. Back\n"
					+ "=====================\n"
					+ "Choose: ");
			input = scanInt();
			if(input > 0 && input < 5) break;
		}while(true);

		Vector<Pokemon> foe = new Vector<Pokemon>();
		switch(input){
		case 1: foe.add(PokemonFactory.randomForest());break;
		case 2: foe.add(PokemonFactory.randomCave());break;
		case 3: foe.add(PokemonFactory.randomFishing());break;
		case 4: return false;
		}

		cls();
		message = "You found a random pokemon!";
		printMessage();
		scan.nextLine();
		if(battle(player,foe,false)){
			message = "You hurried to the pokemon center!";
			printMessage();
			scan.nextLine();
			return true;
		}
		return false;
	}
	private boolean readFile() {
		try {
			Scanner fscan = new Scanner(new File("data.txt"));
			while(fscan.hasNext()){
				String line = fscan.nextLine();
				String [] datas = line.split(((char)176)+"");

				for (String string : datas) {
					System.out.println(string);
				}
				System.out.println("line:"+line);

				//class, attack, defense, speed, hp, level,currentHp,exp;
				try {
					player.add(PokemonFactory.getPokemon(Class.forName(datas[0]), Float.parseFloat(datas[1]), 
							Float.parseFloat(datas[2]), Float.parseFloat(datas[3]), Float.parseFloat(datas[4]), Float.parseFloat(datas[5]), 
							Float.parseFloat(datas[6]), Float.parseFloat(datas[7])));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			fscan.close();
			return true;
		} catch (FileNotFoundException e) {
			//			e.printStackTrace();
		}

		return false;
	}
	public static void main(String[] args) {	

		new MainProgram();
	}

}
