package pt.ipg.gcyclerclient2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RT on 19/02/2017.
 */

public class User {//implements Parcelable {
    private String email;
    private String encrypted_password;
    private String name;
    private String created_at;
    private String updated_at;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public void setEncrypted_password(String encrypted_password) {
        this.encrypted_password = encrypted_password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    //    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(email);
//        parcel.writeString(encrypted_password);
//        parcel.writeString(name);
//    }
//
//    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };
//
//    private User(Parcel in) {
//        email = in.readString();
//        encrypted_password = in.readString();
//        name = in.readString();
//    }
}
