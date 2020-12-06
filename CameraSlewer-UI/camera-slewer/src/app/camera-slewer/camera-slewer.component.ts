import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Camera } from '../models/camera';
import {Location} from '../models/location';
import { DataService } from '../service/dataService';
import { GetNearByResponse } from '../service/response/getNearByresponse';

let marker1: google.maps.Marker, marker2: google.maps.Marker;
let poly: google.maps.Polyline, geodesicPoly: google.maps.Polyline

declare const google: any;

@Component({
  selector: 'app-camera-slewer',
  templateUrl: './camera-slewer.component.html',
  styleUrls: ['./camera-slewer.component.css']
})
export class CameraSlewerComponent implements OnInit {

  camera: Camera;
  nearestLat: number;
  nearestLong: number;
  nearestLocation: String;
  allLocations: Location[];
  
  constructor(
    public dataService: DataService,
    private route:Router) { }

  ngOnInit(): void {
    let nearestLocation: GetNearByResponse = this.dataService.nearestLocation;
    if(nearestLocation && nearestLocation.locations && nearestLocation.locations.length > 0) {
        this.camera = nearestLocation.camera;
        this.nearestLat  = parseFloat(nearestLocation.locations[0].latitude);
        this.nearestLong = parseFloat(nearestLocation.locations[0].longitude);
        this.nearestLocation = nearestLocation.locations[0].locationName;
        this.allLocations = nearestLocation.locations;
        this.initMap();       
    }else {
      alert("No locations found");
      this.route.navigate(['/camera']);
    }
    
  }

 initMap(): void {
    const map = new google.maps.Map(
      document.getElementById("map") as HTMLElement,
      {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.HYBRID
        //center: { lat: 25.2854, lng: 51.5310 },
      }
    );
  
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(
      document.getElementById("info") as HTMLElement
    );

    var icon = {
      url: "assets/markers/cinema.svg",
      scaledSize: new google.maps.Size(50, 50)
    };

    var icon2 = {
      url: "assets/markers/pin.svg",
      scaledSize: new google.maps.Size(50, 50)
    };
  
    marker1 = new google.maps.Marker({
      map,
      draggable: true,
      position: { lat: parseFloat(this.camera.latitude), lng: parseFloat(this.camera.longitude) },
      title: this.camera.name,
      icon: icon
    });
    marker2 = new google.maps.Marker({
      map,
      draggable: true,
      position: { lat: this.nearestLat, lng: this.nearestLong },
      title: this.nearestLocation,
      animation: google.maps.Animation.DROP,
      icon: icon2
    });
    var markers = [];
    this.allLocations.forEach((value, index) => {
      if(index != 0) {
        markers[index+3] = new google.maps.Marker({
          map,
          draggable: true,
          position: { lat: parseFloat(value.latitude), lng: parseFloat(value.longitude) },
          title: value.locationName
        });
      }     
    });
  
    const bounds = new google.maps.LatLngBounds(
      marker1.getPosition() as google.maps.LatLng,
      marker2.getPosition() as google.maps.LatLng
    );
    map.fitBounds(bounds);
    marker2.addListener("click", this.toggleBounce);
  
    google.maps.event.addListener(marker1, "position_changed", this.update);
    google.maps.event.addListener(marker2, "position_changed", this.update);

    const lineSymbol = {
      path: google.maps.SymbolPath.CIRCLE,
      scale: 8,
      strokeColor: "#b3d4fc",
    };
    
    poly = new google.maps.Polyline({
      strokeColor: "#00406e",
      strokeOpacity: 1.0,
      strokeWeight: 3,
      icons: [
        {
          icon: lineSymbol,
          offset: "100%",
        },
      ],
      map: map,
    });
  
    geodesicPoly = new google.maps.Polyline({
      strokeColor: "#00406e",
      strokeOpacity: 1.0,
      strokeWeight: 3,
      geodesic: true,
      map: map,
    });

    this.animateCircle(poly);
  
    this.update();
    var bounds1 = new google.maps.LatLngBounds();
    var points = poly.getPath().getArray();
    for (var n = 0; n < points.length ; n++){
        bounds1.extend(points[n]);
    }
    map.fitBounds(bounds1);

    let popup = new Popup(
      new google.maps.LatLng(this.nearestLat, this.nearestLong),
      document.getElementById("content") as HTMLElement
    );
    popup.setMap(map);

  }
toggleBounce() {
    if (marker2.getAnimation() !== null) {
      marker2.setAnimation(null);
    } else {
      marker2.setAnimation(google.maps.Animation.BOUNCE);
    }
  }

animateCircle(line: google.maps.Polyline) {
    let count = 0;
    window.setInterval(() => {
      count = (count + 1) % 200;
  
      const icons = line.get("icons");
      icons[0].offset = count / 2 + "%";
      line.set("icons", icons);
    }, 20);
  }  
  
update() {
    const path = [
      marker1.getPosition() as google.maps.LatLng,
      marker2.getPosition() as google.maps.LatLng,
    ];
    poly.setPath(path);
    geodesicPoly.setPath(path);
    const heading = google.maps.geometry.spherical.computeHeading(
      path[0],
      path[1]
    );
    (document.getElementById("heading") as HTMLInputElement).value = String(
      heading
    );
    (document.getElementById("origin") as HTMLInputElement).value = String(
      path[0]
    );
    (document.getElementById("destination") as HTMLInputElement).value = String(
      path[1]
    );
   
  }


}  

 /**
   * A customized popup on the map.
   */
  class Popup extends google.maps.OverlayView {
    position: google.maps.LatLng;
    containerDiv: HTMLDivElement;

    constructor(position: google.maps.LatLng, content: HTMLElement) {
      super();
      this.position = position;

      content.classList.add("popup-bubble");

      // This zero-height div is positioned at the bottom of the bubble.
      const bubbleAnchor = document.createElement("div");
      bubbleAnchor.classList.add("popup-bubble-anchor");
      bubbleAnchor.appendChild(content);

      // This zero-height div is positioned at the bottom of the tip.
      this.containerDiv = document.createElement("div");
      this.containerDiv.classList.add("popup-container");
      this.containerDiv.appendChild(bubbleAnchor);

      // Optionally stop clicks, etc., from bubbling up to the map.
      Popup.preventMapHitsAndGesturesFrom(this.containerDiv);
    }

    /** Called when the popup is added to the map. */
    onAdd() {
      this.getPanes().floatPane.appendChild(this.containerDiv);
    }

    /** Called when the popup is removed from the map. */
    onRemove() {
      if (this.containerDiv.parentElement) {
        this.containerDiv.parentElement.removeChild(this.containerDiv);
      }
    }

    /** Called each frame when the popup needs to draw itself. */
    draw() {
      const divPosition = this.getProjection().fromLatLngToDivPixel(
        this.position
      );

      // Hide the popup when it is far out of view.
      const display =
        Math.abs(divPosition.x) < 4000 && Math.abs(divPosition.y) < 4000
          ? "block"
          : "none";

      if (display === "block") {
        this.containerDiv.style.left = divPosition.x + "px";
        this.containerDiv.style.top = divPosition.y + "px";
      }

      if (this.containerDiv.style.display !== display) {
        this.containerDiv.style.display = display;
      }
    }
  }
