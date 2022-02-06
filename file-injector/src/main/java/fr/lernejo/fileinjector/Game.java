package fr.lernejo.fileinjector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    public final Integer id;
    public final String title;
    public final String thumbnail;
    public final String short_description;
    public final String genre;
    public final String platform;
    public final String publisher;
    public final String developer;
    public final String release_date;
    public final String profile;
    public final String game;

    public Game(@JsonProperty("id") Integer id,
                @JsonProperty("title") String title,
                @JsonProperty("thumbnail") String thumbnail,
                @JsonProperty("short_description") String short_description,
                @JsonProperty("genre") String genre,
                @JsonProperty("platform") String platform,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("developer") String developer,
                @JsonProperty("release_date") String release_date,
                @JsonProperty("freetogame_profile_url") String profile,
                @JsonProperty("game_url") String game) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.short_description = short_description;
        this.genre = genre;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
        this.release_date = release_date;
        this.profile = profile;
        this.game = game;
    }
}
