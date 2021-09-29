package br.com.engdb.poc;

import java.io.IOException;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest.Waypoint;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

/*
 * API Doc:  https://developers.google.com/maps/documentation/directions/get-directions
 * Lib Java doc: https://github.com/googlemaps/google-maps-services-java/
 */
public class MainDirections {
   
	private static final String ENDERECO_FIXO = "Av Paulista, 100, São Paulo, SP";

	public static void main(String[] args) {
		GeoApiContext context = new GeoApiContext.Builder()
												 .apiKey("AIzaSyC3bUPf9d79UqE1OCVq5WvH1.......")
												 .build();

		try {
			Waypoint p1 = new Waypoint("Av Paulista, 2150, São Paulo, SP");
			Waypoint p2 = new Waypoint("Av Pacaembu, 150, São Paulo, SP");
			Waypoint p3 = new Waypoint("Av Consolação, 522, São Paulo, SP");
			Waypoint p4 = new Waypoint("Av Sumare, 810, São Paulo, SP");
			
			DirectionsResult result = DirectionsApi.newRequest(context)
					  .mode(TravelMode.DRIVING)
					  .units(Unit.METRIC)
					  .region("br")
		              .origin(ENDERECO_FIXO)
		              .waypoints(p1,p2, p3, p4)
		              .optimizeWaypoints(true)
		              .destination( ENDERECO_FIXO)
		              .departureTimeNow()
		              .alternatives(false)
		              .trafficModel(TrafficModel.BEST_GUESS)		              
		              .language("pt-BR")
		              .await();
			
			DirectionsRoute[] routes = result.routes;
			long totalTime = 0;
			for(DirectionsRoute r : routes) {
				for(DirectionsLeg leg: r.legs) {
				System.out.println(leg.startAddress + " -- " + leg.endAddress);
				System.out.println(leg.startLocation + " -- " + leg.endLocation);
				System.out.println(leg.distance);
				System.out.println(leg.duration);
				totalTime += leg.duration.inSeconds;
//				for (DirectionsStep step : leg.steps) {
//					//System.out.println(step);
//				}
				}
				
			}
			
			System.out.println("Tempo Total: " + (totalTime/ 60));
			
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Invoke .shutdown() after your application is done making requests
		context.shutdown();
	}

}
