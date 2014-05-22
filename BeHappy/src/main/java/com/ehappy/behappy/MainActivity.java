package com.ehappy.behappy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;

/*import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;*/


import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;

public class MainActivity extends Activity {

    // Your Facebook APP ID
    private static String APP_ID = "175695585947163"; // Replace your App ID here
    private Facebook facebook;
    private AsyncFacebookRunner mAsyncRunner;
    String FILENAME = "AndroidSSO_data";
    private SharedPreferences mPrefs;

    private static final String NAMESPACE = "com.service.ServiceImpl";
    private static final String URL =
            "http://192.168.202.124:9000/AndroidWS/wsdl/ServiceImpl.wsdl";
    private static final String SOAP_ACTION = "ServiceImpl";
    private static final String METHOD_NAME = "message";

    public static String[] unHappyFriends;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        facebook = new Facebook(APP_ID);
        mAsyncRunner = new AsyncFacebookRunner(facebook);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void login(View v)
    {
        loginToFacebook();
        callService();
        //Intent myIntent = new Intent(this, FriendsActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
       // startActivity(myIntent);
        //
    }


    public void loginToFacebook() {
        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);

        if (access_token != null) {
            facebook.setAccessToken(access_token);
        }

        if (expires != 0) {
            facebook.setAccessExpires(expires);
        }

        if (!facebook.isSessionValid()) {
            facebook.authorize(this,
                    new String[] { "email", "publish_stream" },
                    new Facebook.DialogListener() {

                        @Override
                        public void onCancel() {
                            // Function to handle cancel event
                        }

                        @Override
                        public void onComplete(Bundle values) {
                            // Function to handle complete event
                            // Edit Preferences and update facebook acess_token
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString("access_token",
                                    facebook.getAccessToken());
                            editor.putLong("access_expires",
                                    facebook.getAccessExpires());
                            editor.commit();
                        }

                        @Override
                        public void onError(DialogError error) {
                            // Function to handle error

                        }

                        @Override
                        public void onFacebookError(FacebookError fberror) {
                            // Function to handle Facebook errors

                        }

                    });
        }
    }

    /*public void callService()
    {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
            //ACTV.setHint("Received :" + resultsRequestSOAP.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    static public String[] convertToStringArray(Object elm) {
        ArrayList<String> arr = new ArrayList<String>();

        for (int i = 0; i < Array.getLength(elm); ++i)
            arr.add((String) Array.get(elm, i));

        return (String[]) arr.toArray(); // this can be cast safely to an array of the type of elm
    }
    public void callService()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        XMLRPCClient sock_common = new XMLRPCClient("http://scripts.incutio.com/xmlrpc/simpleserver.php");
        try {
            //String result = (String) sock_common.call("test.getTime");
            Integer result = (Integer)(sock_common.call("test.add", 6, 13));
            //String result = (String) sock_common.call("test.addArray", new int[]{5,9,11});

            Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show();
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        /*String username = "admin";
            String pwd = "admin";
            String dbname = "behappydb";
        String server_add = "http://openerp.ingenuity.com.my";
        //OpenErpConnect sock_common = null;
        //sock_common = OpenErpConnect.connect(server_add, 8069, dbname, username, pwd);

        //XMLRPCClient sock_common = new XMLRPCClient("http://openerp.ingenuity.com.my");
        XMLRPCClient client = new XMLRPCClient("http://web-25681b10-b23b-434d-91f1-22c20e7636d5.runnable.com");
        String[] x = {"ahmad", "ali"};
        try {
            String res = (String) client.call("say_hello", x);
            int z = 0;
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }
        // String[] params = new String[]{dbname,username,pwd};
        //sock_common.call("login", params);
        Object uid = null;
        *//*try {
            uid = sock_common.call("login", dbname, username, pwd);
            String userID = String.valueOf(uid);
            Object o = sock_common.call("check_service");
            unHappyFriends = convertToStringArray(o);
        } catch (XMLRPCException e) {
            e.printStackTrace();
        }*//*

        //Intent myIntent = new Intent(this, FriendsActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        //XMLRPCClient sock = new XMLRPCClient("http://openerp.ingenuity.com.my/xmlrpc/object");



        //Object[] obj = new Object[]{dbname, uID, pwd, "behappy.service", log};
        //sock.callEx("check_server", obj);
        //startActivity(myIntent);
        //Toast.makeText(null, "ok", Toast.LENGTH_SHORT).show();
        //try {
        //Object uID = sock_common.call("login", dbname, username, pwd);

        //Dictionary<String, String> dic = new Dic

            *//*XMLRPCClient sock = new XMLRPCClient("http://openerp.ingenuity.com.my:8069/xmlrpc/object");
            Object[] obj = new Object[]{dbname, uID, pwd, "behappy.service", log};
            sock.callEx("create", obj);*//*

        *//*} catch (XMLRPCException e) {
            System.out.println(e.getMessage());
        }*/

    }
    
}
