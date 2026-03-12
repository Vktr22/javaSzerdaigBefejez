package hu.szamalk;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            // 1) Név beolvasása fájlból
            String sor = FileBeolvas.elsoSor("nev.txt");
            Nev nev = Nev.fromTeljesNev(sor);

            System.out.println("Eredeti név: " + nev.teljesNev());

            // 2) Monogram
            System.out.println("Monogram: " + nev.monogram());

            // 3) Betű statisztika
            System.out.println("Betű statisztika:");
            for (Map.Entry<Character, Integer> e : nev.betuStatisztika().entrySet()) {
                System.out.println("  " + e.getKey() + " = " + e.getValue());
            }

            // 4) Csere művelet
            Nev csere = nev.csereElsoKetto();
            System.out.println("Csere után: " + csere.teljesNev());

        } catch (IOException e) {
            System.err.println("Hiba a fájl beolvasásakor: " + e.getMessage());
        }
    }
}