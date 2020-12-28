package com.example.TiveLabsChallenge.controller;

import com.example.TiveLabsChallenge.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.FormParam;

@RestController
@RequestMapping("/v1")
@CrossOrigin(maxAge = 3600)
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/calculateJson", method = RequestMethod.POST, headers = "Accept=application/json")
    public String getCalculatedJsonData(@FormParam("jsonData") String jsonData) {
        String result = gameService.getCalculatedData(jsonData);
        return result;
    }
}
