package com.example.TiveLabsChallenge.service;

import com.example.TiveLabsChallenge.dao.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GameServiceImpl")
public class GameServiceImpl implements GameService {
    @Autowired
    private GameDao gameDao;

    @Override
    public String getCalculatedData(String jsonData) {
        return gameDao.getCalculatedData(jsonData);
    }
}