package utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Мадияр on 25.08.2017.
 */

public class NetworkUtils {
    final static String BASE_URL ="https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.championat.com%2Frss%2Fnews%2Fcybersport%2F";
    final static String BASE_GAMES_URL =
            "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=C2206A4558AD929913C2DFA15E982A79";
    final static String BASE_LEAGUES_URL =
            "https://api.opendota.com/api/leagues";
    final static String PARAM_QUERY = "format";
    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */

    /**
     * Builds the URL used to query GitHub.
     */
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL+"/").buildUpon()
                .appendQueryParameter(PARAM_QUERY, "json")
                .build();
        Log.e("fdojsdoif", builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildLeaguesUrl() {
        Uri builtUri = Uri.parse(BASE_LEAGUES_URL+"/").buildUpon()
                .appendQueryParameter(PARAM_QUERY, "json")
                .build();
        Log.e("fdojsdoif", builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static URL buildGamesUrl() {
        Uri builtUri = Uri.parse(BASE_GAMES_URL+"/").buildUpon()
                .appendQueryParameter(PARAM_QUERY, "json")
                .build();
        Log.e("fdojsdoif", builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
