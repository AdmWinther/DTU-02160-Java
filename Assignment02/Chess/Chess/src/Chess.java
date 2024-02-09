class Piece {
    // ...
    private int position_i;
    private char position_c;
    private boolean color;

    public void setArbitraryPosition(Position currentPosition) {
    // Set the initial location of a Piece by taking an object of the type Position.
        if(Position.isPositioningTheField(currentPosition)){
            this.position_c = currentPosition.getPosition_c();
            this.position_i = currentPosition.getPosition_i();
        }
    }

    public void setArbitraryPosition(char position_c, int position_i) {
        //This is an overload of the method setArbitraryPosition. This method is used when the input is int-Char
        //instead of an Object Position.
        setArbitraryPosition(new Position(position_i, position_c));
    }

    public boolean isValidPosition(Position newPosition) {
    //This method is only a placeholder for the child objects. In the child objects like Rook or King, this method
    //must be over-written.
        return (true);
    }
    public int getPosition_i(){
        // To encapsulate the private field of the object, this method is developed.
        return this.position_i;
    }
    public char getPosition_c(){
        // To encapsulate the private field of the object, this method is developed.
        return this.position_c;
    }

    public void setColor(boolean color){
        //This method must be used to protect the color. the color will be defined in the child objects.
        //This method allow the child objects set the color, but still have the color protected and encapsulated.
        this.color = color;
    }

    public boolean getColor(){
        //This method must be used to protect the color. Color is private to protect it from uncontrolled modification.
        //This method allow the child objects get the color, but still have the color protected and encapsulated.
        return color;
    }

}

class King extends Piece {
    private boolean color;

    public King(Player player){
        //Constructor for King object that take a player as argument.
        this.setColor(player.getColorBoolean());;
    }

    public boolean isValidPosition(Position position){
        //This method over-writes the method from the parent object. This will check if the new location is valid.
        //This method accepts Object of the type Position as an argument. Maybe in the future we need to have an
        //overload of the method that accepts int-char.
        //For king the new location is acceptable if the new location is only one cell away from the current location.
        boolean result = false;
        if(Position.isPositioningTheField(position)){
            if(Math.abs(position.getPosition_c()-this.getPosition_c()) <= 1){
                if(Math.abs(position.getPosition_i()-this.getPosition_i()) <= 1){
                    result = true;
                }
            }
        }
        return result;
    }
}

class Rook extends Piece {

    public Rook(Player player){
        //The constructor for Rook. It sets the color of the Rook based on the Object Player.
        this.setColor(player.getColorBoolean());
    }

    public boolean isValidPosition(Position position){
        //This method over-writes the method from the parent object. This will check if the new location is valid.
        //This method accepts Object of the type Position as an argument. Maybe in the future we need to have an
        //overload of the method that accepts int-char.
        //For Rook, the new location is acceptable if the new location is either in the same row or the same column.
        boolean result = false;
        if(Position.isPositioningTheField(position)){
            if(position.getPosition_i()==this.getPosition_i()) result = true;
            if(position.getPosition_c()==this.getPosition_c()) result = true;
        }
        return result;
    }
}

class Bishop extends Piece {

    public Bishop(Player player){
        //The constructor for Rook. It sets the color of the Rook based on the Object Player.
        this.setColor(player.getColorBoolean());
    }

    public boolean isValidPosition(Position position){
        //This method over-writes the method from the parent object. This will check if the new location is valid.
        //This method accepts Object of the type Position as an argument. Maybe in the future we need to have an
        //overload of the method that accepts int-char.
        //Bishop moves diagonal. So the move is valid if the number of cells in horizontal and vertical direction are
        // equal.
        boolean result = false;
        if(Position.isPositioningTheField(position)){
            int delta_i = Math.abs(position.getPosition_i() - this.getPosition_i());
            int delta_c = Math.abs(position.getPosition_c() - this.getPosition_c());
            if(delta_c == delta_i) result = true;
        }
        return result;
    }
}

class Knight extends Piece {

    public Knight(Player player){
        //The constructor for Rook. It sets the color of the Rook based on the Object Player.
        this.setColor(player.getColorBoolean());
    }

    public boolean isValidPosition(Position position){
        //This method over-writes the method from the parent object. This will check if the new location is valid.
        //This method accepts Object of the type Position as an argument. Maybe in the future we need to have an
        //overload of the method that accepts int-char.
        //For Knight the move is valid only if the summation of the move in vertical and horizontal direction is 3.
        boolean result = false;
        if(Position.isPositioningTheField(position)){
            int delta_i = Math.abs(position.getPosition_i() - this.getPosition_i());
            int delta_c = Math.abs(position.getPosition_c() - this.getPosition_c());
            if(delta_c + delta_i == 3) result = true;
        }
        return result;
    }
}

class Pawn extends Piece {

    public Pawn(Player player){
        //The constructor for Rook. It sets the color of the Rook based on the Object Player.
        this.setColor(player.getColorBoolean());
    }

    public boolean isValidPosition(Position position){
        //This method over-writes the method from the parent object. This will check if the new location is valid.
        //This method accepts Object of the type Position as an argument. Maybe in the future we need to have an
        //overload of the method that accepts int-char.
        boolean result = false;
        //Pawn is challenging. first we should check if there is no move in the horizontal direction.
        if(this.getPosition_c()==position.getPosition_c()) {
            //Ok, now we know there is not horizontal direction of move.
            //Npw we need to know the direction of move in vertical way is correct for both white and black.
            int delta_i  = position.getPosition_i()-this.getPosition_i();
            //delta_i will be positive for move upwards (the correct direction for white)
            //delta_i will be negative for move downwards (the correct direction for black)
            boolean direction = delta_i > 0;
            if(direction == this.getColor()){
                //Now we know the direction is right. Now we should check if it is on the row 2 (white) or 7 (black)
                if(this.getPosition_i()==2 && this.getColor()){
                    //If white and in row 2. So it can move either one cell or two cells.
                    if(Math.abs(delta_i) < 3) result = true;
                } else if (this.getPosition_i()==7 && !this.getColor()){
                    //If black and in row 7. So it can move either one cell or two cells.
                    if(Math.abs(delta_i) < 3) result = true;
                } else {
                    result = Math.abs(this.getPosition_i()-position.getPosition_i()) == 1;
                }
            }


        }
        return result;
    }
}

class Player {
    private String playerName;
    private boolean colorWhite;

    public Player(String player){
        this.playerName = player;
    }

    public void setColorWhite(boolean bool){
        this.colorWhite = bool;
    }

    public boolean getColorBoolean(){
        return this.colorWhite;
    }

    public String getColorString(){
        return this.colorWhite? "White":"Black";
    }
}

class Position {
    private char position_c;
    private int position_i;
    public Position(char position_c, int position_i){
        if(Position.isPositioningTheField(position_c,position_i)){
            this.position_c = Character.toLowerCase(position_c);
            this.position_i = position_i;
        }
    }

    public Position(int position_i, char position_c){
        this(position_c, position_i);
    }

    //check if the position in the field
    public static boolean isPositioningTheField(char position_c, int position_i){
        position_c= Character.toLowerCase(position_c);
        return ((position_i < 9 && position_i > 0) && (position_c >= 'a' && position_c <= 'h'));
    }
    public static boolean isPositioningTheField(int position_i, char position_c){
        return isPositioningTheField(position_c, position_i);
    }

    public static boolean isPositioningTheField(Position position){
        return isPositioningTheField(position.getPosition_c(), position.getPosition_i());
    }

    public int getPosition_i(){
        return this.position_i;
    }

    public char getPosition_c(){
        return this.position_c;
    }
}
// ...
public class Chess {
    public static void main(String[] args) {
        Player p1 = new Player("White player");
        p1.setColorWhite(true);
        Player p2 = new Player("Black player");
        p2.setColorWhite(false);
        System.out.println("Testing kings:");
        Piece whiteKing = new King(p1);
        whiteKing.setArbitraryPosition(new Position('f', 5));
        test(whiteKing, 'a', 1, false);
        test(whiteKing, 'f', 4, true);
//        test(whiteKing, 'f', 9, false);
        System.out.println("Testing rooks:");
        Rook blackRook = new Rook(p2);
        blackRook.setArbitraryPosition('d', 5);
        test(blackRook, 'h', 5, true);
        test(blackRook, 'h', 1, false);
        test(blackRook, 'd', 9, false);
        System.out.println("Testing bishops:");
        Piece whiteBishop = new Bishop(p1);
        whiteBishop.setArbitraryPosition(new Position('d', 5));
        test(whiteBishop, 'b', 2, false);
        test(whiteBishop, 'a', 8, true);
        System.out.println("Testing knights:");
        Knight blackKnight = new Knight(p2);
        blackKnight.setArbitraryPosition('d', 4);
        test(blackKnight, 'e', 6, true);
        test(blackKnight, 'f', 6, false);
        test(blackKnight, 'c', 2, true);
        test(blackKnight, 'i', 8, false);
        System.out.println("Testing pawns:");
        Pawn whitePawn = new Pawn(p1);
        Pawn blackPawn = new Pawn(p2);
        blackPawn.setArbitraryPosition('b', 4);
        test(blackPawn, 'b', 3, true);
        test(blackPawn, 'b', 5, false);
        whitePawn.setArbitraryPosition('f', 2);
        test(whitePawn, 'f', 3, true);
        test(whitePawn, 'f', 4, true);
        blackPawn.setArbitraryPosition('g', 5);
        test(blackPawn, 'g', 4, true);
        test(blackPawn, 'g', 3, false);
        whitePawn.setArbitraryPosition('e', 7);
        test(whitePawn, 'd', 8, false);
        test(whitePawn, 'f', 8, false);
    }
    public static void test(Piece p, char x, int y, boolean valid) {
        if (p.isValidPosition(new Position(x, y)) == valid) {
            System.out.println(" > Test passed!");
        } else {
            System.out.println(" X Test NOT passed!");
        }
    }
}