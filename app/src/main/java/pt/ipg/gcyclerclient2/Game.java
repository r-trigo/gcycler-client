package pt.ipg.gcyclerclient2;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RT on 20/02/2017.
 */

public class Game implements Parcelable {

    private int igdb_id;
    private String title;
    private String release_date;
    private String genres;
    private String cover_url;
    private Bitmap cover_bitmap;
    private String summary;
    private String igdb_url;
    private String created_at;
    private String updated_at;

    public Game() {
    }

    public int getIgdb_id() {
        return igdb_id;
    }

    public void setIgdb_id(int igdb_id) {
        this.igdb_id = igdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public Bitmap getCover_bitmap() {
        return cover_bitmap;
    }

    public void setCover_bitmap(Bitmap cover_bitmap) {
        this.cover_bitmap = cover_bitmap;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIgdb_url() {
        return igdb_url;
    }

    public void setIgdb_url(String igdb_url) {
        this.igdb_url = igdb_url;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(igdb_id);
        out.writeString(title);
        out.writeString(release_date);
        out.writeString(genres);
        out.writeString(cover_url);
        out.writeString(summary);
        out.writeString(igdb_url);
        out.writeString(created_at);
        out.writeString(updated_at);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    private Game(Parcel in) {
        igdb_id = in.readInt();
        title = in.readString();
        release_date = in.readString();
        genres = in.readString();
        cover_url = in.readString();
        summary = in.readString();
        igdb_url = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

}
