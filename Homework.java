import stanford.karel.SuperKarel;

import java.awt.*;

public class Homework extends SuperKarel {
    static int vertical = 1;
    static int horizontal = 1;

    public void run(){
        setBeepersInBag(100);
        vertical = 1;
        horizontal = 1;
        scanDimensions();

        while (leftIsClear()) {
            scanLineForBeepers();
            reset();
        }
        scanLineForBeepers();

        turnAround();
        while(leftIsClear()){
            putBeepersHorizontal();
            reset();
        }
        putBeepersHorizontal();

        turnRight();
        while(rightIsClear()){
            putBeepersVertical();
            resetVertical();
        }
        putBeepersVertical();
        returnToOrigin();

        if(vertical == horizontal && vertical%2!=0) {
            putBeeperDiagonal();
            if(noBeepersPresent()){
                putBeeper();
            }
            turnAround();
            while(frontIsClear()){
                move();
            }
            turnLeft();
            putBeeperDiagonal();
            if(noBeepersPresent()){
                putBeeper();
            }
            turnRight();
            while(frontIsClear()){
                move();
            }
            turnAround();
        }
    }

    private void scanDimensions(){
        paintCorner(Color.GREEN);
        while(true){
            if(frontIsBlocked())
                turnLeft();

            move();
            if(facingEast())
                horizontal++;

            else if(facingNorth())
                vertical++;

            while(frontIsBlocked())
                turnLeft();

            if(cornerColorIs(Color.GREEN)){
                paintCorner(Color.BLUE);
                break;
            }
        }
    } //DONE

    private void scanLineForBeepers() {

        if(beepersPresent()){
            pickBeeper();
        }
        while (frontIsClear()) {

            move();
            if(beepersPresent()){
                pickBeeper();
            }
        }
    } //DONE

    private void reset() {
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnRight();
        move();
        turnRight();
    } //DONE

    private void resetVertical() {
        turnAround();
        while (frontIsClear()) {
            move();
        }
        turnLeft();
        move();
        turnLeft();
    }

    private void putBeepersHorizontal(){
        for(int i = 1; i < horizontal; i++){
            move();
            if(horizontal%2==0){
                if((i == horizontal/2 || i == horizontal/2 - 1) && noBeepersPresent())
                    putBeeper();}
            else{
                if(i ==  horizontal/2 && noBeepersPresent())
                    putBeeper();
            }
        }
    }

    private void putBeepersVertical(){
        for(int i = 1; i < vertical; i++){
            move();
            if(vertical%2==0){
                if((i == vertical/2 || i ==vertical/2 - 1) && noBeepersPresent())
                    putBeeper();
            } else {
                if(i== vertical/2 && noBeepersPresent())
                    putBeeper();
            }
        }
    }

    private void returnToOrigin(){
        turnLeft();
        while(frontIsClear()){
            move();
            if(cornerColorIs(Color.BLUE)){
                paintCorner(null);
                turnLeft();
                break;
            }
            if (frontIsBlocked())
                turnLeft();
        }
    }

    private void diagonalStep(){
        turnLeft();
        move();
        turnRight();
        move();
    }

    public void putBeeperDiagonal() {
        while(frontIsClear()){
            if(noBeepersPresent()){
                putBeeper();
            }
            diagonalStep();
        }
    }

    public void putBeeperDiagonalReverse() {
        while(frontIsClear()){
            if(noBeepersPresent()){
                putBeeper();
            }
            diagonalStepReverse();
        }
    }

    private void diagonalStepReverse(){
        turnLeft();
        move();
        turnRight();
        move();
    }
}

// EVEN x EVEN WORKING i.e 8x8, 10x10 AND RETURN TO OG PLACE
// ODD x ODD || ODD x EVEN works as long as ODD != ODD

//1xn || nx1 doesnt work