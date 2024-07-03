package testing.example;

import java.io.*;
import java.util.*;

public class PokemonArena {

    // Method to read Pokemon csv file
    List<Pokemon> readPokemon() throws IOException {
        List<Pokemon> pokemons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\2023-03-13-Pokemon.csv"))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Pokemon pokemon = parsePokemonFromCsvLine(line);
                pokemons.add(pokemon);
            }
        }
        return pokemons;
    }

    // Method to read Attack csv file
    List<Attack> readAttacks() throws IOException {
        List<Attack> attacks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\2023-04-03-Attacks.csv"))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                Attack attack = parseAttackFromCsvLine(line);
                attacks.add(attack);
            }
        }
        return attacks;
    }

    // Method to split any line, so we can add Pokemons using their parameters
    Pokemon parsePokemonFromCsvLine(String line) {
        String[] values = line.split(";");
        int index = Integer.parseInt(values[0]);
        String name = values[1];
        String type1 = values[2];
        String type2 = values[3].isEmpty() ? null : values[3];
        int total = Integer.parseInt(values[4]);
        int hp = Integer.parseInt(values[5]);
        int attack = Integer.parseInt(values[6]);
        int defense = Integer.parseInt(values[7]);
        int specialAttack = Integer.parseInt(values[8]);
        int specialDefense = Integer.parseInt(values[9]);
        int speed = Integer.parseInt(values[10]);
        List<Attack> attacks = new ArrayList<>();

        return new Pokemon(index, name, type1, type2, total, hp, attack, defense, specialAttack, specialDefense, speed, attacks);
    }

    // Method to split any Attack line to extract their parameters
    Attack parseAttackFromCsvLine(String line) {
        String[] values = line.split(";");
        int id = Integer.parseInt(values[0]);
        String name = values[1];
        String effect = values[2];
        String type = values[3];
        String kind = values[4];
        int power = Integer.parseInt(values[5]);
        int accuracy = parseAccuracy(values[6]);
        int pp = Integer.parseInt(values[7]);

        return new Attack(id, name, effect, type, kind, power, accuracy, pp);
    }

    // simple Method to eject '%' sign from 'int accuracy' value into Attack to become just an Integer
    int parseAccuracy(String accuracy) {
        if (accuracy.endsWith("%")) {
            return Integer.parseInt(accuracy.substring(0, accuracy.length() - 1));
        }
        return Integer.parseInt(accuracy);
    }

    // Method allows to choose 2 additional random attacks from Attack list
    List<Attack> getRandomAttacks(List<Attack> attacks) {
        Collections.shuffle(attacks);
        List<Attack> randomAttacks = new ArrayList<>();
        randomAttacks.add(attacks.getFirst());
        randomAttacks.add(attacks.get(1));
        System.out.println("Special Abilities: ");
        System.out.println("1. "+ randomAttacks.getFirst().name());
        System.out.println("2. "+randomAttacks.get(1).name());
        return randomAttacks;
    }


    // Method to execute an attack
    void executeAttack(Pokemon attacker, Pokemon defender, Attack attacks)  {

        double damage = ((double) attacks.power() * ((double) attacker.getAttack() / (double) defender.getDefense()) * (1.0 / 25.0));

        defender.setHp((int) Math.max(defender.getHp() - damage, 0));

        System.out.println(attacker.getName() + " used " + attacks.name() + "!");
        //System.out.println("Effective damage: " + (int) damage);
        System.out.printf("Effective damage: %.2f %n", damage);
        System.out.println(defender.getName() + "'s HP: " + defender.getHp());
        System.out.println("\n");

        if (defender.getHp() <= 0) {
            System.out.println(defender.getName() + " K.O. ");
        }
    }
}






