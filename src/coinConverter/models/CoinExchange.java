package coinConverter.models;

import com.google.gson.JsonObject;

public record CoinExchange(String base_code, JsonObject conversion_rates) {
}
