var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var  infowindow = new google.maps.InfoWindow();
var placesService;


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
      //pointsArray = response.routes[0].overview_path;
      //for(var i = 0; i < 1; i+1) {
   
        //showStuff(map,pointsArray[i]);
      //}
    }
    showStuff(map,start);
  });

  


}

function showStuff(map,position){
    // Given the user input, display hotels within a 500 mile radius
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode( { 'address': position}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK)
      {   
          var request = {
            location: results[0].geometry.location,
            radius: 500,
            types: ['store']
          };
         
          placesService = new google.maps.places.PlacesService(map);
          var service = new google.maps.places.PlacesService(map);
          service.nearbySearch(request, callback);
          //service.getDetails(request, callback);
      }
    });

}

function callback(results, status) {
  //Function to call the marker creator
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
}

function createMarker(place) {
  //Creates map markers where hotels are
  var placeLoc = place.geometry.location;
  var marker = new google.maps.Marker({
    map: map,
    position: place.geometry.location
  });
  //Display basic details for places using the exisiting places
  var request = { reference: place.reference };
  var service = new google.maps.places.PlacesService(map);
  //gets additional details of places
  service.getDetails(request, function(details, status){
    google.maps.event.addListener(marker, 'click', function(){
      infowindow.setContent("<p>" + details.name + "<br />" + 
        "Address: " + details.formatted_address + "<br />" + 
        "Phone: " + details.formatted_phone_number + "<br />" +
        "Rating: " + details.rating +"/5" + "<br />" + 
        "Website: " + details.website + "</p>");
      infowindow.open(map, this);
    });
  });
}


google.maps.event.addDomListener(window, 'load', initialize);


