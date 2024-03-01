package com.example.demo;


import java.net.http.HttpResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;



@Controller
public class WeatherController {
	 @Value("${openweathermap.api.key}")
	    private String openWeatherMapApiKey;

	    @GetMapping("/weather")
	    public String showWeatherForm() {
	        return "form"; // Assuming you have a Thymeleaf template named "weatherForm.html"
	    }

	    @PostMapping("/weather")
	    public String getWeather(@RequestParam("city") String city, Model model) {
	        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + openWeatherMapApiKey;

	        RestTemplate restTemplate = new RestTemplate();
	        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);

	        model.addAttribute("city", city);
	        model.addAttribute("temperature", weatherResponse.getMain().getTemp());
	        // Add more attributes as needed

	        return "display"; // Assuming you have a Thymeleaf template named "weather.html"
	    }
}

