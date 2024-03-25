


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Personal
 */

public class PolindromoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Prolindromo pali = new Prolindromo();
        String phrase1 = "Buenas Profe";
        String phrase2 = "La ruta natural";

        System.out.println("'" + phrase1 + "' "
                + pali.getResulPalindromo(phrase1));
        System.out.println("'" + phrase2 + "' "
                + pali.getResulPalindromo(phrase2));

        // Uso de la API Stack Overflow para verificar un palíndromo
        String phrase3 = "anitalavalatina";
        System.out.println("'" + phrase3 + "' "
                + pali.getResulPalindromo(phrase3));
    }

}
