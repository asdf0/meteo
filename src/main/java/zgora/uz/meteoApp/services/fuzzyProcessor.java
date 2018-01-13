package zgora.uz.meteoApp.services;

import zgora.uz.meteoApp.services.ItemRepresentation.Item;

public interface fuzzyProcessor {
    public Item getPossibilityForTemperature(double temperature);
    public Item getPossibilityForPressure(double pressure);
    public Item getPossibilityForHumidity(double humidity);

}
