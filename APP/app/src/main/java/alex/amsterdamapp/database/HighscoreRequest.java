package alex.amsterdamapp.database;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


/**
 * Weergeeft de \top 3 met de meeste score
 */

public class HighscoreRequest extends StringRequest {
    private static final String URL_HIGHSCORE = "https://pad1617.000webhostapp.com/QuizHighscore.php";


    public HighscoreRequest(Response.Listener<String> listener) {
        super(Method.POST, URL_HIGHSCORE, listener, null);
    }
}
