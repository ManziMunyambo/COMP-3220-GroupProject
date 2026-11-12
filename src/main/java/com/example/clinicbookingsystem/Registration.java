package com.example.clinicbookingsystem;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import com.example.clinicbookingsystem.model.Person;

public abstract class Registration {
    //attributes
    protected Database database;
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-uuuu").withResolverStyle(ResolverStyle.STRICT);

    //getters
    public Database getDatabase(){return database;}
    public DateTimeFormatter getDateFormatter(){return DATE_FORMATTER;}

    //methods
    public abstract Person register();
    public abstract boolean validateRegistration(Person person);
    
}
