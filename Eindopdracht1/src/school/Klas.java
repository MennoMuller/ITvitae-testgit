package school;

import personen.Leerling;

public class Klas {
    public Leerling[] getLeerlingLijst() {
        return leerlingLijst;
    }

    public void setLeerlingLijst(Leerling[] leerlingLijst) {
        this.leerlingLijst = leerlingLijst;
    }

    private Leerling[] leerlingLijst;   //lijst van alle leerlingen in de klas

    public void printLeerlingen(){ //print de namen van alle leerlingen in de klas
        for (Leerling leerling:
             leerlingLijst) {
            System.out.println(leerling.getName());
        }
    }
    public String klasNaam;
    private void removeStudent(int index) {
        /*
        Will eventually have a function that removes a student from the class (not implemented)
         */
    }


}
