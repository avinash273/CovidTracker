package com.example.CovidTracker.services;

import com.example.CovidTracker.Models.CurrentStats;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.net.URL;

@Service
public class CovidDataService {

    private CurrentStats currentStat;
    private String URL;

    public void setURL(String URL) {
        this.URL = URL;
    }

    public CurrentStats getCurrentStat() {
        return currentStat;
    }

    //execute after creating the instance of this service
//    @PostConstruct
//    @Scheduled(cron = "* * 1 * * *")
    public void fetchData(String url){
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.currentStat= mapper.readValue(new URL(url),CurrentStats.class);
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}

