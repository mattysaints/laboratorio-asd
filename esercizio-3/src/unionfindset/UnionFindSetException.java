package unionfindset;

/**
 *  Thrown when trying to find a missing element in a set
 */
public class UnionFindSetException extends Exception{
  
   /**
    * @param message: the message displayed when the exception is thrown
    */
    public UnionFindSetException(String message){
        super(message);
    } // UnionFindSetException
}