/*
Prompt inicial utilizado en OpenCode:

Implementa en Java un verificador de paréntesis usando una pila enlazada
genérica: Nodo<T>, PilaEnlazada<T> y VerificadorParentesis. Usa una pila
porque cada ')' debe emparejarse con el '(' más reciente que aún no se cerró
(LIFO). En estanBalanceados(String expresion), apila cada '(', y al encontrar
')' retorna false si la pila está vacía; de lo contrario, desapila. Al final,
retorna pila.isEmpty(). Si expresion es null, retorna false. Ignora los
caracteres que no sean paréntesis.

Contempla cierres sin apertura y aperturas sin cierre. Cada push, pop e
isEmpty debe ser O(1); estanBalanceados debe ser O(n) en tiempo y O(n) en
espacio, donde n es la longitud de la expresión. En main, prueba dos
expresiones balanceadas y dos desbalanceadas y mide cada verificación con
System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio7_6 como clase pública para coincidir con el archivo.
Nodo<T>, PilaEnlazada<T> y VerificadorParentesis son clases auxiliares.
El verificador también contempla expresiones null y no considera caracteres
que no sean paréntesis.

Prueba de ejecución esperada:

"(2 + 3) * (5 - 1)" -> true
"((a + b) * c)" -> true
"(2 + 3" -> false
"())(" -> false
Los tiempos en nanosegundos varían entre ejecuciones.
*/
public class ejercicio7_6 {

    public static void main(String[] args) {
        String[] expresiones = {
            "(2 + 3) * (5 - 1)",
            "((a + b) * c)",
            "(2 + 3",
            "())("
        };

        for (String expresion : expresiones) {
            long inicio = System.nanoTime();
            boolean balanceada =
                    VerificadorParentesis.estanBalanceados(expresion);
            long tiempo = System.nanoTime() - inicio;

            System.out.println("\"" + expresion + "\" -> " + balanceada
                    + " | Tiempo: " + tiempo + " ns");
        }
    }
}

class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }

    T getDato() {
        return dato;
    }

    Nodo<T> getSiguiente() {
        return siguiente;
    }

    void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}

class PilaEnlazada<T> {
    private Nodo<T> head;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        T dato = head.getDato();
        head = head.getSiguiente();
        return dato;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class VerificadorParentesis {

    public static boolean estanBalanceados(String expresion) {
        if (expresion == null) {
            return false;
        }

        PilaEnlazada<Character> pila = new PilaEnlazada<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (caracter == '(') {
                // Guarda la apertura pendiente de emparejar.
                pila.push(caracter);
            } else if (caracter == ')') {
                /*
                 * Cierre prematuro: no hay un '(' pendiente que corresponda
                 * a este ')'.
                 */
                if (pila.isEmpty()) {
                    return false;
                }

                /*
                 * LIFO: retira el '(' abierto más recientemente, que es el
                 * primero que debe cerrarse.
                 */
                pila.pop();
            }
            // Los caracteres distintos de paréntesis no afectan el balance.
        }

        /*
         * Si quedaron aperturas en la pila, faltan cierres. Si está vacía,
         * cada '(' encontró su ')' correspondiente.
         */
        return pila.isEmpty();
    }
}