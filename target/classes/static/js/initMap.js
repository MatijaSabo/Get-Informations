function initMap() {
    var lat = document.getElementById('lat').value;
    var lng = document.getElementById('lng').value;
    var location = {lat: Number(lat), lng: Number(lng)};
    var map = new google.maps.Map(document.getElementById('map'), {zoom: 15, center: location});
    var marker = new google.maps.Marker({position: location, map: map});
}