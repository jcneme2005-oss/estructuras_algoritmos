public class ejercicio9_2 {

    public static boolean esPalindromo(String cadena) {
        // Una referencia null no representa una cadena que pueda comprobarse.
        if (cadena == null) {
            return false;
        }

        /*
         * Caso base de éxito: una cadena vacía o de un carácter es
         * palíndroma. No quedan extremos que comparar, así que se detiene
         * la recursión y se devuelve true.
         */
        if (cadena.length() <= 1) {
            return true;
        }

        /*
         * Compara los caracteres de los extremos actuales.
         * Si no coinciden, la cadena no puede ser un palíndromo y se
         * devuelve false inmediatamente, sin revisar el interior.
         */
        if (cadena.charAt(0) != cadena.charAt(cadena.length() - 1)) {
            return false;
        }

        /*
         * substring(1, cadena.length() - 1) elimina el primer y el último
         * carácter. La llamada recursiva compara así la siguiente pareja
         * de extremos, avanzando desde afuera hacia adentro.
         */
        return esPalindromo(cadena.substring(1, cadena.length() - 1));
    }

    public static void main(String[] args) {
        String[] casos = {
            "",
            "A",
            "abba",
            "reconocer",
            "hola",
            "abxa"
        };

        for (String cadena : casos) {
            long inicio = System.nanoTime();
            boolean resultado = esPalindromo(cadena);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("\"" + cadena + "\" es palíndromo: "
                    + resultado + " | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}