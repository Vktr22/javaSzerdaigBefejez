package hu.szamalk;

import java.util.*;

public class Nev {

    //azert final, mert a neved nem valtozik ééééésésssss
    // a nev osztaly tiszta ertekobjektum(value object) vagyis: nincs onallo identitasa, csak a tartalom szamit, nem valtozik
    //de am valtozhat valaki neve, szoval:
    //a nev valojaban logikailagf nem valtozik - allapota nem kell, h valtozzon
    private final String vezeteknev;
    private final String keresztnev;
    private final List<String> tovabbiNevek;

    public Nev(String vezeteknev, String keresztnev, List<String> tovabbiNevek) {
        this.vezeteknev = vezeteknev.trim();
        this.keresztnev = keresztnev.trim();
        this.tovabbiNevek = List.copyOf(tovabbiNevek);
    }

    /** Teljes névből szétszedés */
    public static Nev fromTeljesNev(String teljes) {
        String norm = teljes.trim().replaceAll("\\s+", " ");
        String[] reszek = norm.split(" ");

        if (reszek.length < 2) {
            throw new IllegalArgumentException("Legalább vezetéknév + keresztnév kell!");
        }

        String vez = reszek[0];
        String ker = reszek[1];

        List<String> tov = (reszek.length > 2)
                ? Arrays.asList(Arrays.copyOfRange(reszek, 2, reszek.length))
                : List.of();

        return new Nev(vez, ker, tov);
    }

    /** Teljes név */
    public String teljesNev() {
        if (tovabbiNevek.isEmpty()) {
            return vezeteknev + " " + keresztnev;
        }
        return vezeteknev + " " + keresztnev + " " + String.join(" ", tovabbiNevek);
    }

    /** Monogram, pl: N. V. M. */
    public String monogram() {
        List<String> reszek = new ArrayList<>();
        reszek.add(vezeteknev);
        reszek.add(keresztnev);
        reszek.addAll(tovabbiNevek);

        List<String> mon = new ArrayList<>();

        for (String s : reszek) {
            int cp = s.codePointAt(0);
            String elso = new String(Character.toChars(cp)).toUpperCase(new Locale("hu", "HU"));
            mon.add(elso + ".");
        }

        return String.join(" ", mon);
    }

    /** Betű statisztika – ékezetbiztos */
    public Map<Character, Integer> betuStatisztika() {
        String norm = teljesNev().toLowerCase(new Locale("hu", "HU"));

        Map<Character, Integer> stat = new TreeMap<>();

        for (int i = 0; i < norm.length(); i++) {
            char ch = norm.charAt(i);
            if (Character.isLetter(ch)) {
                stat.put(ch, stat.getOrDefault(ch, 0) + 1);
            }
        }

        return stat;
    }

    /** Csere: keresztnév első 2 betűje ↔ vezetéknév első 2 betűje */
    public Nev csereElsoKetto() {
        String ujVez = csere(vezeteknev, keresztnev);
        String ujKer = csere(keresztnev, vezeteknev);
        return new Nev(ujVez, ujKer, tovabbiNevek);
    }

    private String csere(String a, String b) {
        int n = Math.min(2, Math.min(a.length(), b.length()));
        String aPref = a.substring(0, n);
        String bPref = b.substring(0, n);
        String aSuf = a.substring(n);
        return bPref + aSuf;
    }

    @Override
    public String toString() {
        return teljesNev();
    }
}