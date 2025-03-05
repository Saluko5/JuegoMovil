package Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class LanguageManager {

    // Bundle que almacena las traducciones cargadas para el idioma actual.
    private static I18NBundle myBundle;

    // Preferencias para almacenar la configuración del idioma.
    private static final Preferences prefs = Gdx.app.getPreferences("AlienGamePrefs");

    // Código del idioma actual (para evitar recargas innecesarias).
    private static String currentLangCode = "es";

    // Carga el idioma actual desde las preferencias del usuario.
    // Si el idioma no está configurado, usa español ("es") por defecto.
    public static void loadLanguage() {
        String langCode = prefs.getString("language", "es");
        Locale locale = Locale.forLanguageTag(langCode); // Maneja mejor códigos como "en-US"

        try {
            myBundle = I18NBundle.createBundle(Gdx.files.internal("languages/" + langCode), locale);
            currentLangCode = langCode; // Actualizar idioma actual
        } catch (Exception e) {
            System.err.println("Error al cargar el idioma: " + langCode);
            e.printStackTrace();
        }
    }

    // Sirve para obtener la traduccion de una clave
    public static String get(String key) {
        if (myBundle == null) {
            System.err.println("Intento de acceso a un bundle de idioma no cargado.");
            return "[LANGUAGE NOT LOADED]";
        }
        try {
            return myBundle.get(key);
        } catch (Exception e) {
            return "[MISSING: " + key + "]";
        }
    }

    // Sirve para conseguir el idioma actual
    public static void setLanguage(String langCode) {
        if (!currentLangCode.equals(langCode)) { // Evita recargar si ya está en ese idioma
            prefs.putString("language", langCode);
            prefs.flush(); // Guardar cambios
            loadLanguage(); // Recargar idioma
        }
    }
}
