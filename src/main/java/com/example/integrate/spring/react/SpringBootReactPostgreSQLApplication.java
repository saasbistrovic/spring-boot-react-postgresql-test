package com.example.integrate.spring.react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import org.json.JSONException;

@SpringBootApplication
public class SpringBootReactPostgreSQLApplication {

	public static void main(String[] args) {
        try {
        File fileOne = new File("eur_conversion");
	File fileOne1 = new File("target/classes/static/eur_conversion");
	String line="";
        URL oracle = new URL("https://api.hnb.hr/tecajn/v2?valuta=EUR");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                yc.getInputStream()));
        String token1="";
        String inputLine="";
        String jsondata="";
        while ((inputLine = in.readLine()) != null) 
            jsondata=jsondata+inputLine;
        jsondata = jsondata.replace("[","");
        try {
    	String data = jsondata;
    	JSONObject jsonObject = new JSONObject(data);
        token1 = jsonObject.getString("srednji_tecaj");
	} catch (JSONException e) {
    	e.printStackTrace();
	}
        token1 = token1.replace("\r\n","");
	token1 = token1.replace(",",".");
        PrintWriter writer = new PrintWriter(fileOne);
        writer.println(token1);
        writer.close();
        PrintWriter writer1 = new PrintWriter(fileOne1);
        writer1.println(token1);
        writer1.close();
        in.close();
        } catch (MalformedURLException e) {
    	//e.printStackTrace();
  	} catch (IOException e) {
    	//e.printStackTrace();
  	}
		SpringApplication.run(SpringBootReactPostgreSQLApplication.class, args);
	}

}
