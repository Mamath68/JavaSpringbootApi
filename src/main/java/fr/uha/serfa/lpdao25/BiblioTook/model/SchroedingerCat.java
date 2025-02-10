package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.util.concurrent.ThreadLocalRandom;

public class SchroedingerCat {

    public boolean isAlive = false;
    private String name = "pauvre chat";

    public SchroedingerCat(String name) {
        isAlive = ThreadLocalRandom.current().nextBoolean();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getChoucroute() {
        return "hello à moi";
    }

    public boolean isTrue() {
        return true;
    }

    public String recussite() {
        this.isAlive = true;
        return "il est vivant";
    }
}
