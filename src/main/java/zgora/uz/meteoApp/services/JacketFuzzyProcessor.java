package zgora.uz.meteoApp.services;

import zgora.uz.meteoApp.services.ItemRepresentation.Item;
import zgora.uz.meteoApp.services.ItemRepresentation.ItemName;
import zgora.uz.meteoApp.services.exceptions.ProcessingException;

public class JacketFuzzyProcessor implements fuzzyProcessor {
    @Override
    public Item getPossibilityForTemperature(double temperature) {
        if (temperature >= -50 && temperature <=10) {
            return createItemForValue(1.0);
        } else if (temperature > 10 && temperature <= 20) {
            return createItemForValue((temperature * -0.1) + 2);
        } else if (temperature > 20) {
            return createItemForValue(0);
        } else {
            throw new ProcessingException("Bad temperature parameter: " + temperature);
        }
    }

    @Override
    public Item getPossibilityForPressure(double pressure) {
        return createItemForValue(0); //Jacket usage doesn't depend on pressure.
    }

    @Override
    public Item getPossibilityForHumidity(double humidity) {
        return createItemForValue(0); //Jacket usage doesn't depend on humidity.
    }

    private Item createItemForValue(double value){
        return new Item(value, ItemName.JACKET);
    }
}
