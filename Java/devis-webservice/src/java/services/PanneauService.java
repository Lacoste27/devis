/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import model.Etat;
import model.Panneau;

/**
 *
 * @author Tsiory
 */
public class PanneauService {

    public List<Panneau> findAll() {
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
            URL url = new URL("http://localhost:5001/panneau/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Type type = new TypeToken<List<Panneau>>() {
            }.getType();
            List<Panneau> result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static List<Panneau> findByIntervalle(int min, int max) {
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
            URL url = new URL("http://localhost:5001/panneau/findInterval/" + min + "/" + max);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Type type = new TypeToken<List<Panneau>>() {
            }.getType();
            List<Panneau> result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public List<Etat> getEtat(int intervale) {
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
            URL url = new URL("http://localhost:5001/panneau/etat/"+intervale);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Type type = new TypeToken<List<Etat>>() {
            }.getType();
            List<Etat> result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static String getMax() {
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
            URL url = new URL("http://localhost:5001/panneau/max");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            return response;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Panneau findById(int id) {
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
            URL url = new URL("http://localhost:5001/panneau/id/" + id);
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
            Type type = new TypeToken<List<Panneau>>() {
            }.getType();
            Panneau result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Panneau> findAllSecond() {
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
            URL url = new URL("http://localhost:8000/api/panneau/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP erro code " + connection.getResponseCode());
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = "";
            String response = "";
            while ((output = reader.readLine()) != null) {
                response += output;
            }
            connection.disconnect();
            Type type = new TypeToken<List<Panneau>>() {
            }.getType();
            List<Panneau> result = new Gson().fromJson(response, type);
            return result;
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException | KeyManagementException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
