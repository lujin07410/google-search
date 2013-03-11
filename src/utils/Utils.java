package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import main.Element;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utils {
	public static String preString = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

	public static String googleSearch(String keyword) throws IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(preString + keyword);
		HttpResponse response = httpclient.execute(httpget);

		HttpEntity entity = response.getEntity();

		BufferedReader reader = null;
		String contents = new String();
		if (null != entity) {
			reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			contents = reader.readLine();
		}

		httpget.abort();
		if (reader != null)
			reader.close();
		httpclient.getConnectionManager().shutdown();

		return contents;
	}

	public static void jsonParsing(String contents, ArrayList<Element> all) {
		JSONObject json = new JSONObject(contents);
		JSONObject json1 = json.getJSONObject("responseData");
		JSONArray json2 = json1.getJSONArray("results");
		int len = json2.length();
		for (int i = 0; i < len; i++) {
			JSONObject jo = json2.getJSONObject(i);
			String title = (String) jo.get("title");
			String url = (String) jo.get("url");
			String description = (String) jo.get("content");

			title = title.replaceAll("<b>", "");
			title = title.replaceAll("</b>", "");
			url = url.replaceAll("<b>", "");
			url = url.replaceAll("</b>", "");
			description = description.replaceAll("<b>", "");
			description = description.replaceAll("</b>", "");

			Element elem = new Element(title, url, description);
			all.add(elem);
		}
	}

	public static String outPut(ArrayList<Element> all) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < all.size(); i++) {
			buffer.append("Title: " + all.get(i).getTitle() + "\r\n");
			buffer.append("URL: " + all.get(i).getUrl() + "\r\n");
			buffer.append("Description: " + all.get(i).getDescription()
					+ "\r\n\r\n");
		}

		System.out.println(buffer.toString());
		return buffer.toString();
	}
}
