package mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

class POINT {
    double lat,lon;

    POINT(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    double getLat() {
        return lat;
    }

    double getLon() {
        return lon;
    }
}
public class Account {
    String AccountID ; 
    String email ; 
    String name ; 
    String password ; 
    String phoneNumber ; 
    String type  ; 
    int verified  ; 
    
    
    public Account() {
        super();
    }

    public Account(String AccountID, String email, String name, String password, String phoneNumber, String type,
                   int verified) {
        this.AccountID = AccountID;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.verified = verified;
    }

    public void setAccountID(String AccountID) {
        this.AccountID = AccountID;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getVerified() {
        return verified;
    }
    
    public void Signup()  { 
        //connect to Service API SignUp and go to home page 
        
        System.out.println("Signup");        
        throw new AdfException ("Signup" , AdfException.ERROR) ; 
    
    }
    
    public String login() throws IOException, JSONException { 
        //connect to Service A52PI login and go to home page 
            
            JSONObject json = readJsonFromUrl("https://greek-302.herokuapp.com/cards/monster");
            System.out.println(json.toString());
            //throw new AdfException (json.toString() , AdfException.ERROR) ; 
            if(2==2) {
                return "home";
            }
            else {
                throw new AdfException (json.toString() , AdfException.ERROR) ;
            }
    }
    public  Integer markerpoint() {
        //POINT point = new POINT(lat, lon);
        return 10;
        
    }
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();

        try {   
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JSONObject json = new JSONObject(jsonText);
          return json;
        } finally {
          is.close();
        }
      }

    @SuppressWarnings("oracle.jdeveloper.java.nested-assignment")
    private static String readAll(Reader rd) throws IOException {
       StringBuilder sb = new StringBuilder();
       int cp;
       while ((cp = rd.read()) != -1) {
         sb.append((char) cp);
       }
       return sb.toString();
     }
}
