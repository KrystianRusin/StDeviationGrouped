package com.company;

/**
 * Created by Robert Hubert on 21/03/2018.
 */


// ---------*---------*---------*---------*
// The use of static imports is something that should be used carefully.
// I have only ever used this technique for the project wide constants.
//
import static com.company.ProjConstants.*;

public class StDeviation {

    /* ---------*---------*---------*---------*---------*---------*---------*---------*
    // Class Level Variables
    // ---------*---------*---------*---------*---------*---------*---------*--------- */

    // As discussed in class we are using a class populated with Project Constants to
    // ensure help manage the project data. This ensures that each class can access the
    // constant values, but in the event that a change is needed/required that this will
    // only need to be made in one location, or file.
    //
    // Notice: If I had not done the "static import com.company.ProjConstants.*;" then
    //         the use of the constant would have been written as:
    //
    //         private int[] Data = new int[ProjConstants.MAXDATA];
    //
    private int[] Data = new int[MAXDATA];

    // sets all values to invalid to allow some checking
    private int    sdItems       = INVALID;
    private int    sdMinRange    = INVALID_RANGE;
    private int    sdMaxRange    = INVALID_RANGE;
    private int    sdcalcMethod  = INVALID_CALC_METHOD;
    private int    sdNumOfGroups = INVALID;
    private int    sdMin         = INVALID;
    private int    sdMax         = INVALID;
    private int    sdGroupSize   = INVALID;
    private int    sdMidPoint  = INVALID;

    private double sdAve       = INVALID;
    private double sdVar       = INVALID;
    private double sdDev       = INVALID;


    public void setCalcMethod(int how2calculate){

        switch (how2calculate){
            case DISCRETE:{
                sdcalcMethod = how2calculate;
                break;
            }
            case FRQTABLE: {
                sdcalcMethod = how2calculate;
                break;
            }

            case GROUPED:{
                sdcalcMethod = how2calculate;
                break;
            }

            default:{
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method either UNIMPLEMENTED, or UNKNOWN");
                break;
            }
        }
    }

    public int getCalcMethod(){
        return sdcalcMethod;
    }


    public void setMin(int userMin) {
        switch (getCalcMethod()) {
            case 2: {
                if ((userMin >= MINDATA) && (userMin < MAXDATA)) {
                    sdMinRange = userMin;
                } else {
                    sdMinRange = INVALID_RANGE;
                }
            }
            case 3: {
                if ((userMin >= MINDATA) && (userMin < MAXDATA)) {
                    sdMinRange = userMin;
                } else {
                    sdMinRange = INVALID_RANGE;
                }

            }
        }
    }


    public int getMin(){
        return sdMinRange;
    }


    public void setMax(int userMax) {
        switch (sdcalcMethod) {
            case 2: {
                if ((userMax >= MINDATA) && (userMax < MAXDATA)) {
                    sdMaxRange = userMax;
                } else {
                    sdMaxRange = INVALID_RANGE;
                }
            }
            case 3: {
                if ((userMax >= MINDATA) && (userMax < MAXDATA)) {
                    sdMaxRange = userMax;
                } else {
                    sdMaxRange = INVALID_RANGE;
                }

            }
        }
    }


    public int getMax(){
        return sdMaxRange;
    }



    public void setNumberOfGroups(int groups){
            sdNumOfGroups = groups;

    }

    public int getNumberOfGroups(){
        return sdNumOfGroups;
    }

    public void addNewDataItem(int dataItem){

        if ((sdItems == INVALID)){
            sdItems = 0;
        }

        switch (getCalcMethod()){

            case DISCRETE:{

                Data[sdItems] = dataItem;
                sdItems++;
                break;
            }

            case FRQTABLE: {

                if ((getMin() != INVALID_RANGE) && (getMax() != INVALID_RANGE)){

                    if ((dataItem < getMin())|| (dataItem > getMax())){

                        System.out.printf("ERROR: RANGE VIOLATION - Data Value ( %5.0f ), User Values: Minimum ( %5.0f ), Maxium ( %5.0f )\n",
                                (double) dataItem, (double) getMin(), (double) getMax());

                    }
                    else if ((dataItem < MINDATA) || (dataItem >= MAXDATA)){

                        System.out.printf("ERROR: RANGE VIOLATION - Data Value ( %5.0f ), System Values: DATAMIN ( %5.0f ), DATAMAX ( %5.0f )\n",
                                (double) dataItem, (double) MINDATA, (double) MAXDATA - 1);

                    }
                    else {

                        Data[dataItem] = Data[dataItem] + 1;
                        sdItems++;

                    }
                }
                else {
                    System.out.printf("ERROR: RANGE VIOLATION - Range values not set\n");
                }
                break;
            }

            case GROUPED:{

                if ((getMin() != INVALID_RANGE) && (getMax() != INVALID_RANGE)){

                    if ((dataItem < getMin())|| (dataItem > getMax())){

                        System.out.printf("ERROR: RANGE VIOLATION - Data Value ( %5.0f ), User Values: Minimum ( %5.0f ), Maxium ( %5.0f )\n",
                                (double) dataItem, (double) getMin(), (double) getMax());

                    }
                    else if ((dataItem < MINDATA) || (dataItem >= MAXDATA)){

                        System.out.printf("ERROR: RANGE VIOLATION - Data Value ( %5.0f ), System Values: DATAMIN ( %5.0f ), DATAMAX ( %5.0f )\n",
                                (double) dataItem, (double) MINDATA, (double) MAXDATA - 1);

                    }
                    else {

                        Data[dataItem] = Data[dataItem] + 1;
                        sdItems++;

                    }
                }
                else {
                    System.out.printf("ERROR: RANGE VIOLATION - Range values not set\n");
                }

            }
            break;
            default:{
                sdItems    = INVALID;
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method either UNIMPLEMENTED, or UNKNOWN");
                break;
            }
        }

    }


    public int getNumberOfDataItems(){
        return sdItems;
    }


    public double calcAverage(){

        double total = 0;

        switch (getCalcMethod()){
            case DISCRETE:{

                if (sdItems != INVALID){

                    // Add all data values together (recall sdItems is the number of data items in the
                    // Data storage array
                    for (int i = 0; i < sdItems; i++){
                        total = total +  Data[i];
                    }

                    // calculate the average, the "(double)" below instructs the computer to treat
                    // the integer value of "sdItems" as a real number
                    sdAve = total / (double) getNumberOfDataItems();
                }
                else {
                    // Pre-Conditions have not been met
                    sdAve = INVALID;
                }

                break;
            }
            case FRQTABLE: {

                if (sdItems != INVALID){

                    // The Maximum must be greater that the Minimum value provided
                    if (getMax() > getMin()){

                        // Loop over all values in range and add to the total
                        // the value, "i", multiplied by the frequence "Data[i]"
                        for (int i = getMin(); i <= getMax(); i++){
                            total = total + (i * Data[i]);
                        }

                        // calculate the average, the "(double)" below instructs the computer to treat
                        // the integer value of "sdItems" as a real number
                        sdAve = total / (double) getNumberOfDataItems();

                    }
                    else {
                        sdMaxRange = INVALID_RANGE;
                        sdMinRange = INVALID_RANGE;
                        sdAve      = INVALID;
                    }
                }
                else {
                    // Pre-Conditions have not been met
                    sdAve = INVALID;
                }

                break;
            }

            case GROUPED:{
                double storage = INVALID;
                int groupNum = getNumberOfGroups();

                int range = (getMax() - getMin())+1;
                if (range%getNumberOfGroups() != 0){ //Checks to see if the number of data items can be easily divided
                    //by the requested number of groups, if it is not then it prints out an error.
                    //If it is easily divisible, then proceed with calculating average.
                    System.out.println("ERROR: Number of data items is not easily divisible by the number of groups");
                }
                else {
                    int interval = range / groupNum;
                    System.out.println("NUMBER OF GROUPS:" + groupNum);
                    System.out.println("RANGE:" + range);
                    System.out.println("INTERVAL:" + interval);
                    sdMin = getMin();
                    sdMax = sdMin + interval;

                    for (int i = interval; i < groupNum; i = i + interval) {
                        System.out.println("MIN:" + sdMin);
                        sdMax = (sdMin + interval) - 1;
                        System.out.println("MAX:" + sdMax);
                        sdMidPoint = (sdMax + sdMin) / 2;
                        System.out.println("MIDPOINT:" + sdMidPoint);

                        // Loop over all values in range and add to the total
                        // the value, "i", multiplied by the frequency "Data[i]"
                        for (int j = getMin(); j <= getMax(); j++) {
                            total = total + (j * Data[j]);
                        }
                        storage += total * sdMidPoint; //storage acts as the sum of midpoint*frequency
                        sdMin = sdMax;
                        System.out.println("MIN:" + sdMin);
                        sdMax = sdMin + interval;
                        System.out.println("MAX:" + sdMax);

                        sdAve = storage / getNumberOfGroups();//divides storage by number of groups to get the mean

                    }
                }

                }
            break;

            default:{
                sdAve = INVALID;
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method UNKNOWN");
                break;
            }
        }

        // returns the calculated average
        return sdAve;
    }


    public double calcVariance(){

        double total = 0;
        double frequency = 0;
        double difference = 0;
        double diffSquared = 0;

        switch (getCalcMethod()){
            case DISCRETE:{
                // Checks that data entry, and average have been done
                //
                if ((sdItems != INVALID) || (sdAve != INVALID)){

                    // Loop over all data and calculate variance
                    //
                    for (int i = 0; i < sdItems; i++){

                        difference = (Data[i] - sdAve);          // calculates the difference between value and mean
                        diffSquared = Math.pow(difference,2);    // squares the difference
                        total = total + diffSquared;             // adds square of difference into total
                    }

                    sdVar = total / (double) sdItems;            // divides total by the number of observations

                }
                else {
                    // Pre-Conditions have not been met
                    sdVar = INVALID;
                }

                break;
            }

            case FRQTABLE: {
                // Checks that data entry, and average have been done
                //
                if ((sdItems != INVALID) || (sdAve != INVALID)) {

                    // Loop over all data in the range to calculate variance
                    //
                    for (int i = getMin(); i < getMax(); i++){

                        frequency = Data[i];                         // set the frequency for this value "i"
                        difference = (i - sdAve);                    // calculates difference between "i" and mean
                        diffSquared = Math.pow(difference,2);        // squares the difference
                        total = total + (diffSquared * frequency);   // adds difference squared * frequency into total
                    }

                    sdVar = total / (double) sdItems;                 // divides total by the number of observations

                }
                else {
                    // Pre-Conditions have not been met
                    sdVar = INVALID;
                }
                break;
            }

            case GROUPED:{
                if ((sdItems != INVALID) || (sdAve != INVALID)) {

                    // Loop over all data in the range to calculate variance
                    //
                    for (int i = getMin(); i < getMax(); i++){

                        frequency = Data[i];                         // set the frequency for this value "i"
                        difference = (i - sdAve);                    // calculates difference between "i" and mean
                        diffSquared = Math.pow(difference,2);        // squares the difference
                        total = total + (diffSquared * frequency);   // adds difference squared * frequency into total
                    }

                    sdVar = total / (double) sdItems;                 // divides total by the number of observations

                }
            }
            break;
            default:{
                sdVar = INVALID;
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method UNKNOWN");
                break;
            }
        }

        return sdVar;
    }


    public double calcStandardDeviation(){

        // Checks that data entry, average, and variance have been done
        if ((sdItems != INVALID) || (sdAve != INVALID) || (sdVar != INVALID)) {
            sdDev = Math.sqrt(sdVar);
        }
        else {
            // Pre-Conditions have not been met
            sdDev = INVALID;
        }

        return sdDev;
    }

} // end of class