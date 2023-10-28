package com.example.MyMemoryTranslation.service;

import com.example.MyMemoryTranslation.config.TranslatorConfig;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TranslatorService {
    private final OkHttpClient httpClient;
    private final TranslatorConfig translatorConfig;

    @Autowired
    public TranslatorService(TranslatorConfig translatorConfig) {
        this.httpClient = new OkHttpClient();
        this.translatorConfig = translatorConfig;
    }

    public String translateText(String text, String targetLanguage) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(translatorConfig.getApiBaseUrl() + "/get").newBuilder();
        urlBuilder.addQueryParameter("q", text);
        urlBuilder.addQueryParameter("langpair", targetLanguage);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        Response response = httpClient.newCall(request).execute();
        System.out.println(request);
        System.out.println(response);
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            return responseBody;
        } else {
            return "Translation failed.";
        }
    }
}
