package pt.ipg.gcyclerclient2;

/**
 * Created by RT on 23/02/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private TextView tv_title;
    private TextView tv_release_date;
    private TextView tv_igdb_url;
    private TextView tv_summary;
    private ImageView iv_cover;
    private Button bu_adicionar_copia;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swiper, container, false);
        /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/

        //next
        ImageView next = (ImageView) rootView.findViewById(R.id.imageView_next);
        next.setImageDrawable(getResources().getDrawable(R.drawable.ic_navigate_next_black_24dp));
        //before
        ImageView before = (ImageView) rootView.findViewById(R.id.imageView_before);
        before.setImageDrawable(getResources().getDrawable(R.drawable.ic_navigate_before_black_24dp));

        if (getArguments().getInt(ARG_SECTION_NUMBER) < 2) {
            before.setVisibility(View.INVISIBLE);
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) > VarSessao.getCopiesForSale() - 1) {
            next.setVisibility(View.INVISIBLE);
        }

        tv_title = (TextView) rootView.findViewById(R.id.textView_title);
        tv_release_date = (TextView) rootView.findViewById(R.id.textView_release_date);
        tv_igdb_url = (TextView) rootView.findViewById(R.id.textView_igdb_url);
        tv_summary = (TextView) rootView.findViewById(R.id.textView_summary);
        iv_cover = (ImageView) rootView.findViewById(R.id.imageView_cover);
        bu_adicionar_copia = (Button) rootView.findViewById(R.id.button_adicionar_copia);

        //// TODO: 24/02/2017 resolver swipe view
        int index = getArguments().getInt(ARG_SECTION_NUMBER)-1;
        ArrayList<Game> games = VarSessao.getTheeseGames();
        Game game = games.get(index);
        final Helper myHelper = new Helper();
        String date = myHelper.MillisToDate(Long.parseLong(game.getRelease_date()));

        tv_title.setText(game.getTitle());
        tv_release_date.setText(date);
        tv_igdb_url.setText(game.getIgdb_url());
        tv_summary.setText(game.getSummary());
        iv_cover.setImageBitmap(game.getCover_bitmap());


        return rootView;
    }
}