package com.structurax.design.ai;

import com.structurax.entity.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesignAIEngine {

    public List<DesignSuggestion> analyze(

            DesignProject project,

            List<Room> rooms,

            List<Door> doors,

            List<Window> windows,

            List<Furniture> furniture

    ) {

        List<DesignSuggestion> suggestions =
                new ArrayList<>();

        analyzeBedrooms(project, suggestions);

        analyzeVentilation(project,
                windows,
                suggestions);

        analyzeFurniture(
                furniture,
                suggestions);

        analyzeBathrooms(
                project,
                suggestions);

        analyzeKitchen(
                project,
                suggestions);

        return suggestions;

    }

    private void analyzeBedrooms(

            DesignProject project,

            List<DesignSuggestion> suggestions) {

        if(project.getBedrooms() >= 4){

            suggestions.add(

                    new DesignSuggestion(

                            "Bedroom Advice",

                            "Consider attaching bathrooms to master bedrooms.",

                            "INFO"

                    )

            );

        }

    }

    private void analyzeBathrooms(

            DesignProject project,

            List<DesignSuggestion> suggestions){

        if(project.getBathrooms()==1 &&
                project.getBedrooms()>=3){

            suggestions.add(

                    new DesignSuggestion(

                            "Bathroom",

                            "One bathroom may be insufficient.",

                            "WARNING"

                    )

            );

        }

    }

    private void analyzeKitchen(

            DesignProject project,

            List<DesignSuggestion> suggestions){

        if(project.getKitchens()==1 &&
                project.getFloors()>2){

            suggestions.add(

                    new DesignSuggestion(

                            "Kitchen",

                            "Consider pantry or utility room.",

                            "INFO"

                    )

            );

        }

    }

    private void analyzeVentilation(

            DesignProject project,

            List<Window> windows,

            List<DesignSuggestion> suggestions){

        if(windows.size()<project.getBedrooms()){

            suggestions.add(

                    new DesignSuggestion(

                            "Ventilation",

                            "More windows recommended for better airflow.",

                            "WARNING"

                    )

            );

        }

    }

    private void analyzeFurniture(

            List<Furniture> furniture,

            List<DesignSuggestion> suggestions){

        if(furniture.size()<3){

            suggestions.add(

                    new DesignSuggestion(

                            "Furniture",

                            "House contains very little furniture.",

                            "INFO"

                    )

            );

        }

    }

}