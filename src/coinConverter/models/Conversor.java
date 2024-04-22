package coinConverter.models;

import com.google.gson.JsonObject;

public class Conversor {

    private String baseValue;
    private String changedValue;
    private double conversionValue;

    public Conversor(String baseValue, String changedValue, Double conversionValue) {
        this.baseValue = baseValue;
        this.changedValue = changedValue;
        this.conversionValue = conversionValue;
    }

    public double moneyConversion(double value) {
        double newValue = value * this.conversionValue;
        return newValue;
    }
}
