# Dagpenger NAV IT

**Beskrivelse**

Denne oppgaven tar inn en persons intekt de 3 siste kalenderårene og 
returnerer om de er kvalifisert til å få dagpenger, og i så fall hva
deres dagsats vil bli. Jeg brukte NAV sine definisjoner for grunnbeløp og
arbeidsdager per år, for å regne ut dagsatsen.

Først lagde jeg en konstuktør som tar inn 3 års intekt. Deretter lagde jeg 
metoden "qualified()" som sjekker om personen er kvalifisert eller ikke. Metoden 
returnerer false dersom personen ikke hadde noen intekt det siste kalenderåret. Dersom 
personen enten tjente til sammen over 3G de siste tre årene eller tjente over 1.5G det forrige kalenderåret 
skal metoden også returnere false. Dersom metoden returnerer false er personen
ikke kvalifisert, ellers returnerer den true og personen kan få dagpenger.

Videre lagde jeg metoden "dailyRate()" som beregner dagsatsen til personen.
Den finner dagpengegrunnlaget ved å ta den høyeste verdien av enten intekten det siste kalenderåret, 
eller gjennomsnittsintekten de siste 3 kalenderåerne. Den sjekker også om dagpengegrunnlaget
er større enn 6G og returnerer et unntak dersom det er det. Til slutt returnerer den 
dagsatsen rundet opp. 

Til slutt lagde jeg en toStrinng() metode for å skrive ut til brukeren om de er kvalifisert til 
å fa dagpenger og i så fall hva dagsatsen ligger på. 
