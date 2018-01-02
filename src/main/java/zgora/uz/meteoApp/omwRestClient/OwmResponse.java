package zgora.uz.meteoApp.omwRestClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import flexjson.JSONSerializer;
import zgora.uz.meteoApp.model.*;

@JsonIgnoreProperties
public class OwmResponse {
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;

    public Main getMain() {
        return this.main;
    }

    public void setMain(final Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return this.wind;
    }

    public void setWind(final Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return this.clouds;
    }

    public void setClouds(final Clouds clouds) {
        this.clouds = clouds;
    }

    public Rain getRain() {
        return this.rain;
    }

    public void setRain(final Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return this.snow;
    }

    public void setSnow(final Snow snow) {
        this.snow = snow;
    }

}
