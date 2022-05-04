/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import models.Batterie;

/**
 *
 * @author Tsiory
 */
@Stateless
public class BatterieImpl implements BatterieRemote {

    public BatterieImpl() {
    }

    @Override
    public List<Batterie> findAll() {
        try {
            TrustManager[] trustAllCerts;

            trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            URL url = new URL("http://localhost:5001/batterie/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            System.out.println("Output from server ....\n");
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Type type = new TypeToken<List<Batterie>>() {
            }.getType();
            List<Batterie> result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(BatterieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Batterie findById(int id) {
        try {
            TrustManager[] trustAllCerts;

            trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            URL url = new URL("http://localhost:5001/batterie/id/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            System.out.println("Output from server ....\n");
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Batterie result = new Gson().fromJson(response, Batterie.class);
            return result;
        } catch (MalformedURLException ex) {
            Logger.getLogger(BatterieImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            Logger.getLogger(BatterieImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
