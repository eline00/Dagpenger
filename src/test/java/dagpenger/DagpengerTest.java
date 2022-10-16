package dagpenger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DagpengerTest {

    Dagpenger dagpenger1 = new Dagpenger(400000, 450000, 500000);  // 500000 > gjennomsnitt --> dagpengegrunnlag = 500000
    Dagpenger dagpenger2 = new Dagpenger(400000, 500000, 0);  // ingen intekt siste kalenderår
    Dagpenger dagpenger3 = new Dagpenger(10000, 50000, 150000);  // under 1.5 G siste år, og under 3G til sammen
    Dagpenger dagpenger4 = new Dagpenger(100000, 50000, 168000);   // under 3G, men over 1.5G siste år
    Dagpenger dagpenger5 = new Dagpenger(400000, 250000, 150000);   // under 1.5G siste år, men over 3& til sammen
    Dagpenger dagpenger6 = new Dagpenger(0, 0, 1000000);  // dagpengegrunnlaget er over 6G

    @Test
    void qualified() {
        int antallFeil = 0;

        // tilfelle som skal gi riktig svar
        if (!dagpenger1.qualified()) {
            antallFeil++;
            System.out.println("Feil i metoden qualified()!");
        }

        // tilfelle hvor det intekten siste kalenderår er 0
        if (dagpenger2.qualified()) {
            antallFeil++;
            System.out.println("Feil 1 qualified(): " +
                    "skal returnere false dersom intekt siste kalenderår er 0");

        }

        // tilfelle hvor intekten både er under 1.5G siste år, og under 3G til sammen
        if (dagpenger3.qualified()) {
            antallFeil++;
            System.out.println("Feil 2 qualified(): skal returnere false dersom intekten " +
                    "det siste året en under 1.5G og intekten til sammen er under 3G");
        }

        // tilfelle hvor intekt er under 3G til sammen, men over 1.5G siste år
        if (!dagpenger4.qualified()) {
            antallFeil++;
            System.out.println("Feil 3 qualified(): skal returnere true dersom intekten siste " +
                    "året er over 1.5G, selv om intekten til sammen er under 3G");
        }

        // tilfelle hvor intekt siste år er under 1.5G, men til sammen over 3G
        if (!dagpenger5.qualified()) {
            antallFeil++;
            System.out.println("Feil 4 qualified(): skal returnere true dersom intekten til sammen" +
                    "er over 3G, selv om intekten siste året er under 1.5G");
        }

        assertEquals(0, antallFeil, "For mange feil i qualified()!");
    }

    @Test
    void dailyRate() {

        int antallFeil = 0;

        // tilfeller hvor dagpengegrunnlag er intekt siste året (dagpenger1),
        // eller gjennomsnittet av de tre siste årene (dagpenger2)
        if (dagpenger1.dailyRate() != 1924 || dagpenger2.dailyRate() != 1154) {
            antallFeil++;
            System.out.println("Feil i metoden dailyRate()!");
        }

        // tilfelle hvor dagpengegrunnlager er større enn 6G
        if (dagpenger6.dailyRate() != 0) {
            antallFeil++;
            System.out.println("Feil 1 dailyRate(): skal returnere 0 dersom dagpengegrunnlaget en over 6G");
        }

        assertEquals(0, antallFeil, "For mange feil i dailyRate()!");
    }
}