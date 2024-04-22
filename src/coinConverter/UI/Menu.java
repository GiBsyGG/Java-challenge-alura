package coinConverter.UI;

import coinConverter.models.CoinExchange;
import coinConverter.models.Conversion;
import coinConverter.models.Conversor;
import coinConverter.utils.ApiConnector;
import coinConverter.utils.HistorySerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public void startMenu(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ApiConnector connector = new ApiConnector();

        HistorySerializer historySerializer = new HistorySerializer();

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("***************************************");
            System.out.println("Write the money to convert (in capital letter, ex. USD):");
            String currentMoney = scanner.nextLine().toUpperCase();
            System.out.println("Write the money to convert (in capital letter, ex. USD):");
            String newMoney = scanner.nextLine().toUpperCase();
            System.out.println("Write the amount to convert (write -1 to leave):");
            double valueToConvert = scanner.nextDouble();

            if (currentMoney.equals("-1") || newMoney.equals("-1") || valueToConvert == -1){
                try {
                    historySerializer.saveRecords();
                } catch (Exception e) {
                    System.out.println("Error to save");
                }
                break;
            }

            String url = "https://v6.exchangerate-api.com/v6/9bfeab78275e205f81dd324e/latest/" + currentMoney;

            try {
                HttpResponse<String> response = connector.createRequest(url);

                CoinExchange coinExchange = gson.fromJson(response.body(), CoinExchange.class);

                double newMoneyValue = coinExchange.conversion_rates().asMap().get(newMoney).getAsDouble();

                Conversor conversor = new Conversor(currentMoney, newMoney, newMoneyValue);

                double result = conversor.moneyConversion(valueToConvert);

                System.out.printf("The value %s [%s] corresponds to => %s [%s]%n%n", valueToConvert, currentMoney, result, newMoney);

                historySerializer.recordCreation(new Conversion(currentMoney, valueToConvert, newMoney, result));

            } catch (IOException | InterruptedException e){
                System.out.println(e.getMessage());
            } catch (NullPointerException e){
                System.out.println("Badge not find");
            }catch (InputMismatchException e){
                System.out.println("Value not allowed");
            }
            catch (Exception e) {
                System.out.println("Unexpected error, try again");
            }
        }
    }
}