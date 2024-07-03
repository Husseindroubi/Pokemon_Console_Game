package testing.example;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        // New pokemon arena game
        PokemonArena pa = new PokemonArena();

        // reading our csv files
        List<Pokemon> pokemons = pa.readPokemon();
        List<Attack> attacks = pa.readAttacks();

        // Game Scenario
        System.out.println("Welcome to Pokemon Arena Game ");
        for (Pokemon p : pokemons) {
            System.out.println(p.getIndex() + " " + p.getName());
        }
        int userInput; // user chooses a Pokemon from the list
        do {
            System.out.println("Choose your pokemon by pokemon's number(from 1 - 151): ");
            userInput = sc.nextInt() - 1;
        } while (userInput > pokemons.size() || userInput < 0);

        // user's  choice
        System.out.println(" you have chosen: " + pokemons.get(userInput).getName());
        Pokemon userPokemon = pokemons.get(userInput);
        System.out.println("HP: " + userPokemon.getHp() + " ,Attack: " + userPokemon.getAttack() +
                " ,Defense: " + userPokemon.getDefense());
        userPokemon.setAttacks(pa.getRandomAttacks(attacks)); // user gets 2 random special attacks

        System.out.println("\n");
        // computer choice (Random)
        Pokemon computerPokemon = pokemons.get(r.nextInt(151) + 1);
        System.out.println(" Computer have chosen: " + computerPokemon.getName());
        System.out.println("HP: " + computerPokemon.getHp() + " ,Attack: " + computerPokemon.getAttack() +
                " ,Defense: " + computerPokemon.getDefense());
        computerPokemon.setAttacks(pa.getRandomAttacks(attacks)); // computer gets 2 random special attacks

        System.out.println("\n");

        // boolean to define who starts first based on the Pokemon's speed
        boolean userTurn = userPokemon.getSpeed() >= computerPokemon.getSpeed();

        // Start the Battle
        System.out.println("Let the battle begin!");
        while (userPokemon.getHp() > 0 && computerPokemon.getHp() > 0) {
            System.out.println("Your HP: " + userPokemon.getHp() + " | Computer's HP: " + computerPokemon.getHp());
            // user's Turn:
            if (userTurn) {
                System.out.println("It's your turn");
                // user chooses special ability
                System.out.println("1. " + userPokemon.getAttacks().get(0).name());
                System.out.println("2. " + userPokemon.getAttacks().get(1).name());
                int ability;
                do {
                    System.out.println("Choose your special Ability (1 - 2): ");
                    ability = sc.nextInt() - 1;
                } while (ability > 1 || ability < 0);

                // it's user's Attack time !
                pa.executeAttack(userPokemon, computerPokemon, userPokemon.getAttacks().get(ability));
                System.out.println("\n");
            } else {
                System.out.println("It's computer's turn");
                //computerPokemon.getAttacks().get(r.nextInt(2) + 1);
                pa.executeAttack(computerPokemon, userPokemon, computerPokemon.getAttacks().get(r.nextInt(2)));
                System.out.println("\n");
            }
            userTurn = !userTurn;
        }


        // Determine the winner
        if (userPokemon.getHp() <= 0) {
            System.out.println("Computer wins!");
        } else if (computerPokemon.getHp() <= 0) {
            System.out.println("You win!");
        }

    }
}


















