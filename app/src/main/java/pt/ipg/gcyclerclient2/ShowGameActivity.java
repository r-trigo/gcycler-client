package pt.ipg.gcyclerclient2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowGameActivity extends AppCompatActivity {

    private TextView tv_title;
    private TextView tv_release_date;
    private TextView tv_igdb_url;
    private TextView tv_summary;
    private ImageView iv_cover;
    private Button bu_adicionar_copia;
    private Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_game);

        tv_title = (TextView) findViewById(R.id.textView_title);
        tv_release_date = (TextView) findViewById(R.id.textView_release_date);
        tv_igdb_url = (TextView) findViewById(R.id.textView_igdb_url);
        tv_summary = (TextView) findViewById(R.id.textView_summary);
        iv_cover = (ImageView) findViewById(R.id.imageView_cover);
        bu_adicionar_copia = (Button) findViewById(R.id.button_adicionar_copia);

        game = getIntent().getParcelableExtra("game");
        final Helper myHelper = new Helper();
        String date = myHelper.MillisToDate(Long.parseLong(game.getRelease_date()));

        tv_title.setText(game.getTitle());
        tv_release_date.setText(date);
        tv_igdb_url.setText(game.getIgdb_url());
        tv_summary.setText(game.getSummary());
        iv_cover.setImageBitmap(game.getCover_bitmap());

        bu_adicionar_copia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostNewCopy().execute();
            }
        });
    }

    private class PostNewCopy extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {

            Helper myHelper = new Helper();
            JSONObject jsonObject = myHelper.CopyJSONBuilder(game);
            URL myURL = null;
            try {
                myURL = new URL("http://superpi.sytes.net/copies");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            int response = myHelper.SendThisByPOST(myURL, jsonObject);

            //return response;
            return new Integer(response);
        }

        @Override
        protected void onPostExecute(Integer response) {

            if (response == 200 || response == 201) {
                Toast.makeText(getApplicationContext(), "Cópia adicionada com sucesso", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro, tente mais tarde", Toast.LENGTH_LONG).show();
            }
        }
    }
}
