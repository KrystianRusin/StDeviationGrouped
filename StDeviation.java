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

    // ******************************************************************************
    // ******************************************************************************

    // THERE ARE SEVERAL METHODS THAT WILL HAVE TO CHANGE IN THIS CLASS TO ALLOW THE
    // CALCULATION OF STANDARD DEVIATION USING THE FREQUENCY TABLE METHOD AS WELL.
    // PLEASE CONSIDER THE CHANGES THAT YOU WILL HAVE TO MAKE TO:
    //   - addNewDataItem (precondition -  calculation method is set)
    //   - calcAverage    (precondition -  data added, & calculation method is set)
    //   - calcVariance   (precondition -  average calculated, data added, & calculation method is set)

    // --------------------------------------------------
    // The following method (procedure) will  set calculation method to be used.
    // This will impact the path through the code in the addNewDataItem, calcAverage,
    // and calcVariance methods
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - none
    //      Post-Conditions:
    //          - calcMethod set to INVALID_CALC_METHOD, DISCRETE, FRQTABLE, or GROUPED
    //
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

    // --------------------------------------------------
    // The following method (function) will return the current Calculation Method
    // value.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - DISCRETE, FRQTABLE, or INVALID_CALC_METHOD
    //
    public int getCalcMethod(){
        return sdcalcMethod;
    }

    // --------------------------------------------------
    // The following method (procedure) will return set current Minimum value for
    // use with the Frequency Method of Calculation.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - none
    //      Post-Conditions:
    //          - sdMinRange set to INVALID_RANGE, or
    //          - an Integer value between MINDATA & MAXDATA - 1
    //
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

    // --------------------------------------------------
    // The following method (function) will return the current Minimum value for
    // use with the Frequency Method of Calculation.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - INVALID_RANGE,
    //          - Integer value between MINDATA & MAXDATA
    //
    public int getMin(){
        return sdMinRange;
    }


    // --------------------------------------------------
    // The following method (procedure) will return set current Maximum value for
    // use with the Frequency Method of Calculation.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - none
    //      Post-Conditions:
    //          - sdMaxRange set to INVALID_RANGE, or
    //          - an Integer value between MINDATA & MAXDATA - 1
    //
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

    // --------------------------------------------------
    // The following method (function) will return the current Maximum value for
    // use with the Frequency Method of Calculation.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - INVALID_RANGE,
    //          - Integer value between MINDATA & MAXDATA
    //      Post-Conditions:
    //          - none
    //
    public int getMax(){
        return sdMaxRange;
    }


    // --------------------------------------------------
    // The following method (procedure) will set the current value for
    // the number of groups the user wishes to use with the Frequency
    // Method of Calculation.
    //
    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - none,
    //      Post-Conditions:
    //          - stores an Integer value between 1, and userMax - userMin
    //
    public void setNumberOfGroups(int groups){

        if ((groups > MINDATA) && (groups <= (sdMaxRange  - sdMinRange))){
            sdNumOfGroups = groups;
        }
        else {
            sdNumOfGroups = INVALID;
        }
    }

    public int getNumberOfGroups(){
        return sdNumOfGroups;
    }

    // ******************************************************************************
    // ******************************************************************************

    // --------------------------------------------------
    // The following method (procedure) will take a new data item (a parameter)
    // and add it into the 1 Dimensional Array of data values to be used later.
    //
    //      Pre-Conditions:
    //          - Calculation Method, and Range values must have been set
    //      Return Value:
    //          - none
    //      Post-Condition
    //          - Data Item is added into Data Storage
    //          - sdItems incremented
    //          - ERROR
    //              - sdItems set to INVALID, and
    //              - calcMethod is set to INVALID_CALC_METHOD
    //
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
            default:{
                sdItems    = INVALID;
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method either UNIMPLEMENTED, or UNKNOWN");
                break;
            }
        }

    }

    // --------------------------------------------------
    // The following method (function) will return the total number of data
    // items currently stored

    //      Pre-Conditions:
    //          - none
    //      Return Value:
    //          - the number of data items
    //      Post-Condition
    //          - none
    //
    public int getNumberOfDataItems(){
        return sdItems;
    }

    // --------------------------------------------------
    // The following method (procedure) calculates the average based on
    // the calculation method selected
    //
    //      Pre-Conditions:
    //          - Calculation Method, and Range values must have been set
    //          - at least one data has been added
    //      Return Value:
    //          - none
    //      Post-Condition
    //          - NO ERROR sdAve is set to the average
    //          - ERROR
    //              Data Items:
    //                 - sdAve = INVALID
    //              Data Range:
    //                 - sdAve = INVALID, and sdMaxRange & sdMaxRange = INVALID_RANGE
    //              Calculation Method:
    //                 - sdAve = INVALID, and calcMethod = INVALID_CALC_METHOD
    //
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
                System.out.println(getNumberOfDataItems());

                if (getNumberOfDataItems()%getNumberOfGroups() != 0){ //Checks to see if the number of data items can be easily divided
                                                                      //by the requested number of groups, if it is not then it prints out an error.
                                                                      //If it is easily divisible, then proceed with calculating average.
                    System.out.println("ERROR: Number of data items is not easily divisible by the number of groups");
                }
                else{
                    sdGroupSize = getNumberOfDataItems()/getNumberOfGroups(); //Finds group size by dividing number of data items by number of groups.
                sdMin = getMin(); //sets minimum for group
                sdMax = getMin() + sdGroupSize; //sets maximum for group
                    for (int j = 0;j<getNumberOfGroups();j++) { //For loop that repeats itself for the amount of groups there are

                        sdMidPoint = (sdMin+sdMax)/2; //finds mid point between min and max

                        if (sdMax > sdMin) {

                            // Loop over all values in range and add to the total
                            // the value, "i", multiplied by the frequence "Data[i]"
                            for (int i = sdMin; i <= sdMax; i++) {
                                total = total + (i * Data[i]);
                            }
                            storage += total*sdMidPoint; //storages acts as the sum of midpoint*frequency
                        }
                    }
                    sdAve = storage/getNumberOfGroups();//divides storage by number of groups to get the mean


                }
            }

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

    // --------------------------------------------------
    // The following method (function) returns a double precision value which is the Variance of all
    // of the data values. If there is no data, or if the average has not been calculated
    // then it returns INVALID
    //
    //      Pre-Conditions:
    //          - Calculation Method, and Range values must have been set
    //          - at least one data has been added
    //          - the average must have been calculated
    //
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

            default:{
                sdVar = INVALID;
                sdcalcMethod = INVALID_CALC_METHOD;
                System.out.println("ERROR: Standard Deviation Calculation Method UNKNOWN");
                break;
            }
        }

        return sdVar;
    }

    // --------------------------------------------------
    // The following method (function) returns a double precision value which is the Standard
    // Deviation of all of the data values. If there is no data, no average, or if
    // the variance has not been calculated then it returns INVALID
    //
    //      Pre-Conditions:
    //          - at least one data has been added
    //          - the average must have been calculated
    //          - the variance must have been calculated
    //
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
