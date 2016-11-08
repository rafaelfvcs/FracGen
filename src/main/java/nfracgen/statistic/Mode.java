package nfracgen.statistic;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Mode {
    /**
     * Get the number repeated more in the list     
     * Returns Double.NaN if there is not repeated values
     * 
     * @param array
     * @return
     */
    public static Double getMode(ArrayList<Double> array) {
        Double result = Double.NaN;
        ObservableList<Double> ol = FXCollections.observableArrayList(array);
        ol.sorted();
        int counter = 0;
        int higherCounter = 0;
        for (int i = 1; i < ol.size(); i++) {
            if (ol.get(i - 1).equals(ol.get(i))) {
                counter++;
            } else {
                if (counter > higherCounter) {
                    result = ol.get(i - 1);
                    higherCounter = counter;
                }
                counter = 0;
            }
        }
        return result;
    }

}
