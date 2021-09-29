package br.com.engdb.poc;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.commons.lang3.StringUtils;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;



public class MainMatrix {

	public static void main(String[] args) {
		
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyC3bUPf9d79UqE1OCVq5WvH10prLFD8-c4").build();

		String[] origins =
		          new String[] {
		            "Perth, Australia", "Sydney, Australia", "Melbourne, Australia",
		            "Adelaide, Australia", "Brisbane, Australia", "Darwin, Australia",
		            "Hobart, Australia", "Canberra, Australia"
		          };
		      String[] destinations =
		          new String[] {
		            "Uluru, Australia",
		            "Kakadu, Australia",
		            "Blue Mountains, Australia",
		            "Bungle Bungles, Australia",
		            "The Pinnacles, Australia"
		          };

		      try {
				DistanceMatrixApi.newRequest(context)
				      .origins(origins)
				      .destinations(destinations)
				      .mode(TravelMode.DRIVING)
				      .language("en-AU")
				      .avoid(RouteRestriction.TOLLS)
				      .units(Unit.IMPERIAL)
				      .departureTime(
				          Instant.now().plus(Duration.ofMinutes(2))) 
				      .await();
				
				System.out.println(StringUtils.join(origins, "|") + " origins");
				System.out.println(StringUtils.join(destinations, "|") +  " destinations");
				
				
			} catch (ApiException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

}
