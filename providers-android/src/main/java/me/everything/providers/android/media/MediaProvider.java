package me.everything.providers.android.media;

import android.content.Context;
import android.provider.MediaStore;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

/**
 * Created by sromku
 */
public class MediaProvider extends AbstractProvider {

    private final static int LIMIT = 250;
    private final static String ORDER_BY_COLUMN = MediaStore.MediaColumns.DATE_MODIFIED;

    public MediaProvider(Context context) {
        super(context);
    }

    public Data<File> getFiles(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(File.uriInternal, File.class);
            default:
                return getContentTableData(File.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        File.class);
        }
    }

    public Data<Image> getImages(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Image.uriInternal, Image.class);
            default:
                return getContentTableData(Image.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Image.class);
        }
    }

    public Data<Video> getVideos(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Video.uriInternal, Video.class);
            default:
                return getContentTableData(Video.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Video.class);
        }
    }

    public Data<Audio> getAudios(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Audio.uriInternal, Audio.class);
            default:
                return getContentTableData(Audio.uriExternal, null, null,
                        ORDER_BY_COLUMN + " DESC" + " LIMIT " + LIMIT,
                        Audio.class);
        }
    }

    public Data<Album> getAlbums(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Album.uriInternal, Album.class);
            default:
                return getContentTableData(Album.uriExternal, Album.class);
        }
    }

    public Data<Artist> getArtists(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Artist.uriInternal, Artist.class);
            default:
                return getContentTableData(Artist.uriExternal, Artist.class);
        }
    }

    public Data<Genre> getGenres(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Genre.uriInternal, Genre.class);
            default:
                return getContentTableData(Genre.uriExternal, Genre.class);
        }
    }

    public Data<Playlist> getPlaylists(Storage storage) {
        switch (storage) {
            case INTERNAL:
                return getContentTableData(Playlist.uriInternal, Playlist.class);
            default:
                return getContentTableData(Playlist.uriExternal, Playlist.class);
        }
    }

    public enum Storage {
        INTERNAL,
        EXTERNAL
    }
}
