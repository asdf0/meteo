package zgora.uz.meteoApp.services;

import zgora.uz.meteoApp.services.ItemRepresentation.Item;
import zgora.uz.meteoApp.services.ItemRepresentation.ItemName;
import zgora.uz.meteoApp.services.exceptions.ProcessingException;

public class UmbrellaFuzzyProcessor implements fuzzyProcessor {
    @Override
    public Item getPossibilityForTemperature(double temperature) {
        if (temperature <= 0) {
            return createItemForValue(0.0);
        } else if (temperature > 0 && temperature <= 30) {
            return createItemForValue((temperature * 1.67) + 0.5);
        } else if (temperature > 30) {
            return createItemForValue(0);
        } else {
            throw new ProcessingException("Bad temperature parameter: " + temperature);
        }
    }

    @Override
    public Item getPossibilityForPressure(double pressure) {
        if (pressure <= 900) {
            return createItemForValue(1.0);
        } else if (pressure > 900) {
            return createItemForValue((pressure * -0.0067) + 7);} //0% possibility on 1050 hPa
        else {
            throw new ProcessingException("Bad pressure parameter : " + pressure);
        }
    }

    @Override
    public Item getPossibilityForHumidity(double humidity) {
        if (humidity == 0) {
            return createItemForValue(0);
        } else if (humidity > 0 && humidity<=100) {
            return createItemForValue((humidity * 0.01) + 0);}
        else {
            throw new ProcessingException("Bad humidity parameter : " + humidity);
        }
    }

    private Item createItemForValue(double value){
        return new Item(value, ItemName.UMBRELLA);
    }
}
