package com.example.sibiko.api;

public class UtilsApi {
    public static final String BASE_URL_API = "https://sibiko.site/android-alpa/";

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);
    }
}
