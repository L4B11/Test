package com.example;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.model.TicketModel;
import com.example.model.Tickets;

public class JsonSimpleParser {
    
    private static final String TAG_Name_Main = "tickets";

    private static final String TAG_origin = "origin";
    private static final String TAG_origin_name = "origin_name";
    private static final String TAG_destination = "destination";
    private static final String TAG_destination_name = "destination_name";
    private static final String TAG_departure_date = "departure_date";
    private static final String TAG_departure_time = "departure_time";
    private static final String TAG_arrival_date = "arrival_date";
    private static final String TAG_arrival_time = "arrival_time";
    private static final String TAG_carrier = "carrier";
    private static final String TAG_stops = "stops";
    private static final String TAG_price = "price";



    public TicketModel parse(){

        TicketModel root = new TicketModel();

        JSONParser parser = new JSONParser();
        
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("demo/src/main/resources/tickets.json"), "UTF8")){   

            JSONObject rootJSONObject = (JSONObject) parser.parse(reader);

            JSONArray ticketsJsonArray = (JSONArray) rootJSONObject.get(TAG_Name_Main);

            List<Tickets> ticketsList = new ArrayList<>();
            for (Object it: ticketsJsonArray) {
                JSONObject ticketsJSONObject = (JSONObject) it;

                String OriginTickets = (String)ticketsJSONObject.get(TAG_origin); 
                String OriginNameTickets = (String)ticketsJSONObject.get(TAG_origin_name);
                String DestinationTickets = (String)ticketsJSONObject.get(TAG_destination);
                String DestinationNameTickets = (String)ticketsJSONObject.get(TAG_destination_name);
                String DepartureDateTickets = (String)ticketsJSONObject.get(TAG_departure_date);
                String DepartureTimeTickets = (String)ticketsJSONObject.get(TAG_departure_time);
                String ArrivalDateTickets = (String)ticketsJSONObject.get(TAG_arrival_date);
                String ArrivalTimeTickets = (String)ticketsJSONObject.get(TAG_arrival_time);
                String CarrierTickets = (String)ticketsJSONObject.get(TAG_carrier);
                long StopsTickets = (Long)ticketsJSONObject.get(TAG_stops);
                long PriceTickets = (Long)ticketsJSONObject.get(TAG_price);

                Tickets tickets = new Tickets(OriginTickets, OriginNameTickets, DestinationTickets, DestinationNameTickets, DepartureDateTickets, DepartureTimeTickets, ArrivalDateTickets, ArrivalTimeTickets, CarrierTickets, (int) StopsTickets, (int) PriceTickets);

                ticketsList.add(tickets);
            }

            root.setTickets(ticketsList);

            return root;
        } catch (Exception e) {
            System.out.println("Parsing Error " + e.toString());
        }



        return null;
    }

}
