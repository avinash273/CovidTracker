package com.example.CovidTracker.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;

@Service
public class StateListService {

    private final String PATH = "src/main/resources/state-list.json";
    private HashMap<String,String> stateList;

    public HashMap<String,String> getStateList(){
        return stateList;
    }

    @PostConstruct
    public void fetchData() {
        stateList = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            stateList = mapper.readValue(new File(PATH), HashMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
