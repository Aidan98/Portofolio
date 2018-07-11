package alex.amsterdamapp.database;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexa on 23-5-2017.
 */

public class QuizRequest extends StringRequest {
    private static final String QUIZ_RESULTS_URL = "https://pad1617.000webhostapp.com/Quiz.php";
    private Map<String, String> params;

    public QuizRequest(int gebruiker, int score, Response.Listener<String> listener) {
        super(Request.Method.POST, QUIZ_RESULTS_URL, listener, null);
        params = new HashMap<>();
        params.put("Gebruiker_id", Integer.valueOf(gebruiker).toString());
        params.put("Score", Integer.valueOf(score).toString());
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
