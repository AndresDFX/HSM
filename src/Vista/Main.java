package Vista;

/**
 *
 * @author Andres
 */
public class Main {
    
    public static VentanaLogin VL;
    
    public Main(){
           
    }
    
//=======================================================================================================
    
    public static void main (String [] args){
        VL = VentanaLogin.getInstancia();
        VL.setVisible(true); 
       
    }
    
}
