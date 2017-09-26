package pt.ipg.gcyclerclient2;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class SearchGameActivity extends AppCompatActivity {

    private static final int THRESHOLD = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_game);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DelayAutoCompleteTextView gameTitle = (DelayAutoCompleteTextView) findViewById(R.id.et_book_title);
        gameTitle.setThreshold(THRESHOLD);
        gameTitle.setAdapter(new GameAutoCompleteAdapter(this)); // 'this' is Activity instance
        gameTitle.setLoadingIndicator((android.widget.ProgressBar) findViewById(R.id.pb_loading_indicator));
        gameTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Game game = (Game) adapterView.getItemAtPosition(position);
                gameTitle.setText(game.getTitle());
                Toast.makeText(getApplicationContext(),game.getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SearchGameActivity.this, ShowGameActivity.class);
                intent.putExtra("game", game);
                startActivity(intent);
            }
        });

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
