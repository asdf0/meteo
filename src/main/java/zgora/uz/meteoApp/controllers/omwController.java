package zgora.uz.meteoApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zgora.uz.meteoApp.omwRestClient.OwmResponse;
import zgora.uz.meteoApp.omwRestClient.omwRestClient;

@RestController
public class omwController {

    @Autowired
    omwRestClient omwRestClient;

    @RequestMapping("/{cityName}")
    public OwmResponse index(@PathVariable String cityName){
        return omwRestClient.getOwmResponse(cityName);
    }
//
//    @RequestMapping("/")
//    public OwmResponse index(){
//        return omwRestClient.getOwmResponse("London");
//    }
}