package com.example.CovidTracker.Controller;

import com.example.CovidTracker.Models.CountryStateSelection;
import com.example.CovidTracker.services.CovidDataService;
import com.example.CovidTracker.services.StateListService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    String STAT_URL="https://api.covidtracking.com/v1/states/";

    @Autowired
    StateListService stateService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("stateList", stateService.getStateList());
        model.addAttribute("stateSelection", new CountryStateSelection());
        return "index";
    }
    @PostMapping("/details")
    public String details(@ModelAttribute("stateSelection") CountryStateSelection stateSelection,Model model){
        String url = STAT_URL+stateSelection.getState().toLowerCase()+"/current.json";
        covidDataService.fetchData(url);

        model.addAttribute("currentStat",covidDataService.getCurrentStat());
        return  "details";
    }
    @GetMapping("/error")
    public String index(){
        return "error";
    }
}
