package pt.ipg.gcyclerclient2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by RT on 20/02/2017.
 */

public class Helper {

    //HTTP get request
    public String GetJSONRequest(URL myURL) {
        //array due to async task requirements
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();

        try {
            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestProperty("X-Mashape-Key", "V3vLoLS5Ezmsh3xAcO8AmON1kKV2p1mzirXjsnpePBRS9gg02M");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            InputStream stream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String jsonString = buffer.toString();
        return jsonString;
    }

    public Game[] GamesJSONParser(String imjson) {

        Game[] games = new Game[5];

        try {
            JSONArray jsonArray = new JSONArray(imjson);
            JSONObject jsonObject = new JSONObject();
            games = new Game[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                Game g = new Game();
                jsonObject = jsonArray.optJSONObject(i);
                g.setIgdb_id(jsonObject.optInt("id"));
                g.setTitle(jsonObject.optString("name"));
                g.setIgdb_url(jsonObject.optString("url"));
                g.setSummary(jsonObject.optString("summary"));
                g.setGenres(jsonObject.optString(""));

                JSONArray genres = jsonObject.optJSONArray("genres");
                for (int j = 0; j < genres.length(); j++) {
                    int genre_code = genres.optInt(genres.optInt(i));
                    g.setGenres(g.getGenres() + genre_code);
                    //// TODO: 22/02/2017 genre finder array
                }

                g.setRelease_date(jsonObject.optString("first_release_date"));
                JSONObject cover = jsonObject.optJSONObject("cover");
                g.setCover_url(cover.optString("url"));
                g.setCover_bitmap(LoadImageFromWeb(g.getCover_url()));
                games[i] = g;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return games;
    }

    public String GenreFinder(int genre_code) {

//        URL myURL = null;
//        URL
//        GetJSONRequest()

        return null;
    }

    public String MillisToDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return sdf.format(cal.getTime());
    }


    public Bitmap LoadImageFromWeb(String url) {
        url = "https:" + url;
        Bitmap bmp = null;
        URL[] myURL = new URL[1];
        try {
            myURL[0] = new URL(url);
            bmp = BitmapFactory.decodeStream((InputStream) myURL[0].getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    //Sending a POST request
    public int SendThisByPOST(URL myURL, JSONObject params) {
        HttpURLConnection conn = null;
        DataOutputStream output;
        int response = 0;

        try {

            conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            output = new DataOutputStream(conn.getOutputStream());

            //Overperformance choices
            //conn.setFixedLengthStreamingMode(output.size());
            //conn.setChunkedStreamingMode(0);

            byte[] outputInBytes = params.toString().getBytes("UTF-8");
            output.write(outputInBytes);
            output.flush();
            output.close();

            //Get server response
            response = conn.getResponseCode();

            //DEBUG -  Get the server response
            String reply;
            InputStream in = conn.getErrorStream();
            StringBuffer sb = new StringBuffer();
            int chr;
            if (in != null) {
                while ((chr = in.read()) != -1) {
                    sb.append((char) chr);
                }
                reply = sb.toString();
                Log.d("reply", reply);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response;
    }

    public JSONObject CopyJSONBuilder(Game game) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("igdb_id", game.getIgdb_id());
            jsonObject.put("user_id", VarSessao.getId_utilizador());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    //encode base36
    public String encodeBase36(int num) {
        //int num = 586403532;
        String code = "0123456789abcdefghijklmnopqrstuvwxyz";
        String text = "";
        int j = (int) Math.ceil(Math.log(num) / Math.log(code.length()));
        for (int i = 0; i < j; i++) {
            //i goes to log base code.length() of num (using change of base formula)
            text += code.charAt(num % code.length());
            num /= code.length();
        }
        return text;
    }

    //decode base36
    public int decodeBase36(String num36) {
        //String text = "0vn4p9";
        String code = "0123456789abcdefghijklmnopqrstuvwxyz";
        int text = 0;
        int j = num36.length();
        for (int i = 0; i < j; i++) {
            text += code.indexOf(num36.charAt(0)) * Math.pow(code.length(), i);
            num36 = num36.substring(1);
        }

        return text;
    }

    public ArrayList<Integer> IDsForSale (String imjson) {

        ArrayList<Integer> ids = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(imjson);
            JSONObject jsonObject = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.optJSONObject(i);
                ids.add(jsonObject.optInt("igdb_id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ids;
    }
}