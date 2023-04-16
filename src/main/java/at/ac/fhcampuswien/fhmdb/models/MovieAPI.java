package at.ac.fhcampuswien.fhmdb.models;

import org.json.JSONArray;
import okhttp3.*;
import java.io.IOException;

public class MovieAPI {
    private static final String API_URL = "https://prog2.fh-campuswien.ac.at/movies?";

    public static HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();

    public MovieAPI() {
    }

    public static JSONArray getMovies(String url) throws IOException {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("User-Agent", "http.agent")
                    .build();
            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("Error: " + response.code());
            }

            String responseString = response.body().string();
            JSONArray moviesJSONArray = new JSONArray(responseString);


            return moviesJSONArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addURLParameter(String parameter, String value) {
        urlBuilder.addQueryParameter(parameter, value);
    }

    public static void deleteURLParameter(String parameter) {
        urlBuilder.removeAllQueryParameters(parameter);
    }


    public static HttpUrl.Builder getUrlBuilder() {
        return urlBuilder;
    }

    public static String getApiUrl()
    {
        return API_URL;
    }
}

