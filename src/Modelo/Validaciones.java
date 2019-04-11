package Modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 * [Validaciones] Clase que permite validar si los elementos de alguna clase ya existen y sus excepciones
 * @since 09/05/2018
 * @version 2.0
 * @author Julian Andres Castaño - 1625743
 */

public class Validaciones {   
   
//=======================================================================================================
    /**
     * Metodo generico que permite validar 2 tipos de excepciones y si los campos estan vacios,se debe inicilizar con la cantidad
     * de elementos tipo String, tipo Int o tipo Double que se quieran validar [0 si no hay ninguno de ese tipo]
     * @param arregloValidar Almacena los datos ingresados en un solo array para recorrerlo mediante condicionales
     * @param cantidadString Almacena la cantidad real de datos que son String del array a validar 
     * @param cantidadInt Almacena la cantidad real de datos que son Enteros del array a validar
     * @param cantidadDouble Almacena la cantidad real de datos que son Doubles del array a validar
     * @return un String vacio en caso de que todas las validaciones sean correctas o el mensaje de error en caso de que no se cumpla la condicion
     */

    public String validarExcepciones(String arregloValidar[],int cantidadString, int cantidadInt, int cantidadDouble)
    {
          
        String imprimir="";
        int tamanoArray = arregloValidar.length; 
        if (cantidadString >=0)
        {
            int inicio = 0;
            int fin = tamanoArray;
            while (inicio<fin)
            {
              if(arregloValidar[inicio].equals(""))
              {
                return "Algun campo se encuentra vacio";
              }      
              inicio++;
            }
        }

        if (cantidadInt !=0)
        {
            int inicio = cantidadString;
            int fin = cantidadString + cantidadInt;
            while (inicio<fin)
            {
                try{
                    long numero = Long.parseLong(arregloValidar[inicio]);
                }
                catch (NumberFormatException e){
                    return "Tenga en cuenta que el dato ingresado: '" +arregloValidar[inicio]+"' debe ser un numero";
                }
                inicio++;
            } 

        }

        if (cantidadDouble !=0)
        {
            int inicio = cantidadString + cantidadInt;
            int fin = tamanoArray;
            while (inicio<fin)
            {
                try{
                    double numero = Double.parseDouble(arregloValidar[inicio]);
                }
                catch (NumberFormatException e){
                    return "Tenga en cuenta que el dato ingresado: '" +arregloValidar[inicio]+"' debe ser de tipo double";
                }
                inicio++;
            }       
        }

        return imprimir;      
    }
    
//=======================================================================================================
    
    /**
     * Metodo abstracto para verificar si algun elemento de un arreglo de Strings contiene dentro de si un numero
     * @param arregloValidar Almacena los datos ingresados en un solo array para recorrerlo mediante 2 ciclos anidados
     * @return Un String vacio en caso de que ningun elemento de la variable arregloValidar contenga dentro de si un numero
     */    
    
    public String soloLetras (String[] arregloValidar)
    {               
        int tamanoCadenas = arregloValidar.length;
        int iterador = 0;
        String imprimir = "";
        while(tamanoCadenas!=0)
        {
            String cadenaNueva="";
            CharSequence cadenaIngresada = arregloValidar[iterador];       
            for(int i  = 0; i < cadenaIngresada.length(); i++)
            {
                char c = cadenaIngresada.charAt(i);
                if((c==32)||(c > 64 && c < 91)||(c > 96 && c < 123) || (c=='ñ') || (c=='Ñ') || 
                                    (c=='á') || (c=='é') || (c=='í') || (c=='ó') || (c=='ú') || (c=='Á') || 
                                        (c=='É') || (c=='Í') || (c=='Ó') || (c=='Ú'))
                {
                    cadenaNueva+=c;
                }
            }
            
            if  (!(cadenaNueva.equals(cadenaIngresada)))
            {
                return "El dato ingresado '"+arregloValidar[iterador]+"' debe contener solo letras";                
            }
      
            tamanoCadenas--;
            iterador++;
        }
        return imprimir;
    }                     
//=======================================================================================================
    
    /**
     * Metodo para limitar la cantidad de caracteres sean Numeros o Texto de un campo de Texto
     * @param letraIngresada Variable que almacena el evento de la tecla ingresada
     * @param campo Variable que almacena el JTextField al cual se le aplicara este metodo
     * @param cantidadCadena Variable que almacena el valor maximo de la cadena del jTexttField
     * @param cantidadNumber Variable que almacena el valor maximo de numeros del jTextField
     */    
    
    public void limitarCantidadChar(KeyEvent letraIngresada, JTextField campo ,int cantidadCadena, int cantidadNumber)
    {   
        if(cantidadCadena!=0)
        {
            if (campo.getText().replaceAll("\\s","").length()==cantidadCadena)
            {
                letraIngresada.consume();
            }
        }
        
        else
        {
            if (campo.getText().replaceAll("\\s","").length()!=cantidadNumber)
            {
                letraIngresada.consume();
            }
        }        
    }

//=======================================================================================================
}


