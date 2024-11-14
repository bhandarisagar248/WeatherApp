<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 Error</title>
<style>
body{
   background-image: url(error.svg);
    background-size: 78% 129%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 77vh;
    gap: 14vh;
}

.oops{
    margin-top: 31px
}

#city_banner img{
    position: relative;
    right: -21.5vw;
    bottom: -1vh;
}

#city_name{

    font-size: 61px;
    position: relative;
    right: -22.5vw;
    color:#323232;
    }
    
#return_home{

    position: relative;
    right: -20.5vw;
    bottom: 8vh;
    }
    
#return_home{
border: 1px solid white;
    box-shadow: 0px 12px 33px -3px #276873;
    background: linear-gradient(to bottom, #6aacc4 5%, #45aaba 100%);
    background-color: #6aacc4;
    border-radius: 9px;
    display: inline-block;
    cursor: pointer;
    color: #fffdfd;
    font-family: Arial;
    font-size: 20px;
    font-weight: bold;
    padding: 13px 32px;
    text-decoration: none;
    text-shadow: 0px 1px 0px #3d768a;
}

#return_home a{
    text-decoration: none;
    color: white;
}


#return_home:hover {
	background:linear-gradient(to bottom, #45aaba 5%, #6aacc4 100%);
	background-color:#45aaba;
}
  





</style>
</head>

<%
String city=(String) request.getAttribute("city");
%>
<body>

<h1 class="oops"><img id="oops" src="oops.svg" alt="OOPS!"></h1>

<span id="city_banner">
<h1 id="city_name">${city}</h1>
<img src="not_available.svg" alt="Is Not Available">
</span>

<button id="return_home" class="myButton"><a href="index.html">Return Home</a></button>

</body>
</html>