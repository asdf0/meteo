package zgora.uz.meteoApp.omwRestClient;

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
}

