import java.lang.Math;

import java.util.Scanner;

public class Checker {
    private static char[][] initPlayGround(){
        char[][] playGround=new char[8][8];
        for(int i=0; i<8; i++ ){
            for(int j=0; j<8; j++){
                if (i<3){
                    if((i+j)%2==1){
                        playGround[i][j]='1';
                    } else {
                        playGround[i][j]='0';
                    }
                } else if (i<5) {
                    playGround[i][j]='0';
                } else {
                    if((i+j)%2==1){
                        playGround[i][j]='2';
                    } else {
                        playGround[i][j]='0';
                    }
                }
            }
        }
        return playGround;
    }
    private static void printPlayGround(String firstLine, String frameLine, char[][] playGround){
        System.out.println(firstLine);
        System.out.println(frameLine);
        for(int i = 0; i<8; i++){
            System.out.printf("%s |",i);
            printOneLineOfThePlayGround(playGround[i]);
        }
        System.out.println(frameLine);
        System.out.println(firstLine.substring(0, 19));
    }

    private static void printOneLineOfThePlayGround(char[] oneLineOfThePlayGround){
        for(char i:oneLineOfThePlayGround){
            if (i == '0') {
                System.out.print(' ');
            } else {
                System.out.print(i);
            }
            System.out.print(' ');
        }
        System.out.println("|");
    }

    public static byte[] getLocation() {
        //define the scanner
        Scanner s = new Scanner(System.in);
        System.out.print("  Enter X: ");
        byte x = s.nextByte();
        //End the application if the entered location is -1.
        if (x == -1) {
            System.exit( 1);
        }
        System.out.print("  Enter y: ");
        byte y = s.nextByte();
        //End the application if the entered location is -1.
        if (y == -1) {
            System.exit( 1);
        }
        return new byte[]{x,y};
    }

    public static boolean isLocationInTheField(byte[] location) {
        if (location[0] > 8 || location[0] < 0) {
            return false;
        }
        if (location[1] > 8 || location[1] < 0) {
            return false;
        }
        return true;
    }


    public static boolean isLocationEmpty(byte[] location, char[][] playGround) {
        return playGround[location[1]][location[0]] == '0';
    }

    public static boolean isPlayerMovingInTheRightDirection(byte[] oldLocation, byte[] newLocation, char playerTurn) {
        if(playerTurn == '1'){
            //Player 1 must move down, therefore the new_Y = old_Y+1
            return newLocation[1] - oldLocation[1] > 0;
        } else {
            //Player 2 must move up, therefore the new_Y = old_Y-1
            return newLocation[1] - oldLocation[1] < 0;
        }
    }

    public static boolean isPlayerMovingOneRow(byte[] oldLocation, byte[] newLocation) {
            //Player 1 must move down, therefore the new_Y = old_Y+1
            return  Math.abs(newLocation[1] - oldLocation[1]) == 1;
    }

    public static boolean isPlayerMovingOneColumn(byte[] oldLocation, byte[] newLocation) {
        //Player 1 must move down, therefore the new_Y = old_Y+1
        return  Math.abs(newLocation[0] - oldLocation[0]) == 1;
    }

    //Check if the location is valid. While checking the old location, the location is valid if the current user has a stone in that location.
    public static boolean isOldLocationValid(char playerTurn, char[][] playGround,byte[] oldLocation) {
        //Check if the entered location is in the field.
        if(isLocationInTheField(oldLocation)){
            if(playGround[oldLocation[1]][oldLocation[0]] == playerTurn){
                System.out.println(" ");
                //The location is valid
                return true;
            } else {
                System.out.println("You do not have a stone in that location. Try again.");
                return false;
            }
        } else {
            System.out.println("The entered location is out of the field. Try again.");
            return false;
        }
    }

    public static boolean isNewLocationValid(char playerTurn, char[][] playGround,byte[] oldLocation, byte[] newLocation) {
        //Check if the entered location is in the field.
        if(isLocationInTheField(newLocation)){
            //Check if the new location is empty
            if(isLocationEmpty(newLocation, playGround)) {
                //Check if the player is moving in the right direction
                if (isPlayerMovingInTheRightDirection(oldLocation, newLocation, playerTurn)) {
                    //Check if the player is moving only one row.
                    if(isPlayerMovingOneRow(oldLocation, newLocation)){
                        //Now that we know it is only moving one cell forward, we only need to check if it is moving
                        // also one cell to the left or right. We have already checked if the targer location is empty.
                        if(isPlayerMovingOneColumn(oldLocation, newLocation)){
                            return true;
                        } else {
                            System.out.println("The move is not valid. You have to move diagonal.");
                            return false;
                        }
                    } else {
                        System.out.println("You are moving more than one row.");
                        return false;
                    }
                } else {
                    System.out.println("You are moving in the wrong direction.");
                    return false;
                }
            } else {
                System.out.println("The new location is not empty.");
                return false;
            }
        } else {
            System.out.println("The entered location is out of the field. Try again.");
            return false;
        }
    }

    public static void moveTheStone(char[][] playGround, byte[] oldLocation, byte[] newLocation) {
        playGround[newLocation[1]][newLocation[0]] = playGround[oldLocation[1]][oldLocation[0]];
        playGround[oldLocation[1]][oldLocation[0]] = '0';
    }

    //========================================================//
    //==========================Main==========================//
    //========================================================//
    public static void main(String[] str){
        //define the basic parameters
//        boolean gameEnded =false;
        char playerTurn = '1';
        String firstLine = "   0 1 2 3 4 5 6 7   <- X axis";
        String frameLine = "  +----------------+";
        String lastLine = "   0 1 2 3 4 5 6 7";
        char[][] playGround = new char[8][8];
        boolean isOldLocationValid = false;
        boolean isNewLocationValid = false;

        //initializing the game
        playGround = initPlayGround();
        byte[] oldLocation = new byte[2];
        while (true){

            printPlayGround(firstLine, frameLine, playGround);

            //identify which user turn it is
            System.out.printf("Turn of player no.%s %n",playerTurn);

            isOldLocationValid = false;
            isNewLocationValid = false;
            //...............OLD................
            //get the coordination of the piece to move
            while(!isOldLocationValid){
                System.out.println("Coordinate of piece to move");
                oldLocation = getLocation();
                isOldLocationValid = isOldLocationValid(playerTurn,playGround,oldLocation);
            }

            //****************NEW****************
            //Get the destination location
            while(!isNewLocationValid){
                System.out.println("Coordinate of new position");
                byte[] newLocation = getLocation();
                isNewLocationValid = isNewLocationValid(playerTurn, playGround, oldLocation, newLocation);
                if(!isNewLocationValid){
                    System.out.println("You entered an invalid location, try again.");
                } else {
                    moveTheStone(playGround, oldLocation, newLocation);
                }
            }


            if(playerTurn == '1'){
                playerTurn = '2';
            } else {
                playerTurn = '1';
            }
        }


    }


}

