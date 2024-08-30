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
    
    private static final String tagNameMain = "tickets";

    private static final String tagOrigin = "origin";
    private static final String tagOriginName = "origin_name";
    private static final String tagDestination = "destination";
    private static final String tagDestinationName = "destination_name";
    private static final String tagDepartureDate = "departure_date";
    private static final String tagDepartureTime = "departure_time";
    private static final String tagArrivalDate = "arrival_date";
    private static final String tagArrivalTime = "arrival_time";
    private static final String tagCarrier = "carrier";
    private static final String tagStops = "stops";
    private static final String tagPrice = "price";



    public TicketModel parse(){

        TicketModel modelOfTickets = new TicketModel();

        JSONParser parserTicket = new JSONParser();
         
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("demo/src/main/resources/tickets.json"), "UTF8")){   

            JSONObject rootJSONObject = (JSONObject) parserTicket.parse(reader);

            JSONArray ticketsJsonArray = (JSONArray) rootJSONObject.get(tagNameMain);

            List<Tickets> ticketManager = new ArrayList<>();
            for (Object it: ticketsJsonArray) {
                JSONObject ticketsJSONObject = (JSONObject) it;

                String OriginTickets = (String)ticketsJSONObject.get(tagOrigin); 
                String OriginNameTickets = (String)ticketsJSONObject.get(tagOriginName);
                String DestinationTickets = (String)ticketsJSONObject.get(tagDestination);
                String DestinationNameTickets = (String)ticketsJSONObject.get(tagDestinationName);
                String DepartureDateTickets = (String)ticketsJSONObject.get(tagDepartureDate);
                String DepartureTimeTickets = (String)ticketsJSONObject.get(tagDepartureTime);
                String ArrivalDateTickets = (String)ticketsJSONObject.get(tagArrivalDate);
                String ArrivalTimeTickets = (String)ticketsJSONObject.get(tagArrivalTime);
                String CarrierTickets = (String)ticketsJSONObject.get(tagCarrier);
                long StopsTickets = (Long)ticketsJSONObject.get(tagStops);
                long PriceTickets = (Long)ticketsJSONObject.get(tagPrice);

                Tickets tickets = new Tickets(OriginTickets, OriginNameTickets, DestinationTickets, DestinationNameTickets, DepartureDateTickets, DepartureTimeTickets, ArrivalDateTickets, ArrivalTimeTickets, CarrierTickets, (int) StopsTickets, (int) PriceTickets);

                ticketManager.add(tickets);
            }

            modelOfTickets.setTickets(ticketManager);

            return modelOfTickets;
        } catch (Exception e) {
            System.out.println("Parsing Error " + e.toString());
        }



        return null;
    }

}
