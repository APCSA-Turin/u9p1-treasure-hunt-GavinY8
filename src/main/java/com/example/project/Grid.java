package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Dot(i, j);
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size-1-s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        Dot d = new Dot(s.getX(), s.getY());
        switch(direction) {
            case "w":
                d.setY(d.getY()-1);
                break;
            case "a":
                d.setX(d.getX()+1); //creates a Dot object and moves it to the previous location of the player based on direction
                break;
            case "s":
               d.setY(d.getY()+1);
                break;
            case "d":
                d.setX(d.getX()-1);
                break;
        }
        placeSprite(d);
        placeSprite(s);
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Dot) {//assigns emojis based on object
                    System.out.print("⬜");
                }
                else if (grid[i][j] instanceof Trophy) {
                    System.out.print("🏆");
                }
                else if (grid[i][j] instanceof Treasure) {
                    System.out.print("🌈");
                }
                else if (grid[i][j] instanceof Player) {
                    System.out.print("🦄");
                }
                else if (grid[i][j] instanceof Enemy) {
                    System.out.print("🦂");
                }
            }
            System.out.println();
        }            
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You lose...");
    }

    public void win(){ //use this method to display a win 
        System.out.println("You win!");
    }

}