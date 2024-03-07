package groupsix.citywalk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// The Education class represents educational pop-ups or dialogs within the game.
public class Education {
    // HashMaps to store educational messages for each transport mode.
    private HashMap<String, ArrayList<String>> positiveEdList;
    private HashMap<String, ArrayList<String>> negativeEdList;

    // The constructor initializes the lists of educational messages.
    public Education() {
        positiveEdList = new HashMap<>();
        negativeEdList = new HashMap<>();
        initEdLists();
    }

    // Initialize educational lists with default values
    private void initEdLists() {
        // Initialize positive environmental feedback for walk, bike and public transport.
        ArrayList<String> walkPositive = new ArrayList<>();
        walkPositive.add("Discover the green side of life: Walking lets you enjoy nature while reducing air pollution.");
        walkPositive.add("Every step counts: Walking cuts down on CO2 emissions, making our cities cleaner and greener.");
        positiveEdList.put("WALK", walkPositive);

        ArrayList<String> cyclePositive = new ArrayList<>();
        cyclePositive.add("Pedal power: Cycling is a zero-emission journey, keeping our air pure and our bodies fit.");
        cyclePositive.add("Join the cycle revolution: Reduce your carbon wheel-print and enjoy the eco-friendly breeze.");
        positiveEdList.put("CYCLE", cyclePositive);

        ArrayList<String> busPositive = new ArrayList<>();
        busPositive.add("Shared journeys, greener cities: Buses mean less traffic and cleaner air for everyone.");
        busPositive.add("Hop on the eco-friendly bus: Each ride helps cut down on carbon emissions.");
        positiveEdList.put("BUS", busPositive);

        ArrayList<String> dartPositive = new ArrayList<>();
        dartPositive.add("DART towards a cleaner future: Fast, efficient, and a friend to the environment.");
        dartPositive.add("Embrace the green commute: DART reduces the need for cars, making our air fresher.");
        positiveEdList.put("DART", dartPositive);

        ArrayList<String> luasPositive = new ArrayList<>();
        luasPositive.add("Ride the green wave with LUAS: Less noise, less pollution, more smiles per mile.");
        luasPositive.add("Join the sustainable commute: LUAS is your eco-friendly ticket to cleaner urban living.");
        positiveEdList.put("LUAS", luasPositive);

        // Initialize negative environmental feedback for TAXI
        ArrayList<String> taxiNegative = new ArrayList<>();
        taxiNegative.add("Rethink your ride: Taxis contribute to urban congestion and higher carbon emissions.");
        taxiNegative.add("Catch a green ride instead: Opt for more sustainable options than taxis to reduce your environmental impact.");
        negativeEdList.put("TAXI", taxiNegative);

    }

    // Generates an education pop-up while traveling.
    @Override
    public void travelPopUp(TransportMode mode) {
        try {
            // Attempt to get a list of messages for the given mode
            ArrayList<String> selectedList = positiveEdList.getOrDefault(mode.name(), new ArrayList<>());
            selectedList.addAll(negativeEdList.getOrDefault(mode.name(), new ArrayList<>()));

            if (!selectedList.isEmpty()) {
                String message = selectedList.get(new Random().nextInt(selectedList.size()));
                // Logic to display the message as a pop-up
                System.out.println(message); // Placeholder for pop-up logic
            } else {
                throw new IllegalArgumentException("No messages available for transport mode: " + mode);
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where there are no messages for the mode
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Provides end-level education feedback based on route selection.
    public String edFeedback(TransportMode mode, double playerFP, int endStoreFeedback) {
        String feedback = "Your choice of " + mode.name() + " resulted in " + playerFP + " carbon footprint. ";
        if (endStoreFeedback > 0) {
            feedback += "Good job on minimizing environmental impact!";
        } else {
            feedback += "Consider alternative modes to reduce carbon footprint.";
        }
        return feedback;
    }

    // Enumeration for transport modes.
    public enum TransportMode {
        WALK, CYCLE, TAXI, BUS, DART, LUAS
    }
}
