package zgora.uz.meteoApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.round;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private final static Double KELVIN_TO_CELSIUS_DIFFERENCE =273.15;

    private double temp;
    private int humidity;
    private double pressure;


    public double getTemp() {
        return round(temp-KELVIN_TO_CELSIUS_DIFFERENCE,1);
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}
