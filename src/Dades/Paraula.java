package Dades;

public class Paraula {

    private String id;
    private LlistaGenerica<Plana> llistaPlanes;


    public Paraula(String id) {
        this.id = id;
        llistaPlanes = new LlistaGenerica<>(4);
    }

    public void afegirPlana(int numPlana, int numLinea) {
        Plana aux = new Plana(numPlana, numLinea);
        boolean contains = false;
        for (int i = 0; i < llistaPlanes.getNum(); i++) {
            if (llistaPlanes.consultarIessim(i).compareTo(aux) == 0) contains = true;
        }
        if (!contains) llistaPlanes.afegirElement(aux);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < llistaPlanes.getNum(); i++) {
            if (i != llistaPlanes.getNum() -1) {
                sb.append(llistaPlanes.consultarIessim(i).
                        getNumPlana()).append(":").append(llistaPlanes.consultarIessim(i).getNumLinea()).append(", ");
            } else {
                sb.append(llistaPlanes.consultarIessim(i).
                        getNumPlana()).append(":").append(llistaPlanes.consultarIessim(i).getNumLinea());
            }

        }
        return id + " " + sb.toString();
    }
}
