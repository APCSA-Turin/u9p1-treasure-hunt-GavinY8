package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player (int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }

    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

  
    //move method should override parent class, sprite
    public void move(String direction) { //changes the (x,y) coordinates of the player
        switch(direction) {
            case "w":
                setY(getY()+1);
                break;
            case "a":
                setX(getX()-1);
                break;
            case "s":
                setY(getY()-1);
                break;
            case "d":
                setX(getX()+1);
                break;
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Enemy) {
            numLives--;
        }
        else if (obj instanceof Treasure && !(obj instanceof Trophy)) {
            treasureCount++;
        }
        else if (obj instanceof Trophy) {
            if (treasureCount == numTreasures) {
                win = true;
            }
            else {
                switch(direction) {//if the player moves against the trophy without collecting all the treasure, don't allow them to move into the trophy
                    case "w":
                        move("s");
                        break;
                    case "a":
                        move("d");
                        break;
                    case "s":
                        move("w");
                        break;
                    case "d":
                        move("a");
                        break;
                }
            }
        }

    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        int bound = size-1;
        switch(direction) {
            case "w":
                if (getY()+1 > bound) {     //checks if the spot the player tries to move into is in bounds
                    return false;
                }return true;
            case "a":
                if (getX()-1 < 0) {
                    return false;
                }return true;
            case "s":
                if (getY()-1 < 0) {
                    return false;
                }return true;
            case "d":
                if (getX()+1 > bound) {
                    return false;
                } return true;
        }
        return false;
    }

    public String getCoords(){ //returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }

    public String getRowCol(int size){
        return "Player:[" + (size-1-getY()) + "][" + getX() + "]";
    }
   
}



