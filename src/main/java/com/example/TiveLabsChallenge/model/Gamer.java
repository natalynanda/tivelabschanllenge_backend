package com.example.TiveLabsChallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gamer implements Comparable<Gamer> {
    @SerializedName("userId")
    @Expose()
    private String id; //ID

    @SerializedName("name")
    @Expose()
    private String name;

    @SerializedName("level")
    @Expose()
    private Integer level;

    @SerializedName("coins")
    @Expose()
    private Integer coins;

    @SerializedName("score")
    @Expose()
    private Integer score;

    @SerializedName("time")
    @Expose()
    private Integer time;

    @SerializedName("country")
    @Expose()
    private String country;

    @SerializedName("friends")
    @Expose()
    private List<String> friends;

    @SerializedName("globalRankPosition")
    @Expose()
    private Integer globalRankPosition;

    @SerializedName("friendRankPosition")
    @Expose()
    private Integer friendRankPosition;

    @Override
    public int compareTo(Gamer o) {
        if(this.score != null && o.getScore() != null) {
            if (this.score != o.getScore()) {
                return this.score - o.getScore();
            }
        }

        return this.name.compareTo(o.getName());
    }

    public Gamer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Integer getGlobalRankPosition() {
        return globalRankPosition;
    }

    public void setGlobalRankPosition(Integer globalRankPosition) {
        this.globalRankPosition = globalRankPosition;
    }

    public Integer getFriendRankPosition() {
        return friendRankPosition;
    }

    public void setFriendRankPosition(Integer friendRankPosition) {
        this.friendRankPosition = friendRankPosition;
    }
}
