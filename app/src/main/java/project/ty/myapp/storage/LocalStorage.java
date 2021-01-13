package project.ty.myapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import project.ty.myapp.model.User;

import static android.content.Context.MODE_PRIVATE;

public class LocalStorage {
    private static LocalStorage instance;
    private static SharedPreferences preferences;

    public static void init(Context context) {
        if (instance == null) {
            instance = new LocalStorage(context);
        }
    }

    public static LocalStorage getInstance() {
        return instance;
    }

    public static final String PREFS_NAME = "final_project";

    public static final String USER_KEY = "USER_KEY";

    public static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";


    private final Gson gson;

    private LocalStorage(Context context) {
        this.gson = new Gson();
        preferences = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    public void saveUser(User user) {
        preferences.edit().putString(USER_KEY, gson.toJson(user)).apply();
    }

    public User getUser() {
        return gson.fromJson(preferences.getString(USER_KEY, null), User.class);
    }

    public static void clear() {
        preferences.edit().clear().apply();
    }

    public void saveToken(String accessToken) {
        preferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply();
    }

    public String getToken() {
        return preferences.getString(ACCESS_TOKEN_KEY, null);
    }
}
