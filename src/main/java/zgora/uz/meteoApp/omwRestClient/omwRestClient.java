package zgora.uz.meteoApp.omwRestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class omwRestClient {

    private final RestTemplate restTemplate;
    @Value("${owm.url}")
    String url;

    @Value("${owm.apiKey}")
    String apiKey;

    @Autowired
    public omwRestClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OwmResponse getOwmResponse(final String cityId) {
        return this.restTemplate.getForObject(this.url, OwmResponse.class, cityId, this.apiKey);
    }

    public String getFormattedJsonResponse(OwmResponse response) throws JsonProcessingException {
        ObjectMapper mapper= new ObjectMapper();
        String json=mapper.writeValueAsString(response);
        return pretifyResponse(json);
    }

    public ResponseToProcess getResponseToProcess(OwmResponse response){
        return new ResponseToProcess(response.getMain().getTemp(),response.getMain().getHumidity(), response.getMain().getPressure());
    }

    private String pretifyResponse (final String input){
        String response= input.replaceAll("\\{|\\}|\"","");
        response= response.replaceAll("main:","<strong>main:</strong><br>");
        response= response.replaceAll("wind:","<strong>wind:</strong><br>");
        response= response.replaceAll("clouds:","<strong>clouds:</strong><br>");
        response= response.replaceAll("rain:","<strong>rain:</strong><br>");
        response= response.replaceAll("snow:","<strong>snow:</strong><br>");
        response= response.replaceAll("null","0");
        response= response.replaceAll(",","<br>");

        //TODO replaceAll values  with values+ unit

        return response;
    }
}

