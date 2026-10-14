public class ejercicio1_1 {
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array nulo o vacío");
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] vector = {5, 3, 9, -2, 7};
        System.out.println("Vector: " + java.util.Arrays.toString(vector));
        int minimo = findMin(vector);
        System.out.println("Mínimo: " + minimo);
    }
}