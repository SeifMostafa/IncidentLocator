<!DOCTYPE html>
<html>
<head>
<style>
div.container {
    width: 100%;
    border: 1px solid gray;
}

header {
    padding: 1em;
    color: white;
    background-color: black;
    clear: left;
    text-align: center;
}
.mytext {
    width: 400px;
	height: 200px;
	
}
footer {
	padding: 1em;
    color: white;
    background-color: black;
    clear: left;
	text-align: center;
    position:fixed;
   bottom:0;
   width:100%;

}
nav {
    float: left;
    max-width: 160px;
    margin: 0;
    padding: 1em;
}

nav ul {
    list-style-type: none;
    padding: 0;
}
   
nav ul a {
    text-decoration: none;
}

article {
    margin-left: 170px;
    border-left: 1px solid gray;
    padding: 1em;
    overflow: hidden;
}

	  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111;
}
.button {
    background-color: black;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
</style>

</head>

<body>
<header>
  
</header>
  

<nav>
  <ul>
   <!-- <li><a href="#">View the nearest address</a></li> -->
    
  </ul>
</nav>


<script
src="http://maps.googleapis.com/maps/api/js">
</script>
    <script>
function initialize() {
  var mapProp = {
    center:new google.maps.LatLng(30.2060555,30.7813875),
    zoom:5,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
}
google.maps.event.addDomListener(window, 'load', initialize);
</script>
   <div id="googleMap" style="width:1000px;height:600px;"></div> 
   
	

   <button class="button" style="float:right;margin-top:-500px;position:relative;left:-100px;">Show Incedints </button>
   <button class="button" style="float:right;margin-top:-400px;position:relative;left:-100px;">   where am i  </button>

<footer>All copy rights are reserved @2016</footer>


</body>

</html>