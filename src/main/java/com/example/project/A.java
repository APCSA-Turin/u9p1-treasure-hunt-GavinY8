package com.example.project;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        int size = 10;
        Grid grid = new Grid(size);
        Player player = new Player(0, 0);
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7,8);
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(0,1);
        Trophy trophy = new Trophy(9, 9);

        grid.placeSprite(player);
        grid.placeSprite(enemy);
        grid.placeSprite(enemy2);
        grid.placeSprite(treasure);
        grid.placeSprite(treasure2);
        grid.placeSprite(trophy);
        grid.display();

        Scanner scanner = new Scanner(System.in);
        String d = scanner.nextLine();
            if (player.isValid(grid.getGrid().length, d)) {
                player.move(d);
                grid.placeSprite(player, d);
            }
        grid.display();
        System.out.println(player.isValid(grid.getGrid().length, d));
    }
}
