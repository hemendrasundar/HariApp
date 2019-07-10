package com.sun.hariapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private Spinner country_Spinner;
    private Spinner state_Spinner;
    private Spinner city_Spinner;

    private ArrayAdapter<Country> countryArrayAdapter;
    private ArrayAdapter<State> stateArrayAdapter;
    private ArrayAdapter<City> cityArrayAdapter;

    private ArrayList<Country> countries;
    private ArrayList<State> states;
    private ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country_Spinner = (Spinner) findViewById(R.id.country_spinner);
        state_Spinner = (Spinner) findViewById(R.id.state_spinner);
        city_Spinner = (Spinner) findViewById(R.id.city_spinner);



        countries = new ArrayList<>();
        states = new ArrayList<>();
        cities = new ArrayList<>();

        createLists();

        countryArrayAdapter = new ArrayAdapter<Country>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country_Spinner.setAdapter(countryArrayAdapter);

        stateArrayAdapter = new ArrayAdapter<State>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, states);
        stateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state_Spinner.setAdapter(stateArrayAdapter);

        cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_Spinner.setAdapter(cityArrayAdapter);

        country_Spinner.setOnItemSelectedListener(country_listener);
        state_Spinner.setOnItemSelectedListener(state_listener);
        city_Spinner.setOnItemSelectedListener(city_listener);

    }

    private AdapterView.OnItemSelectedListener country_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final Country country = (Country) country_Spinner.getItemAtPosition(position);

                ArrayList<State> tempStates = new ArrayList<>();

                tempStates.add(new State(0, new Country(0, "Choose a Country"), "Choose a State"));

                for (State singleState : states) {
                    if (singleState.getCountry().getCountryID() == country.getCountryID()) {
                        tempStates.add(singleState);
                    }
                }

                stateArrayAdapter = new ArrayAdapter<State>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, tempStates);
                stateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                state_Spinner.setAdapter(stateArrayAdapter);
            }

            cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<City>());
            cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            city_Spinner.setAdapter(cityArrayAdapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener state_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final State state = (State) state_Spinner.getItemAtPosition(position);

                ArrayList<City> tempCities = new ArrayList<>();

                Country country = new Country(0, "Choose a Country");
                State firstState = new State(0, country, "Choose a State");
                tempCities.add(new City(0, country, firstState, "Choose a City"));

                for (City singleCity : cities) {
                    if (singleCity.getState().getStateID() == state.getStateID()) {
                        tempCities.add(singleCity);
                    }
                }

                cityArrayAdapter = new ArrayAdapter<City>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tempCities);
                cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                city_Spinner.setAdapter(cityArrayAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener city_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void createLists() {
        Country country0 = new Country(0, "Choose a Country");
        Country country1 = new Country(1, "India");
        Country country2 = new Country(2, "Pakistan");

        countries.add(new Country(0, "Choose a Country"));
        countries.add(new Country(1, "India"));
        countries.add(new Country(2, "Pakistan"));

        State state0 = new State(0, country0, "Choose a Country");
        State state1 = new State(1, country1, "Andhra");
        State state2 = new State(2, country1, "Telangana");
        State state3 = new State(3, country2, "punjab");
        State state4 = new State(4, country2, "sindh");

        states.add(state0);
        states.add(state1);
        states.add(state2);
        states.add(state3);
        states.add(state4);

        cities.add(new City(0, country0, state0, "Choose a City"));
        cities.add(new City(1, country1, state1, "nellore"));
        cities.add(new City(2, country1, state1, "vijayawada"));
        cities.add(new City(3, country1, state2, "hyderbad"));
        cities.add(new City(4, country2, state2, "kammam"));
        cities.add(new City(5, country2, state3, "kotli"));
        cities.add(new City(6, country2, state3, "bagh"));
        cities.add(new City(7, country2, state4, "neelum"));
        cities.add(new City(8, country1, state4, "havelis"));
    }
}
