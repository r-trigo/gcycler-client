package pt.ipg.gcyclerclient2;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RT on 21/02/2017.
 */

public class GameAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<Game> resultList = new ArrayList();

    public GameAutoCompleteAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Game getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getTitle());

        ((ImageView) convertView.findViewById(R.id.imageView1)).setImageBitmap(getItem(position).getCover_bitmap());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Game> games = findGames(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = games;
                    filterResults.count = games.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<Game>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    /**
     * Returns a search result for the given book title.
     */
    private List<Game> findGames(String gameTitle) {
        Helper myHelper = new Helper();

        URL myURL = null;

        try {
            myURL = new URL("https://igdbcom-internet-game-database-v1.p.mashape.com/games/?fields=name%2Cfirst_release_date%2Ccover%2Cgenres%2Csummary%2Curl&limit=3&offset=0&order=release_dates.date%3Adesc&search=" + gameTitle);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String imjson = myHelper.GetJSONRequest(myURL);
        Game[] games = myHelper.GamesJSONParser(imjson);

        List<Game> gamesList = new ArrayList<>();
        for (int i = 0; i < games.length; i++) {
            gamesList.add(games[i]);
        }

        return gamesList;
    }
}