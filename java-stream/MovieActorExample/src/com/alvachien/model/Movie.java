package com.alvachien.model;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    private String title;
    private int releaseYear;

    private Set<Actor> actors = new HashSet<>();

    public Movie(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    public Set<Actor> actors() {
        return this.actors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Move{title = " + title + ", releaseYear = " + releaseYear + "}";
    }
}
