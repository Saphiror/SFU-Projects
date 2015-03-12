var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var placesService;
var routeBoxes = new RouteBoxer();
var f=0;

function initialize() {
  //initializes the map
  directionsDisplay = new google.maps.DirectionsRenderer();
  var vancouver = new google.maps.LatLng(49.283949900000000000, -123.120086799999970000);
  var mapOptions = {
    zoom:7,
    center: vancouver
  }
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  directionsDisplay.setMap(map);


}

function calcRoute() {
  //Given the start and end points from the user, calculate and show the route on the map
  var start = document.getElementById('start').value;
  var end = document.getElementById('end').value;
  var request = {
      origin:start,
      destination:end,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
      //This is the partial code for getting hotels every so often on the path
      pointsArray = response.routes[0].overview_path;
      //create boxes to fit to the route
      var covers = routeBoxes.box(pointsArray, 20); 
      for(var i=1; i<covers.length; i=i+1){
        showStuff(map,covers[covers.length-i]);
      }
    }
  });
}

function showStuff(map,position){
     //The function to find the different hotels and other locations of interest along the route
      var request = {
        bounds: position, // in this box containing the route
        types: ['lodging'], //find hotels
        rankBy: google.maps.places.RankBy.PROMINENCE //ranks by rating
      };
         
      placesService = new google.maps.places.PlacesService(map);
      var service = new google.maps.places.PlacesService(map);
      service.nearbySearch(request, callback);
      
}

function callback(results, status) {
  //Function to display the different locations of interest along the route
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    //only shows 20 results from a block
    for (var i = 0; i < 20; i++) {
      createMarker(results[i]);
    }
  }
}

function createMarker(place) {
  //creates a window on the map to display the name of the location  of interest
  var window = new google.maps.InfoWindow();

  //Creates map markers where locations of interest are
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });
  var infoString = place.name + "<br>Rating: " + place.rating;
  google.maps.event.addListener(marker, 'click', function() {
        window.setContent(infoString);
        window.open(map, this);
      });

}

google.maps.event.addDomListener(window, 'load', initialize);


