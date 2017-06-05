package Dades;

public class Plana implements Comparable<Plana> {

    private int numPlana;
    private int numLinea;

    public Plana(int numPlana, int numLinea) {
        this.numPlana = numPlana;
        this.numLinea = numLinea;
    }

    public int getNumPlana() {
        return numPlana;
    }

    public int getNumLinea() {
        return numLinea;
    }

    @Override
    public int compareTo(Plana o) {
        if (this.getNumPlana() > o.getNumPlana()) return 1;
        else if (this.getNumPlana() < o.getNumPlana()) return -1;
        else {
            if (this.getNumLinea() > o.getNumLinea()) return 1;
            else if (this.getNumLinea() < o.getNumLinea()) return -1;
            else return 0;
        }
    }
}
