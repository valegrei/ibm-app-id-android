package pe.edu.uni.valegrei.serverlessapp;

import android.content.Context;
import android.content.SharedPreferences;

public class TokensManager {
    private static final String APP_ID_TOKENS_PREF = "app_id_tokens";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private final Context context;

    public TokensManager(Context context) {
        this.context = context;
    }

    public void saveAccessToken(String accessToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public String getAccessToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public void saveRefreshToken(String refreshToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REFRESH_TOKEN, refreshToken);
        editor.apply();
    }

    public String getRefreshToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(REFRESH_TOKEN, null);
    }

    public void saveUserId(String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, null);
    }

    public void saveUserName(String accessToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, accessToken);
        editor.apply();
    }

    public String getUserName() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_ID_TOKENS_PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_NAME, null);
    }
}
