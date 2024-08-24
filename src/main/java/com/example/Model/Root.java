package com.example.Model;

import java.util.List;

public class Root {
    
    private List<Tickets> tickets;

    public List<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(List<Tickets> tickets){
        this.tickets = tickets;
    }

    public String toString(){
        return "Root{" + "ticket = '" + tickets + "}'";
    }
}
