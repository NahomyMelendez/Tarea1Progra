


/**
 *
 * @author Personal
 */
public class Prolindromo {

    public static boolean aPalin(String phrase) {
                return StackAPIHelper.isPalindromo(phrase);
    }

    public String getResulPalindromo(String phrase) {
        return aPalin(phrase) ? "es un palíndromo." : "no es un palíndromo.";
    }
}
//    public static boolean aPalin(String phrase) {
//        String normalizeFrace = normalizeFrace(phrase);
//        String invertPhrase = invertPhrase(normalizeFrace);
//        return normalizeFrace.equals(invertPhrase);
//    }
//
//    private static String normalizeFrace(String phrase) {
//        return phrase.replaceAll("\\s+", "").toLowerCase();
//    }
//
//    private static String invertPhrase(String phrase) {
//        StringBuilder reverse = new StringBuilder(phrase);
//        return reverse.reverse().toString();
//    }
//
//    public String getResulPalindromo(String phrase) {
//        return aPalin(phrase) ? "es un palíndromo." : "no es un palíndromo.";
//    }

}
