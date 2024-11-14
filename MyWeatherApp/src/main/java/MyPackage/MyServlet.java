package MyPackage;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		
		String City_name=request.getParameter("address");
		PrintWriter out=response.getWriter();
//		out.print(City_name);
		
		
		String Encoded_City=URLEncoder.encode(City_name,"UTF-8");
		String API_KEY="f77f66932146648adcefb1d419285014";
		String Weather_url="https://api.openweathermap.org/data/2.5/weather?q="+Encoded_City+"&appid="+API_KEY;
//		out.print(Weather_url);
		
		try {
		
		//making an url object to convert the string to url
		@SuppressWarnings("deprecation")
		URL url=new URL(Weather_url);
		
		
		//making the url connection using HTTPULRConnection object
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		//since the url return data and information stream 
		//making the obejct of InputStream 
		//InputStream receives the api data and information 
		
		InputStream inputstream=connection.getInputStream();
		
		//InputStreamReader class is use to read the InputedStream data and information from API
		InputStreamReader reader=new InputStreamReader(inputstream);
		
		//to store the value in string we use StringBuilder because string is immutable
		StringBuilder responseContent=new StringBuilder();
		
		
		//Scanner is use to scan and take input data and information from InputStreamReader
		Scanner sc=new Scanner(reader);
		
		
		while(sc.hasNext()) {
			
			responseContent.append(sc.nextLine());
		}
		
		//closing the Scanner Object
		sc.close();
//		System.out.println(responseContent);
		out.print(responseContent);
	
		//typecasting/parsing the json result to String from StringBuilder 
		//gson object/instance creation
		
		Gson gson=new Gson();
	
		JsonObject jsonobject=gson.fromJson(responseContent.toString(), JsonObject.class);
		
		System.out.println(jsonobject);
		
		
		
		long TimeStamp=jsonobject.get("dt").getAsLong()*1000;
		String date=new Date(TimeStamp).toString();
		
		
		double TemperatureKelvin=jsonobject.getAsJsonObject("main").get("temp").getAsDouble();
		int TemperatureCelsius= (int) (TemperatureKelvin-273.15);
		
		int humidity=jsonobject.getAsJsonObject("main").get("humidity").getAsInt();
		
		long pressure=jsonobject.getAsJsonObject("main").get("pressure").getAsLong();
		
		double WindSpeed=jsonobject.getAsJsonObject("wind").get("speed").getAsDouble();
		
		String WeatherCondition=jsonobject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		
		
		//storing all the data to the request object (for sending to jsp page)
		request.setAttribute("date", date);
		request.setAttribute("temperature", TemperatureCelsius);
		request.setAttribute("humidity", humidity);
		request.setAttribute("pressure", pressure);
		request.setAttribute("windspeed", WindSpeed);
		request.setAttribute("weather", WeatherCondition);
		request.setAttribute("city", City_name);
		
		
		//closing the connection
		connection.disconnect();
		
		//REQUESTDISPATHCER is use to redirect to the another page without changing the url
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
		}
		catch(Exception e) {
			request.setAttribute("city", City_name);
			e.printStackTrace();
			RequestDispatcher rd=request.getRequestDispatcher("Error.jsp");
			rd.forward(request, response);
		}
		
//		doGet(request, response);
	}

}
