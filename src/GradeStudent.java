/*
    This program was developed to create a simple application called GradeStudent.
    The application starts with a message explaining the task it can do.
    User interact with prompts appear in the console to use the application
    At the end, the application will display overall percentage and the GPA
*/

// import Scanner & ArrayList
import java.util.Scanner;
import java.util.ArrayList;

public class GradeStudent {
    // create new Scanner object
    public static final Scanner sc = new Scanner(System.in);

    // declare constant and global variables to use in the program
    public static final int totalWeight = 100;
    public static int midTermWeight;
    public static int finalWeight;
    public static int homeworkWeight;

    // main method to control the flow of the program
    public static void main(String[] args) {
        // call report method
        report();
    }

    // begin method to display opening message
    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade");
    }

    // midTerm method to receive and compute information regarding midTerm score
    public static double midTerm() {

        // declare variables
        int midTermScore;
        int midTermScoreShift;
        int midTermShiftAmount;
        int midTermTotal = 0;
        double midTermPoints;

        // receive information about midterm score
        System.out.println("Midterm: ");
        System.out.print("Weight (0-100)? ");
        midTermWeight = sc.nextInt();
        System.out.print("Score earned? ");
        midTermScore = sc.nextInt();

        // handle shifted score
        System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
        midTermScoreShift = sc.nextInt();
        if (midTermScoreShift == 1) {
            System.out.print("Shift amount? ");
            midTermShiftAmount = sc.nextInt();
            midTermTotal = midTermScore + midTermShiftAmount;
            // ensure total midterm score less than or equal to 100
            if (midTermTotal > 100) {
                midTermTotal = 100;
            }
        } else if (midTermScoreShift == 2) {
            midTermTotal = midTermScore;
        }

        //calculate midterm points and round it to 1 decimal
        midTermPoints = (double) midTermTotal / 100 * midTermWeight;
        midTermPoints = Math.round(midTermPoints * 10) / 10.0;

        // display results of midterm
        System.out.println("Total points = " + midTermTotal + " / 100");
        System.out.println("Weighted score = " + midTermPoints + " / " + midTermWeight);

        // return midterm points for total points calculation
        return midTermPoints;
    }

    // finalTerm method to receive and compute information regarding final score
    public static double finalTerm() {

        // declare variables
        int finalScore;
        int finalScoreShift;
        int finalShiftAmount;
        int finalTotal = 0;
        double finalPoints;

        // receive information about final score
        System.out.println("Final: ");
        System.out.print("Weight (0-100)? ");
        finalWeight = sc.nextInt();
        System.out.print("Score earned? ");
        finalScore = sc.nextInt();

        //handle shifted score
        System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
        finalScoreShift = sc.nextInt();
        if (finalScoreShift == 1) {
            System.out.print("Shift amount? ");
            finalShiftAmount = sc.nextInt();
            finalTotal = finalScore + finalShiftAmount;
            if (finalTotal > 100) {
                finalTotal = 100;
            }
        } else if (finalScoreShift == 2) {
            finalTotal = finalScore;
        }

        // calculate final points and round it to 1 decimal
        finalPoints = (double) finalTotal / 100 * finalWeight;
        finalPoints = Math.round(finalPoints * 10) / 10.0;

        // display results of final
        System.out.println("Total points = " + finalTotal + " / 100");
        System.out.println("Weighted score = " + finalPoints + " / " + finalWeight);

        // return final points for total points calculation
        return finalPoints;
    }

    // homework method to receive and compute information regarding assignment
    public static double homework() {

        // declare variables
        int homeworkNumber;
        ArrayList <Integer> score = new ArrayList<Integer>();
        int homeworkScore = 0;
        int homeworkMax = 0;
        int sections;
        int sectionPoints;
        double homeworkPoint;

        // receive information about assignments
        System.out.println("Homework:");
        System.out.print("Weight (0-100)? ");

        // make sure total weight is equal 100
        homeworkWeight = totalWeight - midTermWeight - finalWeight;
        System.out.println(homeworkWeight);
        System.out.print("Number of assignments? ");
        homeworkNumber = sc.nextInt();

        // receive score and max for each assignment
        for (int i = 1; i <= homeworkNumber; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            score.add(sc.nextInt());
            score.add(sc.nextInt());
            sc.nextLine();
        }

        // calculate homework score achieved
        for (int j = 0; j < score.size(); j+=2) {
            homeworkScore += score.get(j);
        }

        // calculate maximum homework score
        for (int k = 1; k < score.size(); k+=2) {
            homeworkMax += score.get(k);
        }

        // calculate section points
        System.out.print("How many sections did you attend? ");
        sections = sc.nextInt();
        sectionPoints = sections * 5;
        if (sectionPoints > 30) {
            sectionPoints = 30;
        }

        // update homework score and maximum score
        homeworkScore += sectionPoints;
        homeworkMax += 30;

        // set limits to assignment score
        if (homeworkScore > 150) {
            homeworkScore = 150;
        }

        if (homeworkMax > 150) {
            homeworkMax = 150;
        }

        // calculate homework points and round it to 1 decimal
        homeworkPoint = (double) homeworkScore / homeworkMax * homeworkWeight;
        homeworkPoint = Math.round(homeworkPoint * 10) / 10.0;

        // display results of homework
        System.out.println("Section points = " + sectionPoints + " / 30");
        System.out.println("Total points = " + homeworkScore + " / " + homeworkMax);
        System.out.println("Weighted score = " + homeworkPoint + " / " + homeworkWeight);

        // return homework points for total points calculation
        return homeworkPoint;
    }

    public static void report() {
        // initiate begin method
        begin();
        System.out.println("");

        // initiate midTerm, finalTerm and homework methods and assignment return values to new variables
        double midTermPercentage = midTerm();
        System.out.println("");
        double finalTermPercentage = finalTerm();
        System.out.println("");
        double homeworkPercentage = homework();
        System.out.println("");

        // calculate total percentage
        double percentage = midTermPercentage + finalTermPercentage + homeworkPercentage;

        // calculate GPA
        double GPA;
        if (percentage >= 85) {
            GPA = 3.0;
        } else if (percentage >= 75) {
            GPA = 2.0;
        } else if (percentage >= 60) {
            GPA = 1.0;
        } else {
            GPA = 0.0;
        }

        // display overall results
        System.out.println("Overall percentage = " + percentage);
        System.out.println("Your grade will be at least: " + GPA);

        // display custom message corresponding to GPA
        if (GPA == 3.0) {
            System.out.println("High distinction");
        } else if (GPA == 2.0) {
            System.out.println("Distinction");
        } else if (GPA == 1.0) {
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }
}

