//(difficult) Create a base class with an abstract print() method that is overridden in a derived class.
//The overridden version of the method prints the value of an int variable defined in the derived class.
//At the point of definition of this variable, give it a nonzero value. In the base-class constructor,
//call this method.In main, create an object of the derived type, and then call its print method.
//Explain the results.
abstract class BaseClass{

    public BaseClass(){
        this.Print();
    }
    public abstract void Print();
}


public class exercise_03_10{
    public static void main(String[] args) {
        DrivenClass drivenClass = new DrivenClass();
        drivenClass.Print();
    }
}
class DrivenClass extends BaseClass{

    private int i = 9;

    public void Print() {
        System.out.println(this.i);
    }

}
