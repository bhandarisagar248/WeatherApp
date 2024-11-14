<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather App</title>
<link rel="stylesheet" href="index.css">
</head>
<body>

<%
String weather=(String) request.getAttribute("weather");
String date=(String) request.getAttribute("date");
int temperature=(int)request.getAttribute("temperature");
int humidity=(int)request.getAttribute("humidity");
long pressure=(long) request.getAttribute("pressure");
double windspeed=(double) request.getAttribute("windspeed");
String city=(String) request.getAttribute("city");%>


<div id="parent">

<form method="POST" action="MyServlet" id="search_bar">
<input type="text" id="address" name="address" value="<%=city %>" placeholder="Enter City Name">
<button type="submit" id="search"><img src="map.png" alt="search" id="search_logo"></button>
<input type="hidden" id="weather_value" value="${weather}">
</form>

<div id="weather">

<div id="weather_logo">
<img id="weather_Condition" alt="Weather_Logo" src="">

</div>
<div id="temperature_logo">
<h2 id="temp">${temperature}  <sup>o</sup>C</h2>

<h2 id="condition">${weather}</h2>

</div>

</div>
<div id="heading"><h1 class="city_name">${city}</h1></div>
<div id="date">${date}</div>

<hr>
<div id="base">


<div id="humidity"><img src="Humidity.png" alt="Humidity" height="70px">
<div class="humidity_percentage" id="humidity_percentage">
<h3>Humidity</h3>
<h2>${humidity}%</h2>
</div>

</div>

<div id="wind_speed">

<img src="wind.png" alt="Wind Speed" height="70px">
<div id="wind_percentage" class="humidity_percentage">
<h3>Wind Speed</h3>
<h2>${windspeed} km/h</h2>
</div>
</div>

</div>


</div>


<script>

var weather=document.getElementById("weather_value").value;
console.log(weather);

switch(weather){

case 'Clouds':
	document.getElementById("weather_Condition").src="Cloud.png";
	break;

case 'Clear':
	document.getElementById("weather_Condition").src="sun.png";
	break;

case 'Rain':
	document.getElementById("weather_Condition").src="rain.png";
	break;

case 'Mist':
	document.getElementById("weather_Condition").src="mist.png";
	break;

case 'Snow':
	document.getElementById("weather_Condition").src="snow.png";
	break;
	
case 'Haze':
	document.getElementById("weather_Condition").src="haze.png";
	break;

	
case 'Smoke':
	document.getElementById("weather_Condition").src="smoke.png";
	break;
	
case 'Fog':
	document.getElementById("weather_Condition").src="foggy.png";
	break;
	
}

</script>

</body>
</html>