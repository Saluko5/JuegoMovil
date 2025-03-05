package Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Clase que maneja el idioma del juego
public class GamePreferences {
    private static final String PREFS_NAME = "AlienGamePrefs"; // Nombre del archivo de preferencias
    private static final String LANGUAGE = "language";

    private static Preferences prefs;

    private static Preferences getPreferences() {
        if (prefs == null) {
            prefs = Gdx.app.getPreferences(PREFS_NAME);
        }
        return prefs;
    }

    public static void setLanguage(String language) {
        getPreferences().putString(LANGUAGE, language).flush();
    }

    public static String getLanguage() {
        return getPreferences().getString(LANGUAGE, "es");
    }
}