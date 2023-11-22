package ru.mirea.nizikovaa.tasktwo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.FileWriter;
import java.io.IOException;

public class Task2 {

    public static class PrimeRequest {
        private String name;
        private int length;
        private String numbers;

        public PrimeRequest(String name, int length, String numbers) {
            this.name = name;
            this.length = length;
            this.numbers = numbers;
        }

        public String getName() {
            return name;
        }

        public int getLength() {
            return length;
        }

        public String getNumbers() {
            return numbers;
        }
    }

    public interface MyApi {
        @POST("/")
        Call<PrimeResponse> sendPrimeRequest(@Body PrimeRequest primeRequest);
    }

    public static class PrimeResponse {
        @JsonProperty("answer")
        private boolean answer;

        public boolean isAnswer() {
            return answer;
        }
    }

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://prime.tms-studio.ru:8085/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        PrimeRequest primeRequest = new PrimeRequest("Иванов Иван Иванович", 10, "10,32,40,21,56,40,90,23,91,40");

        Call<PrimeResponse> call = myApi.sendPrimeRequest(primeRequest);

        try {
            PrimeResponse response = call.execute().body();

            if (response != null) {
                saveResponseToFile(response.isAnswer());
            } else {
                System.err.println("Error receiving response from the server");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveResponseToFile(boolean answer) {
        ObjectMapper objectMapper = new ObjectMapper();
        PrimeResponse response = new PrimeResponse();
        response.answer = answer;

        try (FileWriter fileWriter = new FileWriter("response.json")) {
            objectMapper.writeValue(fileWriter, response);
            System.out.println("Response saved to 'response.json' file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
