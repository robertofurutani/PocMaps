package br.com.engdb.poc;

import java.io.IOException;

import com.google.maps.DirectionsApi;
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

public class MainDirections {

	public static void main(String[] args) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyC3bUPf9d79UqE1OCVq5WvH10prLFD8-c4").build();

		try {

			
			DirectionsResult result = DirectionsApi.newRequest(context)
					  .mode(TravelMode.DRIVING)
					  .units(Unit.METRIC)
					  .region("br")
		              .origin("Av Paulista, 2150, São Paulo, SP")
		              .destination( "Av Pacaembu, 150, São Paulo, SP")
		              .departureTimeNow()
		              .alternatives(true)
		              .trafficModel(TrafficModel.PESSIMISTIC)
		              .transitRoutingPreference(TransitRoutingPreference.FEWER_TRANSFERS)
		              .language("pt-BR")
		              .await();
			
			DirectionsRoute[] routes = result.routes;
			for(DirectionsRoute r : routes) {
				for(DirectionsLeg leg: r.legs) {
				System.out.println(leg.startAddress + " -- " + leg.endAddress);
				System.out.println(leg.distance);
				System.out.println(leg.duration);
				for (DirectionsStep step : leg.steps) {
					System.out.println(step);
				}
				}
			}
			
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
