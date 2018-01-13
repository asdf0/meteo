package zgora.uz.meteoApp.services.ItemRepresentation;

public class Item {
    private Double possibilityValue;
    private ItemName name;

    public Item(Double possibilityValue, ItemName name) {
        this.possibilityValue = possibilityValue;
        this.name = name;
    }

    public Double getPossibilityValue() {
        return possibilityValue;
    }

    public void setPossibilityValue(Double possibilityValue) {
        this.possibilityValue = possibilityValue;
    }

    public ItemName getName() {
        return name;
    }

    public void setName(ItemName name) {
        this.name = name;
    }
}
