package myPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		// Initializing a variable for the API key value :
		String apiKey="02f93275b3870fd9d046034888ccf694";
		
		// Initializing a variable for to store the city name that we are searching in our page.
		// getting the value from the "name" argument in the <input> tag which user/client sends as request,so getting that value from the request's parameter.
		String city= request.getParameter("city");
		
		// API Integration ----> Starts here....
		// Creating a API url :
		String apiUrl="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
		
		//Making above string a proper URL so, for that importing URL class :
		URL url = new URL(apiUrl);
		
		//To establish a connection :
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		// API Integration ends here.....
		
		// In order to read the data that we're getting :
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		
		// To store the data :
		// We're using String builder here because Strings are immutable in nature and for this we'll get different different values
		StringBuilder responseContent = new StringBuilder();
		
		// To capture data from the reader, create a scanner class :
		Scanner sc= new Scanner(reader);
		while(sc.hasNext()) {
			responseContent.append(sc.nextLine());
		}
		sc.close();
//		System.out.println(responseContent);
		
		// Parsing data into JSON String into an JSON object by importing a external Gson JAR file :
		Gson gson= new Gson();
		JsonObject jsonObject = gson.fromJson(responseContent.toString(),JsonObject.class);
//		System.out.println(jsonObject);
		
		// Date and time formatting :
		long dateTimeStamp = jsonObject.get("dt").getAsLong()*1000;
		// Create object of date and then supply the argument and converting it into String format :
		String date= new Date(dateTimeStamp).toString();
//		System.out.println(date);
		
		// To get Temperature :
		double tempKelvin= jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		// Converting kelvin temperature into the degree celsius :
		int tempCelsius = (int) (tempKelvin-273.15);
//		System.out.println(tempCelsius);
		
		// To get humidity :
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
//		System.out.println(humidity);
		
		// To get wind speed :
		double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
//		System.out.println(windSpeed);
		
		// To get visibility :
		int visibility = jsonObject.get("visibility").getAsInt()/1000;
		
		// Feels like :
		double feelsLike= jsonObject.getAsJsonObject("main").get("feels_like").getAsDouble();
		int feelsLikeCelsius= (int) (feelsLike-273.15);
		
		//To include pressure :
		int pressure= jsonObject.getAsJsonObject("main").get("pressure").getAsInt();
		
		// To get weather condition :
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
//		System.out.println(weatherCondition);
		
		
		
		// We need to set attribute to the request that we've received from the client/user :
		request.setAttribute("date",date);
		request.setAttribute("city", city);
		request.setAttribute("visibility",visibility);
		request.setAttribute("feelsLike", feelsLikeCelsius);
		request.setAttribute("pressure", pressure);
		request.setAttribute("temperature",tempCelsius);
		request.setAttribute("weatherCondition", weatherCondition);
		request.setAttribute("humidity", humidity);
		request.setAttribute("windSpeed",windSpeed);
		request.setAttribute("weatherData",responseContent.toString());
		
		connection.disconnect();
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		
	}

}
