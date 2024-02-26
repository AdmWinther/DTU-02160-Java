import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

class Piece {
    static int counterBlack = 0;
    static int counterWhite = 0;

    private boolean color;  //1 is false, 2 is true

    public Piece(boolean color){
        if(color){
            //Make a new white piece
            if(counterWhite <12){
                this.color= true;
                counterWhite++;
            } else {
                return;
            }
        } else {
            //Make a new black piece
            if(counterBlack <12){
                this.color= false;
                counterBlack++;
            } else {
                return;
            }
        }
    }

    public boolean getColor(){
        return this.color;
    }
    public int getColorString(){
        return this.color? 2: 1;
    }
}

class Grid2{
    private Piece[][] grid;

    //constructor of the Field
    public  Grid2() {
        grid= new Piece[8][8];
        for(int i = 0; i <8 ; i++){
            for(int j = 0; j <8 ; j++){
                grid[i][j]= null;
            }
        }
        this.initialPiecePlacement();
    }

    private Piece gridCell(int[] location){
        return grid[location[0]][location[1]];
    }

    private void initialPiecePlacement(){
        for(int i=0; i<8;i++){
            for(int j=0; j<3; j++){
                if((i+j)%2==1){
//                    grid[i][j].FillCell(false);
                    grid[i][j]=new Piece(false);
                }
//                this.printGrid();
            }
        }

        for(int i=0; i<8;i++){
            for(int j=5; j<8; j++){
                if((i+j)%2==1){
//                    grid[i][j].FillCell(true);
                    grid[i][j]= new Piece(true);
                }
//                this.printGrid();
            }
        }
    }

    private int[] getLocation() {
        //define the scanner
        Scanner s = new Scanner(System.in);
        System.out.print("  Enter X: ");
        int x = s.nextInt();
        //End the application if the entered location is -1.
        if (x == -1) {
            System.exit( 1);
        }
        System.out.print("  Enter y: ");
        int y = s.nextInt();
        //End the application if the entered location is -1.
        if (y == -1) {
            System.exit( 1);
        }
        return new int[]{x,y};
    }

    private void moveStoneBetweenCells(int[] oldLocation, int[] newLocation){
        grid[newLocation[0]][newLocation[1]] = this.gridCell(oldLocation);
        grid[oldLocation[0]][oldLocation[1]] = null;
    }

    private static boolean isLocationInTheField(int[] location) {
        if (location[0] > 8 || location[0] < 0) {
            return false;
        }
        return location[1] <= 8 && location[1] >= 0;
    }
    private boolean isLocationEmpty(int[] location) {
        return this.gridCell(location) == null;
    }

    private boolean isPlayerMovingOwnStone(int[] oldLocation, boolean playerTurn) {
        boolean pieceColor = gridCell(oldLocation).getColor();
        if(!pieceColor){
            //the color of the piece in the cell was 1.
            return !playerTurn;
        } else {
            //the color of the piece in the cell was 2.
            return playerTurn;
        }
    }



    private static boolean isPlayerMovingInTheWrongDirection(boolean playerTurn, int[] oldLocation, int[] newLocation) {
        if(playerTurn){
            //Player 2 must move up, towards lower Js, therefore the new_Y < old_Y
            return newLocation[1] - oldLocation[1] >= 0;
        } else {
            //Player 1 must move down, therefore the new_Y > old_Y-1
            return newLocation[1] - oldLocation[1] <= 0;
        }
    }

    private static boolean isPlayerMovingOneRow(int[] oldLocation, int[] newLocation) {
        //Player 1 must move down, therefore the new_Y = old_Y+1
        return  Math.abs(newLocation[1] - oldLocation[1]) == 1;
    }

    private static boolean isPlayerMovingOneColumn(int[] oldLocation, int[] newLocation) {
        //Player 1 must move down, therefore the new_Y = old_Y+1
        return  Math.abs(newLocation[0] - oldLocation[0]) == 1;
    }
    private boolean isOldLocationValid(boolean playerTurn, int[] oldLocation) {
        //Check if the entered location is in the field.
        if(isLocationInTheField(oldLocation)){
            if(isLocationEmpty(oldLocation)){
                System.out.println("location is empty.");
                return false;
            } else {
                if(isPlayerMovingOwnStone(oldLocation, playerTurn)){
                    //Correct. Now we should get the coordination of the destination.
//                    System.out.println("*********Correct*********.");
                    return true;
                } else {
                    System.out.println("This is not your stone");
                    return false;
                }
            }
        } else {
            System.out.println("Location is out of the field.");
            return false;
        }
    }

    private boolean isNewLocationValid(boolean playerTurn, int[] oldLocation, int[] newLocation) {
        //Check if the entered location is in the field.
        if(isLocationInTheField(newLocation)){
            if(isPlayerMovingInTheWrongDirection(playerTurn, oldLocation, newLocation)){
                System.out.println("You are moving in the wrong direction!");
                return false;
            } else {
                if(isPlayerMovingOneRow(oldLocation, newLocation)){
                    if(isPlayerMovingOneColumn(oldLocation, newLocation)){
                        if(isLocationEmpty(newLocation)){
//                            System.out.println("correct.");
                            return true;
                        } else {
                            System.out.println("location is NOT empty.");
                            return false;
                        }
                    } else {
                        System.out.println("Only Diagonal move is acceptable.");
                        return false;
                    }
                } else {
                    System.out.println("You can move only one row at a time!");
                    return false;
                }
            }
        } else {
            System.out.println("Location is out of the field.");
            return false;
        }
    }
    public void printGrid() {
        String firstLine = "    0 1 2 3 4 5 6 7   <- X axis";
        String frameLine = "  +-----------------+";
        String lastLine = "    0 1 2 3 4 5 6 7";
        System.out.println(firstLine);
        System.out.println(frameLine);
        for (int j = 0; j < 8; j++) {
            System.out.printf("%s |", j);
            for (int i = 0; i < 8; i++) {
//                grid[i][j].printCell();
                if(grid[i][j] == null){
                    System.out.print("  ");
                } else {
                    if (grid[i][j].getColor()) {
                        System.out.print(" 2");
                    } else {
                        System.out.print(" 1");
                    }
                }
            }
            System.out.println(" |");
        }
        System.out.println(frameLine);
        System.out.println(lastLine);
    }

    private String playerTurnString(boolean playerTurn){
        return (playerTurn ? "2":"1");
    }

    public void playerMove(boolean playerTurn){
        boolean playerGrabbedStone = false;
        boolean playerPlacedStone = false;
        int[] oldLocation = new int[]{10,10};
        int[] newLocation = new int[]{10,10};
        while (true){
            printGrid();
            System.out.println("Turn of player no. "+this.playerTurnString(playerTurn));
            System.out.println("Coordinate of piece to move");
            oldLocation = getLocation();

            if(isOldLocationValid(playerTurn, oldLocation)){
                break;
            }
        }

        while (true){
            System.out.println("Coordinate of new position");
            newLocation = getLocation();


            if(isNewLocationValid(playerTurn, oldLocation ,newLocation)){
                moveStoneBetweenCells(oldLocation, newLocation);
                break;
            }
        }
    }
}
// ...

public class Checker {


    public static void main(String[] args){
        Grid2 grid = new Grid2();
        boolean playerTurn = false;

        while(true){
            //get location of the
            grid.playerMove(playerTurn);
            //get location of the destination
            playerTurn = !playerTurn;
        }
    }

}