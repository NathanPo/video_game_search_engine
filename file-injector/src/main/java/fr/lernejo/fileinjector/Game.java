package fr.lernejo.fileinjector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    public Integer id;
    public String title;
    public String thumbnail;
    public String short_description;
    public String genre;
    public String platform;
    public String publisher;
    public String developer;
    public String release_date;
    @JsonProperty("freetogame_profile_url")
    public String profile;
    @JsonProperty("game_url")
    public String game;
}
