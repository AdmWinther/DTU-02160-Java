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
        System.out.print("  Enter y: ");
        byte y = s.nextByte();
        return new byte[]{x,y};
    }

    public static boolean isLocationValid(char playerTurn, char[][] playGround,byte[] oldLocation) {
        if(oldLocation[0]<8 && oldLocation[0]>=0){
            System.out.println("X in the field");
            if(oldLocation[1]<8 && oldLocation[1]>=0){
                System.out.println("Y in the field");
                if(playGround[oldLocation[1]][oldLocation[0]] == playerTurn){
                    System.out.println("location is valid.");
                    return true;
                } else {
                    System.out.println("location is not valid.");
                    return false;
                }
            } else {
                System.out.println("Y out of the field.");
                return false;
            }
        } else {
            System.out.println("X out of the field.");
            return false;
        }
    }

    //========================================================//
    //==========================Main==========================//
    //========================================================//
    public static void main(String[] str){
        //define the basic parameters
        boolean gameEnded =false;
        char playerTurn = '1';
        String firstLine = "   0 1 2 3 4 5 6 7   <- X axis";
        String frameLine = "  +----------------+";
        String lastLine = "   0 1 2 3 4 5 6 7";
        char[][] playGround = new char[8][8];
        boolean isOldLocationValid = false;

        //initializing the game
        playGround = initPlayGround();

        while (!gameEnded){

            printPlayGround(firstLine, frameLine, playGround);

            //identify which user turn it is
            System.out.printf("Turn of player no.%s %n",playerTurn);

            //get the coordination of the piece to move
            while(!isOldLocationValid){
                System.out.println("Coordinate of piece to move");
                byte[] oldLocation = getLocation();
                isOldLocationValid = isLocationValid(playerTurn,playGround,oldLocation);
                if(!isOldLocationValid){
                    System.out.println("You entered an invalid location, try again.");
                }
            }



            playerTurn = 2;
            gameEnded = true;
        }


    }


}

