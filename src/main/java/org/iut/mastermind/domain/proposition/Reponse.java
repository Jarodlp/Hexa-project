package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        resultat.clear();
        for (int i = 0; i < essai.length(); i++) {
            this.position = i;
            resultat.add(evaluationCaractere(essai.charAt(i)));
        }
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(Lettre.PLACEE::equals);
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluationCaractere(char carCourant) {
        return estPlace(carCourant) ? Lettre.PLACEE :
                estPresent(carCourant) ? Lettre.NON_PLACEE :
                        Lettre.INCORRECTE;
    }

    // le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return this.motSecret.chars().anyMatch(c -> c == carCourant);
    }

    // le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        return this.motSecret.charAt(this.position) == carCourant;
    }
}
