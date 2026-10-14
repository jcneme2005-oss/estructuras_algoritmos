/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase ValidadorExpresiones con un método estático
 * validarExpresion(String expresion). Usa una pila para apilar (, [ y {
 * al encontrarlos. Al encontrar ), ] o }, comprueba que la pila no esté
 * vacía, desapila y verifica que el delimitador coincida. Al terminar,
 * retorna pila.isEmpty(). En main, prueba '(5 + 3) * (2 + 1)',
 * '(5 + 3)) * (2 + 1', '((5 + 3) * 2' y '{[5 + (3 * 2)]}',
 * midiendo el tiempo con System.nanoTime()."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio3_4 para coincidir con el archivo;
 * ValidadorExpresiones es una clase auxiliar. Se utilizó java.util.Stack,
 * como indica el prompt.
 *
 * Prueba de ejecución esperada:
 * "(5 + 3) * (2 + 1)" -> true  | Tiempo: [variable] ns
 * "(5 + 3)) * (2 + 1" -> false | Tiempo: [variable] ns
 * "((5 + 3) * 2" -> false     | Tiempo: [variable] ns
 * "{[5 + (3 * 2)]}" -> true   | Tiempo: [variable] ns
 */
import java.util.Stack;

public class ejercicio3_4 {

    public static void main(String[] args) {
        String[] expresiones = {
            "(5 + 3) * (2 + 1)",
            "(5 + 3)) * (2 + 1",
            "((5 + 3) * 2",
            "{[5 + (3 * 2)]}"
        };

        for (String expresion : expresiones) {
            long inicio = System.nanoTime();
            boolean valida = ValidadorExpresiones.validarExpresion(expresion);
            long tiempoTranscurrido = System.nanoTime() - inicio;

            System.out.println("\"" + expresion + "\" -> " + valida
                    + " | Tiempo: " + tiempoTranscurrido + " ns");
        }
    }
}

class ValidadorExpresiones {

    public static boolean validarExpresion(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            // Apila cada delimitador de apertura para comprobar su cierre después.
            if (caracter == '(' || caracter == '[' || caracter == '{') {
                pila.push(caracter);
            } else if (caracter == ')' || caracter == ']' || caracter == '}') {

                /*
                 * Primera causa de invalidez: subdesbordamiento.
                 * No existe apertura pendiente que corresponda a este cierre,
                 * por lo que no se puede hacer pop().
                 */
                if (pila.isEmpty()) {
                    return false;
                }

                // LIFO: obtiene la apertura más reciente para comprobar el anidamiento.
                char apertura = pila.pop();

                /*
                 * Solo se aceptan los pares correspondientes:
                 * () con (), [] con [] y {} con {}.
                 * Así se rechazan cierres cruzados como ([)].
                 */
                if ((caracter == ')' && apertura != '(')
                        || (caracter == ']' && apertura != '[')
                        || (caracter == '}' && apertura != '{')) {
                    return false;
                }
            }
        }

        /*
         * Segunda causa de invalidez: si quedan aperturas en la pila,
         * no se encontró su delimitador de cierre antes de terminar la cadena.
         */
        return pila.isEmpty();
    }
}
