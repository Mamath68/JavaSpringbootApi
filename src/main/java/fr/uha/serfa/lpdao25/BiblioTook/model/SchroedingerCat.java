package fr.uha.serfa.lpdao25.BiblioTook.model;


import java.util.concurrent.ThreadLocalRandom;

/**
 * une classe naive pour expliciter l'usage de Jackson lors de la conversion en json dans le controller. <br/>
 * le chat est soit vivant, soit mort à l'instanciation
 */
public class SchroedingerCat {

    /**
     * l'attribut est public → isAlive sera visible dans le json
     */
    public boolean isAlive = false;
    private String name = "pauvre chat";

    public SchroedingerCat(String name) {
        isAlive = ThreadLocalRandom.current().nextBoolean();
        this.name = name;
    }

    /**
     * La méthode débute par get → le nom sera visible dans le json
     *
     * @return this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * La méthode débute par get, choucroute aura une valeur dans le json
     *
     * @return String
     */
    public String getChoucroute() {
        return "hello à moi";
    }

    /**
     * La méthode débute par is, true aura une valeur dans le json
     *
     * @return boolean
     */
    public boolean isTrue() {
        return true;
    }

    /**
     * Recussite n’apparait pas dans le json car ne débute pas par is ou get
     *
     * @return String
     */
    public String recussite() {
        this.isAlive = true;
        return "il est vivant";
    }
}
