package dagpenger;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Dagpenger {


    private double income1, income2, income3;       // inntekter per kalenderårene

    private final int G = 111477;     // Grunnbeløp per 1.mai 2022
    private final int workdaysPerYear = 260;        // antall arbeidsdager i året per NAVs definisjon

    // konstuktør
    public Dagpenger(double income1, double income2, double income3) {
        this.income1 = income1;
        this.income2 = income2;
        this.income3 = income3;
    }

    // metode som sjekker om en person er kvalifisert til å få dagpenger
    public boolean qualified() {
        double totalIncome = income1+income2+income3;       // total inntekt de siste 3 årene
        boolean qualified = true;
        if (income3 == 0) {     // må ha hatt arbeidsinntekt minst det siste kalenderåret
            qualified = false;
        } else if (dailyRate() == 0) {
            qualified = false;
        } else if (totalIncome < 3*G && income3 < 1.5*G){
            qualified = false;
        }
        return qualified;
    }

    // metode for å beregne dagsatsen
    public int dailyRate() {
        double dailyBasis;
        int dailyRate;
        double avarageIncome = (income1+income2+income3)/3;       // gjennomsnittlig inntekt de siste 3 kalederårene

        if (income3 > avarageIncome) {
            dailyBasis= income3;
        } else {
            dailyBasis = avarageIncome;
        }

        if (dailyBasis > 6 * G) {
            dailyRate = 0;
        } else {
            dailyRate = (int) Math.ceil(dailyBasis/workdaysPerYear);   // dagsats = dagpengegrunnlag/arbeidsdager per år rundet opp
        }

        return dailyRate;   // dagsats = dagpengegrunnlag/arbeidsdager per år rundet opp
    }

    @Override
    public String toString() {
        String s;

        if (qualified()) {
            s = "Du er kvalifisert til å få dagpenger. \nDin dagsats er på: " + dailyRate() + "kr.";
        } else {
            s = "Du er ikke kvalifisert til å få dagpenger.";
        }
        return s;
    }
}
