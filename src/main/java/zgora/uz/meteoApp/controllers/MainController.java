package zgora.uz.meteoApp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.NativeJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import zgora.uz.meteoApp.omwRestClient.OwmResponse;
import zgora.uz.meteoApp.omwRestClient.ResponseToProcess;
import zgora.uz.meteoApp.omwRestClient.omwRestClient;
import zgora.uz.meteoApp.services.OwmDataProcessor;

@Controller
public class MainController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    zgora.uz.meteoApp.omwRestClient.omwRestClient omwRestClient;

    @Autowired
    OwmDataProcessor owmDataProcessor;

    @RequestMapping("/")
    public String index(){
        return "home";
    }

    @RequestMapping(value="/getForecastForCity", params = {"cityName"})
    public String getForecast(@RequestParam("cityName") String name, Model model) throws JsonProcessingException {

        OwmResponse response=omwRestClient.getOwmResponse(name);
        ResponseToProcess responseToProcess= omwRestClient.getResponseToProcess(response);
        String forecast= omwRestClient.getFormattedJsonResponse(response);
        String processedItemCode=owmDataProcessor.getPropperEquipmentResource(responseToProcess);

        model.addAttribute("itemCode",processedItemCode);
        model.addAttribute("cityName", name);
        model.addAttribute("forecastJson", forecast);
        return "forecastForCity";
    }
}
