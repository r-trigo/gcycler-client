package pt.ipg.gcyclerclient2;

import java.util.ArrayList;

/**
 * Created by RT on 23/02/2017.
 */

public abstract class VarSessao {

    private static int id_utilizador;
    private static ArrayList<Game> theeseGames;
    private static int copiesForSale;

    public static int getCopiesForSale() {
        return copiesForSale;
    }

    public static void setCopiesForSale(int copiesForSale) {
        VarSessao.copiesForSale = copiesForSale;
    }

    public static int getId_utilizador() {
        return id_utilizador;
    }

    public static void setId_utilizador(int id_utilizador) {
        VarSessao.id_utilizador = id_utilizador;
    }

    public static ArrayList<Game> getTheeseGames() {
        return theeseGames;
    }

    public static void setTheeseGames(ArrayList<Game> theeseGames) {
        VarSessao.theeseGames = theeseGames;
    }
}
