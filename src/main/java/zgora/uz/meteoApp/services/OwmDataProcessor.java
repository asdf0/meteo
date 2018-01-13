package zgora.uz.meteoApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgora.uz.meteoApp.omwRestClient.ResponseToProcess;
import zgora.uz.meteoApp.services.ItemRepresentation.Item;
import zgora.uz.meteoApp.services.ItemRepresentation.ItemName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class OwmDataProcessor {
    private static final String JACKET_IMAGE_NAME="jacket";
    private static final String UMBRELLA_IMAGE_NAME="umbrella";
    private static final String SUNGLASSES_IMAGE_NAME="sunglasses";

    private fuzzyProcessor jacketFuzzyProcessor;
    private fuzzyProcessor umbrellaFuzzyProcessor;
    private fuzzyProcessor sunglassesFuzzyProcessor;

    @Autowired
    public OwmDataProcessor() {
        this.jacketFuzzyProcessor = new JacketFuzzyProcessor();
        this.umbrellaFuzzyProcessor = new UmbrellaFuzzyProcessor();
        this.sunglassesFuzzyProcessor = new SunglassesFuzzyProcessor();
    }

    public String getPropperEquipmentResource(ResponseToProcess response) {
        Item resultItem=calculateEquipmentResult(response.getTemperature(),response.getHumidity(),response.getPressure());
        ItemName resultItemName=resultItem.getName();

        switch (resultItemName){
            case JACKET: return JACKET_IMAGE_NAME;
            case UMBRELLA: return UMBRELLA_IMAGE_NAME;
            case SUNGLASSES: return SUNGLASSES_IMAGE_NAME;
            default: return "empty";
        }

    }

    private Item calculateEquipmentResult(double temperature, double humidity, double pressure) {
        final Item[][] table = prepareResultTable(temperature, humidity, pressure);
        final Comparator<Item> comparator = Comparator.comparingDouble(Item::getPossibilityValue);
        final List<Item> itemsList = new ArrayList<>();
        itemsList.add(getMinimumValueForItem(table, 0));
        itemsList.add(getMinimumValueForItem(table, 1));
        itemsList.add(getMinimumValueForItem(table, 2));

        return itemsList.stream().max(comparator).get();


    }

    private Item[][] prepareResultTable(double temperature, double humidity, double pressure) {
        Item[][] resultTable = new Item[3][3];

        resultTable[0][0] = umbrellaFuzzyProcessor.getPossibilityForTemperature(temperature);
        resultTable[1][0] = umbrellaFuzzyProcessor.getPossibilityForHumidity(humidity);
        resultTable[2][0] = umbrellaFuzzyProcessor.getPossibilityForPressure(pressure);

        resultTable[0][1] = jacketFuzzyProcessor.getPossibilityForTemperature(temperature);
        resultTable[1][1] = jacketFuzzyProcessor.getPossibilityForHumidity(humidity);
        resultTable[2][1] = jacketFuzzyProcessor.getPossibilityForPressure(pressure);

        resultTable[0][2] = sunglassesFuzzyProcessor.getPossibilityForTemperature(temperature);
        resultTable[1][2] = sunglassesFuzzyProcessor.getPossibilityForHumidity(humidity);
        resultTable[2][2] = sunglassesFuzzyProcessor.getPossibilityForPressure(pressure);

        return resultTable;
    }

    private Item getMinimumValueForItem(final Item[][] resultTable, int positionOfItem) {
        final List<Item> itemsList = new ArrayList<>();
        final Comparator<Item> comparator = Comparator.comparingDouble(Item::getPossibilityValue);
        for (int i = 0; i < 3; i++) {
            if (resultTable[i][positionOfItem].getPossibilityValue() > 0) { //We dont pick 0 as minimum value, if theres other values.
                itemsList.add(resultTable[i][positionOfItem]);
            }
        }
        if (itemsList.isEmpty()) {
            return new Item(0.0, resultTable[0][positionOfItem].getName());
        }
        return itemsList.stream().min(comparator).get();
    }


}
