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

    public boolean qualified() {
        double totalIncome = income1+income2+income3;       // total inntekt de siste 3 årene
        if (income3 == 0) {     // må ha hatt arbeidsinntekt minst det siste kalenderåret
            return false;
        } else {
            // enten tjent over 3G siste 3 årene eller tjent over 1.5G forrige kalenderår
            return !(totalIncome < 3 * G) && !(income3 < 1.5 * G);
        }
    }

    public int dailyRate() {
        double dailyAllowanceBasis;
        double avarageIncome = (income1+income2+income3)/3;       // gjennomsnittlig inntekt de siste 3 kalederårene

        if (income3 > avarageIncome) {
            dailyAllowanceBasis= income3;
        } else {
            dailyAllowanceBasis = avarageIncome;
        }

        return (int) dailyAllowanceBasis/workdaysPerYear;   // dagsats = dagpengegrunnlag/arbeidsdager per år rundet opp
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

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Din årsintekt i 2019- ");
        int a = sc.nextInt();
        System.out.print("Din årsintekt i 2020- ");
        int b = sc.nextInt();
        System.out.print("Din årsintekt i 2021- ");
        int c = sc.nextInt();

        Dagpenger dagpenger = new Dagpenger(a, b, c);
        System.out.println(dagpenger);
    }
}
