package fr.hcube_conseil.hcube_conseil.modele;

public class User {

    //éléments de la classe user

    private int id;
    private String pseudo;
    private String email;
    private String password;
    private int statut;

    public User(int id, String pseudo, String email, String password, int statut) {
        this.id = id;
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
