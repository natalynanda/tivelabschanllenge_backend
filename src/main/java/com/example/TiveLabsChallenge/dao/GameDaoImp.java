package com.example.TiveLabsChallenge.dao;

import com.example.TiveLabsChallenge.model.Gamer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameDaoImp implements GameDao {

   @Override
   public String getCalculatedData(String jsonData) {
       GsonBuilder gsonBuilder = new GsonBuilder();
       gsonBuilder.serializeNulls();
       Type listTypeOT = new TypeToken<ArrayList<Gamer>>() {}.getType();
       Gson gson = gsonBuilder.create();
       List<Gamer> gamerList = gson.fromJson(jsonData, listTypeOT);

       for(Gamer gamer : gamerList) {
            gamer = levelUpIfNeeded(gamer);
            gamer.setScore(calculateScore(gamer));
       }
        gamerList = rankPosition(gamerList);
       gamerList = friendRankPosition(gamerList);
       return gson.toJson(gamerList);
   }

   private Integer calculateScore(Gamer gamer) {
       return (gamer.getLevel() * gamer.getCoins()) + gamer.getTime();
   }

   private Gamer levelUpIfNeeded(Gamer gamer) {
       if(gamer.getTime().compareTo(28800) < 0) {
           return gamer;
       } else {
           Integer div = gamer.getTime() / 28800;
           Integer restTime = gamer.getTime() - (div * 28800);
           gamer.setLevel(gamer.getLevel() + div);
           gamer.setTime(restTime);

           return gamer;
       }
   }

   private List<Gamer> rankPosition(List<Gamer> gamers) {
       List<Gamer> sortedGamer = gamers
               .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

       int i = 1;
      for(Gamer gamer : sortedGamer) {
          gamer.setGlobalRankPosition(i);
          i ++;
      }
       return sortedGamer;

   }

    private Gamer rankPositionByFriend(List<Gamer> gamers, String gamerId) {
        List<Gamer> sortedGamer = gamers
                .stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        int i = 1;
        for(Gamer gamer : sortedGamer) {
            if(gamer.getId().equals(gamerId)) {
                gamer.setFriendRankPosition(i);
            }
            i ++;
        }
        return sortedGamer.stream()
                .filter(customer -> gamerId.equals(customer.getId()))
                .findAny()
                .orElse(null);

    }

   private List<Gamer> friendRankPosition(List<Gamer> gamers) {
       for(Gamer gamer: gamers) {
            gamer.setFriendRankPosition(1);
            List<Gamer> gamersOfFriends = new ArrayList<>();
            gamersOfFriends.add(gamer);
           for(String friend : gamer.getFriends()) {
               Gamer friendGamer = gamers.stream()
                       .filter(customer -> friend.equals(customer.getId()))
                       .findAny()
                       .orElse(null);
               if(friendGamer != null)
                    gamersOfFriends.add(friendGamer);
           }
           if(gamersOfFriends.size() > 1) {
               gamer = rankPositionByFriend(gamersOfFriends, gamer.getId());
           }
       }
       return gamers;
   }

}
