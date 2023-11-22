package ru.mirea.nizikovaa.taskone;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task1 {

    public interface MyApi {
        @GET("/")
        Call<JsonNode> fetchNumbersArray();
    }

    public static void main(String[] arguments) {
        Retrofit client = new Retrofit.Builder()
                .baseUrl("http://prime.tms-studio.ru:8085/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        MyApi api = client.create(MyApi.class);

        Call<JsonNode> request = api.fetchNumbersArray();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<Response<JsonNode>> futureResponse = executor.submit(request::execute);

            Response<JsonNode> response = futureResponse.get();

            if (response.isSuccessful() && response.body() != null) {
                JsonNode jsonResponse = response.body();
                String numbersArrayString = jsonResponse.get("arrayNumbers").asText();

                int[] numbersArray = parseNumbersArray(numbersArrayString);

                int maximum = findMaximum(numbersArray);
                System.out.println("Maximum number: " + maximum);

                int minimum = findMinimum(numbersArray);
                System.out.println("Minimum number: " + minimum);

                double average = calculateAverage(numbersArray);
                System.out.println("Average value: " + average);
            } else {
                System.err.println("Error while receiving the response: " + response.message());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static int[] parseNumbersArray(String numbersArrayString) {
        String[] numbersStringArray = numbersArrayString.split(",");
        int[] numbersArray = new int[numbersStringArray.length];
        for (int i = 0; i < numbersStringArray.length; i++) {
            numbersArray[i] = Integer.parseInt(numbersStringArray[i].trim());
        }
        return numbersArray;
    }

    private static int findMaximum(int[] numbersArray) {
        int maximum = Integer.MIN_VALUE;
        for (int number : numbersArray) {
            if (number > maximum) {
                maximum = number;
            }
        }
        return maximum;
    }

    private static int findMinimum(int[] numbersArray) {
        int minimum = Integer.MAX_VALUE;
        for (int number : numbersArray) {
            if (number < minimum) {
                minimum = number;
            }
        }
        return minimum;
    }

    private static double calculateAverage(int[] numbersArray) {
        int sum = 0;
        for (int number : numbersArray) {
            sum += number;
        }
        return (double) sum / numbersArray.length;
    }
}
