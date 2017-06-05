package Aplicacio;

import Dades.*;
import Excepcions.FormatClauException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Aplicacio {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static boolean mostrarOpcio = true;


    public static void main(String[] args) throws IOException, FormatClauException {
        TADEstructura<String, Paraula> dades = null;
        long tempsi, tempsf;

        int cas = mostraOpcions();
        switch (cas) {
            case 1:
                dades = new TaulaHashEncadenadaIndirecta<>(10);
                break;
            case 2:
                dades = new Trie<>();
                break;
            case 3:
                dades = new JavaUtil<>();
                break;
        }

        String nomFitxer = nomFitxer();
        tempsi = System.nanoTime();
        cerca(dades, nomFitxer);
        tempsf = System.nanoTime();
        consultarTemps(tempsf, tempsi);

        String llegitTaclat;
        while (mostrarOpcio) {
            int cas2 = mostraOpcionsConsultes();
            switch (cas2) {
                case 1:
                    System.out.println(dades.llistaClaus().toString());
                    break;
                case 2:
                    System.out.print("Introdueix la paraula que vols afegir (no mes caracters): ");
                    llegitTaclat = reader.readLine();
                    String aux = llegitTaclat.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
                    Paraula p = new Paraula(aux);
                    if (!aux.equals("")) {
                        dades.afegir(aux, p);
                    }
                    System.out.println("");
                    System.out.println("Paraula: [" + aux + "] afegita a la llista de cerca.");
                    break;
                case 3:
                    System.out.print("Introdueix la paraula que vols esborrar (no mes caracters): ");
                    llegitTaclat = reader.readLine();
                    String paraulaEsborrar = llegitTaclat.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (dades.consultar(paraulaEsborrar) != null) {
                        dades.esborrar(paraulaEsborrar);
                        System.out.println("");
                        System.out.println("Paraula: [" + paraulaEsborrar + "] esborrada de la lliste de cerca.");
                    } else {
                        System.out.println("");
                        System.out.println("Paraula: [" + paraulaEsborrar + "] no esta a la llista de cerca.");
                    }
                    break;
                case 4:
                    tempsi = System.nanoTime();
                    dades = cerca(dades, nomFitxer);
                    tempsf = System.nanoTime();
                    consultarTemps(tempsf, tempsi);
                    break;
                case 5:
                    mostrarOpcio = false;
                    break;
            }
            if (mostrarOpcio) mostrarAltraConsulta();
        }
        System.out.println("");
        System.out.println("Fi de programa. ");

    }

    public static int mostraOpcions() {
        System.out.println("**************************************************" +
                "\n******* Tercera pràctica d’Estructures de dades  *\n" +
                "******* Índex alfabètic d’un text                *"
                + " \n**************************************************");
        System.out.println(" ");
        System.out.println("Quin tipus de estrucura de dades vols fer servir?");
        System.out.println("1. Estructura de dades Taula de dispersió");
        System.out.println("2. Estructura de dades basada en Arbres.");
        System.out.println("3. Una estructura de java Collection");
        System.out.println("");
        System.out.print("Introdueix una opcio: ");
        return (llegirEnter(3));
    }

    private static int llegirEnter(int max) {
        int aux = 0;
        boolean llegit = false;
        while (!llegit) {
            try {
                aux = Integer.parseInt(reader.readLine());
                if ((aux > max) || (aux < 1)) throw new NumberFormatException();
                llegit = true;
            } catch (NumberFormatException e) {
                System.out.println("Has d'indicar un valor numeric entre 1 i " + max);
            } catch (IOException e) {
                System.out.println("ERROR: I/O");
            }
        }
        return (aux);
    }

    private static String nomFitxer() {
        System.out.print("Introdueix el nom de fitxer: ");
        String nomFitxer = null;
        boolean sortir = false;
        while (!sortir) {
            try {
                nomFitxer = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error IOException");
            }
            String aux[] = nomFitxer.split("\\.");

            if (aux.length != 2) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.print("Format de fitxer es incorrecte, torna introdir: ");
                }
            } else sortir = true;
        }
        return nomFitxer;
    }

    private static FileInputStream comprovarArxiu(String nomFitxer) {
        FileInputStream fis = null;
        boolean sortir = false;
        while (!sortir) {
            try {
                fis = new FileInputStream(nomFitxer);
                sortir = true;
            } catch (FileNotFoundException e) {
                System.out.println("No es pot trobar arxiu: [" + nomFitxer + "]");
                nomFitxer = nomFitxer();
            }
        }
        return fis;
    }

    public static int mostraOpcionsConsultes() {
        System.out.println(" ");
        System.out.println("Quin tipus de consulta vols realitzar?");
        System.out.println("1. Mostrar totes les paraules que estan guardats a estructura");
        System.out.println("2. Afegir una paraula a cerca");
        System.out.println("3. Esborrar una paraula de la estructura");
        System.out.println("4. Tornar fer la cerca de paraules a text");
        System.out.println("");
        System.out.println("5. Sortir de programa");

        System.out.println("");
        System.out.print("Introdueix una opcio: ");
        return (llegirEnter(5));
    }

    public static void mostrarAltraConsulta() {
        int altraConsulta = altraConsulta();
        switch (altraConsulta) {
            case 1:
                mostrarOpcio = true;
                break;
            case 2:
                mostrarOpcio = false;
                break;
        }

    }

    public static int altraConsulta() {
        System.out.println(" ");
        System.out.println("Vols realitzar un altra consulta");
        System.out.println("1. Si, fer una altra consulta");
        System.out.println("2. No, surtir de programa");

        System.out.println("");
        System.out.print("Introdueix una opcio: ");
        return (llegirEnter(2));
    }

    public static TADEstructura cerca(TADEstructura dades, String nomFitxer) throws FormatClauException, IOException {
        FileInputStream fis = comprovarArxiu(nomFitxer);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                fis, StandardCharsets.UTF_8));

        String linea;
        String[] paraula;
        int numPlana = 0;
        int numLinea = 1;

        while ((linea = fileReader.readLine()) != null) {
            if (linea.trim().startsWith("<Plana numero=") && (linea.endsWith(">"))) {
                numLinea = 1;
                linea = linea.replaceAll("[^0-9]+", "");
                numPlana = Integer.parseInt(linea);
            } else {
                paraula = linea.replaceAll("'", " ").split("\\s+");
                for (int i = 0; i < paraula.length; i++) {
                    if (paraula[i].startsWith("$")) {
                        String aux = paraula[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
                        Paraula p = new Paraula(aux);
                        p.afegirPlana(numPlana, numLinea);
                        if (!aux.equals("")) {
                            dades.afegir(aux, p);
                        }

                    } else {
                        String filtrarParaula = paraula[i].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        Paraula aux = (Paraula) dades.consultar(filtrarParaula);
                        if (aux != null) {
                            aux.afegirPlana(numPlana, numLinea);
                        }
                    }
                }
                numLinea++;
            }
        }
        System.out.println("");
        System.out.println("Resultats de cerca: ");
        LlistaGenerica<String> llistaParaules = dades.llistaClaus();
        if (llistaParaules.getNum() != 0) {
            for (String s : llistaParaules) {
                System.out.println(dades.consultar(s).toString());
            }
        } else
            System.out.println("Has de afegir com a minim una paraula a cerca");
        return dades;
    }


    public static void consultarTemps(long tempsf, long tempsi){
        System.out.println(" ");
        System.out.println("Informacio de temps");
        System.out.println("Temps que ha tardat programa per fer els calculs " + (tempsf - tempsi) + " nanosegons");
        double seconds = (double) (tempsf - tempsi) / 1000000000.0;
        System.out.println("Temps que ha tardat: " + seconds + " seconds");
    }

}
