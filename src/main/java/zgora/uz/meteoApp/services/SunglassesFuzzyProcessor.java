package zgora.uz.meteoApp.services;

import zgora.uz.meteoApp.services.ItemRepresentation.Item;
import zgora.uz.meteoApp.services.ItemRepresentation.ItemName;
import zgora.uz.meteoApp.services.exceptions.ProcessingException;

public class SunglassesFuzzyProcessor implements fuzzyProcessor{
    @Override
    public Item getPossibilityForTemperature(double temperature) {
        return createItemForValue(0); //Sunglasses usage doesn't depend on temperature.
    }

    @Override
    public Item getPossibilityForPressure(double pressure) {
        if (pressure <= 900) {
            return createItemForValue(0);
        } else if (pressure > 900) {
            return createItemForValue((pressure * 0.0066) - 6);} //100% possibility on 1050 hPa ||WAS 0.66
        else {
            throw new ProcessingException("Bad pressure parameter : " + pressure);
        }
    }

    @Override
    public Item getPossibilityForHumidity(double humidity) {
        if (humidity >= 0 && humidity <=70) {
            return createItemForValue((humidity * -0.014) +1);
        } else if (humidity > 70 && humidity<=100) {
            return createItemForValue(0);}
        else {
            throw new ProcessingException("Bad humidity parameter : " + humidity);
        }
    }

    private Item createItemForValue(double value){
        return new Item(value, ItemName.SUNGLASSES);
    }
}
