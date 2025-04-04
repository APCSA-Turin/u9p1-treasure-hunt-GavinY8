package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        this.initialize();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();
            Sprite playerPos = new Sprite(player.getX(), player.getY());
            Scanner scanner = new Scanner(System.in);
            String d = scanner.nextLine();
            if (player.isValid(size, d)) {
                //moves playerPos to where the player is going to go
                if (d.equals("w")) {
                    playerPos.setY(playerPos.getY() + 1);

                } else if (d.equals("a")) {
                    playerPos.setX(playerPos.getX() - 1);

                } else if (d.equals("s")) {
                    playerPos.setY(playerPos.getY() - 1);

                } else if (d.equals("d")) {
                    playerPos.setX(playerPos.getX() + 1);
                }
                //interacts with the spot player is trying to move to
                player.interact(size, d, treasures.length, grid.getGrid()[size - 1 - playerPos.getY()][playerPos.getX()]);
                player.move(d);
                grid.placeSprite(player, d);

                if (player.getLives() <= 0 || player.getWin()) {
                    //when the player wins or loses, clear the screen.
                    clearScreen();
                    if (player.getLives() <= 0) { //player losing output
                        grid.gameover();
                        break;

                    } else { //player winning output
                        grid.win(); 
                        break;
                    }
                }
            }
            
        }
            
    }

    public void initialize(){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid = new Grid(size);
        player = new Player(0, 0);
        enemies = new Enemy[2];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7,8);
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1,7);
        trophy = new Trophy(size-1, size-1);
        for (int i = 0; i < enemies.length; i++) {
            grid.placeSprite(enemies[i]);
        }
        for (int i = 0; i < treasures.length; i++) {
            grid.placeSprite(treasures[i]);
        }
        grid.placeSprite(trophy);
        grid.placeSprite(player);
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        Game g = new Game(10);
        g.initialize();
        g.play();
    }
}