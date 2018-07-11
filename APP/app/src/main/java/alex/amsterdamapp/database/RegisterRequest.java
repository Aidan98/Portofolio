package alex.amsterdamapp.database;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidan on 09/05/2017.
 */

public class RegisterRequest extends JsonObjectRequest {
    private static final String REGISTER_REQUEST_URL = "https://pad1617.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String zipcode, String email, String password, Response.Listener<JSONObject> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, null, listener, null);
        params = new HashMap<>();

        params.put("email", email);
        params.put("naam", name);
        params.put("postcode", zipcode);
        params.put("wachtwoord", password);

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }


}

