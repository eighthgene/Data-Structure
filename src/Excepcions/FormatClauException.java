package Excepcions;

/**
 * Created by Oleg Sokolov on 04/06/2017.
 */
public class FormatClauException extends Exception {
    private static final long serialVersionUID = 1L;

    public FormatClauException(){
        super("ERROR : En aqesta estructura clau ha de ser un String");
    }
}
