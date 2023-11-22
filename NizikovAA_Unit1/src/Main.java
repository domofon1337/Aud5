import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int birthDateSum = (9 + 4 + 2 + 4) * 2; // Пример даты: 09.04.2004
        int N = birthDateSum;
        int[] array = new int[N];
        Random random = new Random();


        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(201) - 100;
        }

        System.out.println(Arrays.toString(array));

        // Задание 1
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        double z = (double) sum / N;
        int countValuesAboveZ = 0;
        for (int value : array) {
            if (value > z) {
                countValuesAboveZ++;
            }
        }
        System.out.println("Задание 1. Количество значений, превышающих среднее: " + countValuesAboveZ);

        // Задание 2
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        int differenceMaxMin = max - min;
        System.out.println("Задание 2. Разность между наибольшим и наименьшим значением: " + differenceMaxMin);

        // Задание 3
        int countDivisibleByIndex = 0;
        for (int i = 0; i < N; i++) {
            if (array[i] % (i + 1) == 0) {
                countDivisibleByIndex++;
            }
        }
        System.out.println("Задание 3. Количество элементов, делящихся на свой номер: " + countDivisibleByIndex);

        // Задание 4
        int countEvenPositions = N / 2;
        double product = 1.0;
        for (int i = 1; i < N; i += 2) {
            product *= array[i];
        }
        double geometricMean = Math.pow(product, 1.0 / countEvenPositions);
        System.out.println("Задание 4. Среднее геометрическое элементов на четных позициях: " + geometricMean);
    }
}
