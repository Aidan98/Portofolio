package alex.amsterdamapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by alexa on 12-5-2017.
 */

public class HandleUserSession {


    private SharedPreferences prefs;

    public HandleUserSession(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }


    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }

    public String getEmail() {
        String email = prefs.getString("email", "");
        return email;
    }

    public void setLanguage(int languageId){prefs.edit().putInt("language", languageId).commit();}

    public int getLanguage() {
        int languageId = prefs.getInt("language", 0);
        return languageId;
    }

    public void getLanguageId(){

    }

    public void setId(int id) {
        prefs.edit().putInt("id", id).commit();
    }

    public int getId() {
        int id = prefs.getInt("id", 0);
        return id;
    }


    public void setIsAdmin(boolean admin) {
        prefs.edit().putBoolean("admin", admin).commit();
    }

    public Boolean getIsAdmin() {
        boolean isAdmin = prefs.getBoolean("admin", false);
        return isAdmin;
    }

    public void removeSession() {
        prefs.edit().clear().commit();
    }
}
