var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var  infowindow = new google.maps.InfoWindow();
var placesService;
var details;
var routeBoxes = new RouteBoxer();
var hotelMarkers = [];
var restaurantMarkers = [];
var gasMarkers = [];
var attractionMarkers = [];
var trip_id = -1;
var placesList = document.getElementById('places');
var saveList = null;
 var i = 0;
 var mapMarkers =[];
 var placeMarkers =[];


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
  var start = document.getElementById("start");
  var end = document.getElementById("end");
  if (start && end) {
  	new google.maps.places.Autocomplete(start);
	new google.maps.places.Autocomplete(end);
  }
  placesService = new google.maps.places.PlacesService(map);
}

function submitTrip(start, end) {
	var csrfmiddlewaretoken = document.getElementsByName('csrfmiddlewaretoken')[0].value;
	if (csrfmiddlewaretoken != null && trip_id == -1) {
		$.post("/submittrip/", { start: start, end: end, csrfmiddlewaretoken: csrfmiddlewaretoken}, function (trip) {
			trip_id = trip;
		});
	}

}

function calcRoute(success, error, start, end) {
  if (typeof(start) == "undefined") {
    //Given the start and end points from the user, calculate and show the route on the map
    start = document.getElementById('start').value;
    end = document.getElementById('end').value;
  }
  var request = {
      origin:start,
      destination:end,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      submitTrip(start, end);
      directionsDisplay.setDirections(response);

      var pointsArray = response.routes[0].overview_path;
      //create boxes to fit to the route
      var covers = routeBoxes.box(pointsArray, 20); 
      for(var i=1; i<covers.length; i=i+1){
        (function(i) {
            setTimeout(function() {
              showStuff(map,covers[covers.length-i]);
            }, i * 100);
          })(i);
      }
      console.log("done");
      if (success != undefined)
        success();
    } else {
      if (error != undefined)
        error();
    }
  });

}

// Shows any markers currently in the array.
function showMarkers() {
  setAllMap(map);
}

//This function will be used to reroute using waypoints with the selected list of items in "savedList"
//Takes the address from the "savedList" and remaps it on the map 
function reRoute(){
    var start = document.getElementById('start').value;
    var end = document.getElementById('end').value;
    var waypts = []; 
    var wayptsLength = (document.getElementById("infoList").rows.length);
   // alert(document.getElementById("infoList").rows[1].cells[1].innerHTML);
    for (var i=1; i < wayptsLength; i++){
      var wayptsName = document.getElementById("infoList").rows[i].cells[1].innerHTML;
     // alert(wayptsName);
      waypts.push({
        location:wayptsName,
        stopover:true
      })
    }
    var request = {
      origin: start,
      destination: end,
      waypoints: waypts,
      optimizeWaypoints: true,
      //icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
      travelMode: google.maps.TravelMode.DRIVING
    };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
      var route = response.routes[0];
    }
  });
  clearMarkers();
}

//Saves the places to the DB. 
function savePlaceList(){
  var csrfmiddlewaretoken = document.getElementsByName('csrfmiddlewaretoken')[0].value;
  var wayptsLength = (document.getElementById("infoList").rows.length);
  for (var i=1; i < wayptsLength; i++) {
    var row = document.getElementById("infoList").rows[i];
    var placeName =  row.cells[0].innerHTML;
    var placeAddress = row.cells[1].innerHTML;
    var webcell = row.cells[2];
    var placeWebsite = "None";
    if (webcell.childElementCount != 0) {
      placeWebsite = webcell.children[0].href;
    }
    var placePhone = row.cells[3].innerHTML;
    var placeRating = row.cells[4].innerHTML;
    if (csrfmiddlewaretoken != null && trip_id != "No Auth") {
      $.post("/history/" + trip_id + "/addplace/",
        { 
          name:placeName,
          address:placeAddress,
          website:placeWebsite, 
          phone:placePhone,
          rating:placeRating, 
          csrfmiddlewaretoken:csrfmiddlewaretoken
        },
        function(data,status){
          //alert(data, status);
        }  )
    }
  }

}

var red_markers = 'lodging';
var green_markers = 'restaurant';
var blue_markers = 'gas_station';

var orange_markers = ['amusement_park', 'aquarium', 'bowling_alley', 'casino',
					'museum', 'night_club', 'park', 'shopping_mall', 'spa',
					'stadium', 'zoo', 'other'];

var markers = red_markers.concat(blue_markers).concat(green_markers).concat(orange_markers);

function showStuff(map,position){
     //The function to find the different hotels and other locations of interest along the route
      var typesToSearch=[];
	  var addall = false;

	  if (document.getElementById("hotel") == null) {
        addall = true;
	  }
      
      if (addall || document.getElementById("hotel").checked){
         typesToSearch.push('lodging');            
      }
      if (addall || document.getElementById("restaurant").checked){
         typesToSearch.push('restaurant');            
      }
      if (addall || document.getElementById("gas").checked){
         typesToSearch.push('gas_station');            
      }
      if (addall || document.getElementById("attraction").checked){

        typesToSearch.push('amusement_park', 'aquarium', 
        'bowling_alley', 'casino', 'museum', 'night_club', 'park', 'shopping_mall', 
        'spa', 'stadium', 'zoo');            
      }
	  if (typesToSearch.length == 0) {
	  	console.log("Nothing selected");
		return;
	  }
      
      var request = {
        bounds: position, // in this box containing the route
        types: typesToSearch, //find places
        rankBy: google.maps.places.RankBy.PROMINENCE //ranks by rating
      };
         
      placesService.nearbySearch(request, createCallback(request));
} 

function createCallback(request) {
return function (results, status) {
  //Function to call the marker creator
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    
    for (var i = 0; i < results.length; i++) {
      createMarker(results[i]);
    }
  }
  if (status == google.maps.places.PlacesServiceStatus.OVER_QUERY_LIMIT) {
    setTimeout(function() {
      placesService.nearbySearch(request, createCallback(request));
    }, 1000);
  }
};
}

function saveToList(place) {
	var info;
	info = '<td id="placeName">'+place.name+'</td><td>'+place.formatted_address+'</td>';
	if (!!place.website)
		info += '<td><a target="_blank" href="'+place.website+'">'+place.website+'</a></td>';
	else
		info+= '<td> None </td>';
	if(!!place.formatted_phone_number)
		info += '<td> '+place.formatted_phone_number+'</td>';
	else
		info+= '<td> None </td>';
	if(!!place.rating)
		info += '<td>'+place.rating +'/5</td>';
	else
		info+= '<td> None </td>'

	info += '<td><button class="btn btn-danger">Delete</button></td>';

	var csrfmiddlewaretoken = document.getElementsByName('csrfmiddlewaretoken')[0].value;
	if (csrfmiddlewaretoken != null && trip_id != "No Auth") {
		$.post("/history/" + trip_id + "/addplace/",
        {
          name:place.name,
          address:place.formatted_address,
          website:place.website,
          phone:place.formatted_phone_number,
          rating:place.rating,
          csrfmiddlewaretoken:csrfmiddlewaretoken
        },
        function(data,status) {
			if (data == "None") {
				return;
			}
			//place holder to save the location and information as a pinpoint of roadtrip
			var savedList = document.getElementById('infoList');
			var row = savedList.insertRow(-1);
			row.innerHTML = info;
			row.id = "placerow"+data;
			row.cells[5].children[0].onclick = function() {
				deletePlace(data);
			};
        });
    } else {
		var savedList = document.getElementById('infoList');
		var row = savedList.insertRow(-1);
		row.innerHTML = info;
	}

}

function createMarker(place){
  //First find the correct icon for the place
  var iconname = "";
  if (place.types.indexOf(red_markers)>=0){
      iconname= "red_MarkerH.png";     
   }
  else if (place.types.indexOf(blue_markers)>=0){
      iconname= "blue_MarkerG.png";
   }
  else if (place.types.indexOf(green_markers)>=0){
      iconname= "green_MarkerR.png";
   }
  else{
      iconname= "orange_MarkerA.png";
   }

  var icons= '/static/website/Markers/' + iconname;
    //Creates a marker on the map 
  var gmarker = new google.maps.Marker({
    map: map,
    position: place.geometry.location,
	  icon: icons,
  });

  //push the marker to the correct type for filtering
  if (place.types.indexOf(red_markers)>=0){
    //console.log("Hotel");
	  hotelMarkers.push(gmarker);
	} 
  else if (place.types.indexOf(green_markers)>=0){
    //console.log("Rest");
	  restaurantMarkers.push(gmarker);
	} 
  else if (place.types.indexOf(blue_markers)>=0){
    //console.log("gas");
	  gasMarkers.push(gmarker);
  }
   else{
    //console.log("Att");
    attractionMarkers.push(gmarker);
   }
  mapMarkers[i]= gmarker;
  placeMarkers[i] = place;
  
  var link = '<a href="javascript:void(0);" onclick="myclick('+i+')">';
  $("#places").append('<tr><td>'+link + place.name + '</a></td></tr>');
    i++;
  //Adds a listener to show the details of the location being clicked on
  google.maps.event.addListener(gmarker,'click',function(){
    OpenInfoWindow(place, gmarker);
  })


}

function OpenInfoWindow(place,gmarker){
    var request = {reference:place.reference};
    placesService.getDetails(request, function(place,status){
      if (status==google.maps.places.PlacesServiceStatus.OK){
        //Set contents of the details "Note" after the icon is clicked. 
        var Note = '<h5><center>'+place.name+'</center></h5><p>Address: '+place.formatted_address;
        if (!!place.website)
          Note += '<br>Website: <a target="_blank" href="'+place.website+'">'+place.website+'</a>';
        if(!!place.formatted_phone_number)
          Note += '<br>Phone: '+place.formatted_phone_number;
        if(!!place.rating)
          Note += '<br>Rating: '+place.rating +'/5';
        //place holder to save the location and information as a pinpoint of roadtrip
        var Add = '<br><input type="Submit" name="Save" value="Add to List" onclick="saveList();"/>';
        infowindow.setContent(Note+Add);
        infowindow.open(map,gmarker);
        //Adds the Note Infomration into the DOM. Will be sent to backend for saving 
        saveList = function (){
            saveToList(place);
        }
     }
      //If no details, throw a no result exception
      else{
        var Note = "<p>No Result,"+ status+"</p>";
        infowindow.setContent(Note);
        infowindow.open(map,marker);
      }
    })
}


function myclick(i){
  var m = mapMarkers[i];
  var p = placeMarkers[i];
  OpenInfoWindow(p,m);
}


function setAllMap(map, markerType){
  //console.log("Im here now"); 
  for(var i =0; i<markerType.length; i++){
    markerType[i].setMap(map);
  }
}


function toggleHotelMarkers(){
  if (!document.getElementById("hotel").checked){
    setAllMap(map, hotelMarkers);
  }
  else{
    setAllMap(null, hotelMarkers);
  }
  
}


function toggleRestaurantMarkers(){
  if (!document.getElementById("restaurant").checked){
    setAllMap(map, restaurantMarkers);
  }
  else{
    setAllMap(null, restaurantMarkers);
  }
  
}

function toggleGasMarkers(){
  if (!document.getElementById("gas").checked){
    setAllMap(map, gasMarkers);
  }
  else{
    setAllMap(null, gasMarkers);
  }
  
}

function toggleAttractionMarkers(){
  if (!document.getElementById("attraction").checked){
    setAllMap(map, attractionMarkers);
  }
  else{
    setAllMap(null, attractionMarkers);
  }
  
}


//removes all markers
function clearMarkers() {
  //setAllMap(null);
  setAllMap(null, hotelMarkers);
  setAllMap(null, restaurantMarkers);
  setAllMap(null, gasMarkers);
  setAllMap(null, attractionMarkers);
}


function step2error() {
	$(error).html("Error finding directions.");
	$(error).show();
}
function step2success() {
	$(step2Head).hide();
	$(go).hide();
	$(where).hide();
	$(recalc).show();
	$(savedList).show();
	$(resultList).show();
	$(choice).prepend("<br><br>");
	$(choice).toggleClass('col-md-2');
}

function step2(start, end){
    calcRoute(step2success, step2error);
}

function step2auto(){
    autoRoute(step2success, step2error);
}

jQuery(document).ready(function($) {
    $(recalc).hide();
    $(savedList).hide();
    $(resultList).hide();
});
google.maps.event.addDomListener(window, 'load', initialize);



function autoRoute(success, error) {
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	var request = {
		origin:start,
		destination:end,
		travelMode: google.maps.TravelMode.DRIVING
	};
	var time = 8*60*60;
	var numRequests = 0;
	var doneRequests = 0;
	directionsService.route(request, function(response, status) {
		if (status == google.maps.DirectionsStatus.OK) {
			submitTrip(start, end);
			success();
			$(resultList).hide();
			$(choice).hide();
			var leg = response.routes[0].legs[0];
			var total = 0;
			for (var i in leg.steps) {
				var step = leg.steps[i];
				var newtotal = total + step.duration.value;
				if (newtotal > time) {
					newtotal = step.duration.value;
					var request = {
						location: step.lat_lngs[0],
						radius: '50000',
						types: ['lodging']
					};
					var service = new google.maps.places.PlacesService(map);
					++numRequests;
					service.nearbySearch(request, function(results, status) {
						if (status == google.maps.places.PlacesServiceStatus.OK) {
							//TODO: Make this find the closest hotel, or best hotel
							var request = {reference:results[0].reference};
							placesService.getDetails(request, function(place,status) {
								if (status == google.maps.places.PlacesServiceStatus.OK) {
									++doneRequests;
									saveToList(place);
								} else {
									console.log("No detail");
								}
							});
						} else {
							console.log("No results");
						}
					});
				}
				total = newtotal;
			}
			timefunc = function() {
				if (doneRequests == numRequests) {
					reRoute();
				} else {
					setTimeout(timefunc, 100);
				}
			}
			setTimeout(timefunc, 100);

		} else {
			error();
		}


	});


}

function deletePlace(id) {
    var csrfmiddlewaretoken = document.getElementsByName('csrfmiddlewaretoken')[0].value;
	$.post("/place/"+id+"/delete/", { csrfmiddlewaretoken: csrfmiddlewaretoken});
	document.getElementById("placerow"+id).remove();
}
