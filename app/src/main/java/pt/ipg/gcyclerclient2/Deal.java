package pt.ipg.gcyclerclient2;

/**
 * Created by RT on 19/02/2017.
 */

public class Deal {

    private String date_accepted;
    private double amount;
    private boolean pending = true;
    private int user_id;
    private int game_id;
    private String created_at;
    private String updated_at;

    public String getDate_accepted() {
        return date_accepted;
    }

    public void setDate_accepted(String date_accepted) {
        this.date_accepted = date_accepted;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
