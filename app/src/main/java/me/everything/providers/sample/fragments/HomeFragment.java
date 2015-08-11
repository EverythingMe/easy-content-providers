package me.everything.providers.sample.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import me.everything.providers.sample.R;
import me.everything.providers.sample.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static final Option[] options = new Option[] {
            new Option("Contacts", null),
            new Option("Contacts", ContactsFragment.class),
            new Option("Call Logs", null),
            new Option("Calls", CallsFragment.class),
            new Option("Telephony", null),
            new Option("SMS(es)", SmsesFragment.class),
            new Option("MMS(es)", MmsesFragment.class),
            new Option("Conversations", ConversationsFragment.class),
            new Option("Threads", ThreadsFragment.class),
            new Option("Carriers", CarriersFragment.class),
            new Option("Calendars", null),
            new Option("Calendars, Events, Instances, Reminders, Attendees", CalendarsFragment.class),
            new Option("Browser", null),
            new Option("Bookmarks", BookmarksFragment.class),
            new Option("Searches", SearchesFragment.class),
            new Option("Dictionary", null),
            new Option("Words", WordsFragment.class),
            new Option("Media External", null),
            new Option("Files", FilesFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Images", ImagesFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Videos", VideosFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Audios", AudiosFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Albums", AlbumsFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Artists", ArtistsFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Genres", GenresFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Playlists", PlaylistsFragment.class, MediaFragment.MEDIA_EXTERNAL),
            new Option("Media Internal", null),
            new Option("Files", FilesFragment.class),
            new Option("Images", ImagesFragment.class),
            new Option("Videos", VideosFragment.class),
            new Option("Audios", AudiosFragment.class),
            new Option("Albums", AlbumsFragment.class),
            new Option("Artists", ArtistsFragment.class),
            new Option("Genres", GenresFragment.class),
            new Option("Playlists", PlaylistsFragment.class),
            new Option("Custom Made", null),
            new Option("Posts", CustomPostsFragment.class),
    };

    private final static class Option {
        final String name;
        final Class<? extends Fragment> fragment;
        int param;

        Option(String name, Class<? extends Fragment> fragment) {
            this.name = name;
            this.fragment = fragment;
        }

        Option(String name, Class<? extends Fragment> fragment, int param) {
            this(name, fragment);
            this.param = param;
        }
    }

    private class OnMenuItemSelected implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Option option = options[position];
            Class<? extends Fragment> fragment = option.fragment;
            setFragment(fragment, option.param);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        setToolbarTitle("Home");
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(new MenuAdapter());
        listView.setOnItemClickListener(new OnMenuItemSelected());
        return view;
    }

    private class MenuAdapter extends BaseAdapter {

        private final static int TYPE_HEADER = 0;
        private final static int TYPE_ROW = 1;

        private final int COLOR_HEADER = Color.parseColor("#000000");
        private final int COLOR_ROW = Color.parseColor("#222222");

        private final int SIZE_HEADER = 18; //dp
        private final int SIZE_ROW = 16; //dp

        private final int mPaddingSide;
        private final AbsListView.LayoutParams mLayoutParamsHeader;
        private final AbsListView.LayoutParams mLayoutParamsRow;

        public MenuAdapter() {
            Resources resources = getResources();
            mPaddingSide = resources.getDimensionPixelSize(R.dimen.menu_list_padding);
            mLayoutParamsHeader = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.menu_header_height));
            mLayoutParamsRow = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, resources.getDimensionPixelSize(R.dimen.menu_row_height));
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return options[position].fragment == null ?
                TYPE_HEADER : TYPE_ROW;
        }

        @Override
        public boolean isEnabled(int position) {
            return getItemViewType(position) == TYPE_ROW;
        }

        @Override
        public int getCount() {
            return options.length;
        }

        @Override
        public Object getItem(int position) {
            return options[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Option option = options[position];
            TextView view;
            if (convertView == null) {
                int type = option.fragment == null ? TYPE_HEADER : TYPE_ROW;
                view = createTextView(type);
            } else {
                view = (TextView) convertView;
            }
            view.setText(option.name);
            return view;
        }

        private TextView createTextView(int type) {
            TextView textView = new TextView(getApplicationContext());
            switch (type) {
                case TYPE_HEADER:
                    textView.setLayoutParams(mLayoutParamsHeader);
                    textView.setTextColor(COLOR_HEADER);
                    textView.setTextSize(SIZE_HEADER);
                    textView.setGravity(Gravity.BOTTOM | Gravity.LEFT);
                    textView.setTypeface(Typeface.DEFAULT_BOLD);
                    break;
                case TYPE_ROW:
                    textView.setLayoutParams(mLayoutParamsRow);
                    textView.setTextColor(COLOR_ROW);
                    textView.setTextSize(SIZE_ROW);
                    textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                    break;
            }

            textView.setPadding(mPaddingSide, 0, mPaddingSide, 0);
            return textView;
        }
    }

}
