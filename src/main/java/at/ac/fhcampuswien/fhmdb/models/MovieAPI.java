package at.ac.fhcampuswien.fhmdb.models;

import okhttp3.*;
import java.io.IOException;

public class MovieAPI {
    private static final String API_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String USER_AGENT_VALUE = "http.agent";
    private OkHttpClient httpClient;

    public MovieAPI() {
        httpClient = new OkHttpClient();
    }

    public String getMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader(USER_AGENT_HEADER, USER_AGENT_VALUE)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String searchMovies(String query, String genre) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        urlBuilder.addQueryParameter("query", query);
        urlBuilder.addQueryParameter("genre", genre);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .addHeader(USER_AGENT_HEADER, USER_AGENT_VALUE)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            return response.body().string();
        }
    }
}

