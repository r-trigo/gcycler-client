package pt.ipg.gcyclerclient2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CopiesForSaleActivity extends AppCompatActivity {

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copies_for_sale);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pd = new ProgressDialog(this);
        pd.show();
        new FetchCopiesForSale().execute();
    }

    private class FetchCopiesForSale extends AsyncTask<Void, Void, ArrayList> {

        @Override
        protected ArrayList doInBackground(Void... params) {
            ArrayList ids = new ArrayList();
            String url = "http://superpi.sytes.net/copies.json";
            try {
                URL myURL = new URL(url);
                Helper myHelper = new Helper();
                String imjson = myHelper.GetJSONRequest(myURL);
                ids = myHelper.IDsForSale(imjson);
                VarSessao.setCopiesForSale(ids.size());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return ids;
        }

        @Override
        protected void onPostExecute(ArrayList ids) {
//            for (int i = 0; i < ids.size(); i++) {
//                try {
//                    URL myURL = new URL("https://igdbcom-internet-game-database-v1.p.mashape.com/games/" + ids.get(i) + "?fields=*");
//                    new FetchGameData().execute(myURL);
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//            }
            new FetchGameData().execute(ids);
        }
    }

    private class FetchGameData extends AsyncTask<ArrayList, Void, Game[]> {

        @Override
        protected Game[] doInBackground(ArrayList... params) {

            Helper myHelper = new Helper();
            Game[] games = new Game[50];

            for (int i = 0; i < params[0].size(); i++) {
                try {
                    URL myURL = new URL("https://igdbcom-internet-game-database-v1.p.mashape.com/games/" + params[0].get(i) + "?fields=*");
                    String imjson = myHelper.GetJSONRequest(myURL);
                    games = myHelper.GamesJSONParser(imjson);

                    ArrayList<Game> gal;
                    if (VarSessao.getTheeseGames() != null) {
                        gal = VarSessao.getTheeseGames();
                    } else {
                        gal = new ArrayList<>();
                        VarSessao.setTheeseGames(gal);
                    }
                    gal.add(games[0]);
                    VarSessao.setTheeseGames(gal);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }

            return games;
        }

        @Override
        protected void onPostExecute(Game[] games) {

            pd.dismiss();
            Intent intent = new Intent(CopiesForSaleActivity.this, SwiperActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}