package com.example;

import java.util.ArrayList;

import com.example.Model.Root;
import com.example.Model.Tickets;

public class Main {
    public static  String gettime(String str){
        JsonSimpleParser parser = new JsonSimpleParser();
        Root root = parser.parse();
        int m = -1;
        for (int i = 0; i < root.getTickets().size(); i++){
            if (root.getTickets().get(i).getCarrier().equals(str)){
                if (m == -1 || m > getmin(i)){ 
                    m = getmin(i);
                }
            }
        }
        if (m == -1){ return "-1";}
        String finaltime = "";
        if (m%60 > 9){
            finaltime = String.valueOf(m/60) + ":" + String.valueOf(m%60);
        }else{
            finaltime = String.valueOf(m/60) + ":0" + String.valueOf(m%60);
        }
        return finaltime;
    }

    public static  int getmin(int i){
        JsonSimpleParser parser = new JsonSimpleParser();
        Root root = parser.parse();
        // в данном методе я не буду расписывать учитывание дат при подсчете времени тк они никогда не меняются
        String start = root.getTickets().get(i).getDepartureTime();
        String end = root.getTickets().get(i).getArrivalTime();
        String[] st = start.split(":");
        String[] en = end.split(":");
        
        int min = Integer.parseInt(en[0])*60 + Integer.parseInt(en[1]) - Integer.parseInt(st[0])*60 - Integer.parseInt(st[0]);
        
        return min;
    }

    public static void main(String[] args) {
        JsonSimpleParser parser = new JsonSimpleParser();
        Root root = parser.parse();
        ArrayList<Tickets> ticList = new ArrayList<>();
        ArrayList<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);
        x.add(6);
        for (int i = 0; i < x.size(); i++){
            System.out.println(x.get(i));
        }
        System.out.println("TK: " + gettime("TK"));
        System.out.println("S7: " + gettime("S7"));
        System.out.println("SU: " + gettime("SU"));
        System.out.println("BA: " + gettime("BA"));
        for (int i = 0; i < root.getTickets().size(); i++){

        }


        root.getTickets().stream().filter(tickets -> {
            return tickets.getPrice() == 7000;
        }).forEach(tickets -> {
            System.out.println("Ticket: " + tickets.toString());
        });

        //System.err.println("Root: " + root.toString());
    }
}