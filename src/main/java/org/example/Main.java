package org.example;

import java.io.*;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;
import java.nio.file.Path;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws IOException {


        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "funkos.csv"))) {
            List<List<String>> funkosString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();
            //funkos.forEach(System.out::println);
            List<Funko> funkosFunkos = new ArrayList<>();




/*
            for (int i = 1; i < funkosString.size(); i++) {
                List<String> unFunko = funkosString.get(i);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


                Funko ejemplo = new Funko();
                ejemplo.setCod(unFunko.get(0));
                ejemplo.setNombre(unFunko.get(1));
                ejemplo.setModelo(unFunko.get(2));
                ejemplo.setPrecio(Double.parseDouble(unFunko.get(3)));
                ejemplo.setFecha_lanzamiento(LocalDate.parse(unFunko.get(4), formatter));

                funkosFunkos.add(ejemplo);
            }

*/
            funkosFunkos.forEach(System.out::println);
/*
            //Funko más caro
       double funkoCaro= funkosFunkos.stream()
                .map(Funko::getPrecio)
                .max(Double::compareTo)
               .orElse(null);


            funkosFunkos.stream()
                    .filter(f->f.getPrecio()==funkoCaro)
                    .map(Funko::getNombre)
                    .forEach(System.out::println);


            //Media de precio de Funkos

            double mediaFunkos = funkosFunkos.stream()
                    .mapToDouble(Funko::getPrecio)
                    .average()
                    .orElse(0);
            System.out.println("La media de precio de funkos es : "+mediaFunkos);

            //Funkos agrupados por modelos

            System.out.println("Funkos de marvel: ");
            funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "MARVEL"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("Funkos de Disney: ");
            funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "DISNEY"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("Funkos de ANIME: ");
            funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "ANIME"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            System.out.println("Funkos de otro tipo: ");
            funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "OTROS"))
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

            //Número de funkos por modelos

            System.out.println("EL numero de funkos de marvel es: ");
            long numeroFunkosMarvel= funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "MARVEL"))
                    .count();
            System.out.println(numeroFunkosMarvel);

            System.out.println("EL numero de funkos de diseny es: ");
            long numeroFunkosDisney= funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "DISNEY"))
                    .count();
            System.out.println(numeroFunkosDisney);

            System.out.println("EL numero de funkos de anime es: ");
            long numeroFunkosAnime= funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "ANIME"))
                    .count();
            System.out.println(numeroFunkosAnime);

            System.out.println("EL numero de otors funkos  es: ");
            long numeroFunkosOtros= funkosFunkos.stream()
                    .filter(f-> Objects.equals(f.getModelo(), "OTROS"))
                    .count();
            System.out.println(numeroFunkosOtros);

                // funkos del 2023

            System.out.println("FUNKO DEL 2023");
            funkosFunkos.stream()
                    .filter(funko -> funko.getFecha_lanzamiento().getYear() == 2023)
                    .map(Funko::getNombre)
                    .forEach(System.out::println);

*/


            Path rutaTxt = Path.of(".", "src", "main", "resources", "backup.txt");
            String miRutaBackup = "/home/daw2/IdeaProjects/Tema 3-TécnicadeAccesoaDatos /Funkos/src/main/resources/backup.dat";


            // Realizar el backup de los objetos
            backup(funkosFunkos, miRutaBackup);

            // Restaurar los objetos desde el archivo



        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }


//Método para realizar la serialización de objetos Funko a un archivo .dat
    public static void backup(List<Funko> funkos, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(funkos);
            System.out.println("Backup realizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para realizar la deserialización de objetos Funko desde un archivo .dat
    public static void restore(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Funko[] funkos = (Funko[]) ois.readObject();

            // Mostrar los objetos leídos
            System.out.println("Objetos leídos desde el archivo:");
            for (Funko funko : funkos) {
                System.out.println(funko.toString());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

// hago el backup como si fuera csv para que el restore sea más sencillo TXT
    /*public static void backup(List lista, String ruta) throws IOException {

        // Borra el contenido del archivo
        Files.write(Paths.get(ruta), new byte[0]); // Escribe un array de bytes vacío para limpiar el archivo


        for (int i = 0; i < lista.size(); i++) {
            try {
                Files.writeString(Paths.get(ruta), lista.get(i).toString(),  StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
*/
    //TXT
    /*public static List restore(String ruta) throws IOException {

        Stream<String> contenidoFichero = Files.lines(Paths.get(ruta));

        List<List<String>> funkosTxtString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();

        List<Funko> funkosTxtFunkos = new ArrayList<>();

        for (int i = 0; i < funkosTxtString.size(); i++) {
            List<String> unFunko = funkosTxtString.get(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Funko ejemplo = new Funko();
            ejemplo.setCod(unFunko.get(0));
            ejemplo.setNombre(unFunko.get(1));
            ejemplo.setModelo(unFunko.get(2));
            ejemplo.setPrecio(Double.parseDouble(unFunko.get(3)));
            ejemplo.setFecha_lanzamiento(LocalDate.parse(unFunko.get(4), formatter));

            funkosTxtFunkos.add(ejemplo);
        }

        return funkosTxtFunkos;


    }

     */

    public static Double masCaro(List<Funko> lista) {
        // SE PODRÍA HACER MEJOR
        Double precioMax = lista.stream()
                .map(Funko::getPrecio)
                .max(Double::compareTo)
                .orElse(null);

        // Funko más caro: NOMBRE
        System.out.println("FUNKO MÁS CARO:");
        lista.stream()
                .filter(funko -> funko.getPrecio() == precioMax)
                .map(Funko::getNombre)
                .forEach(System.out::println);

        return precioMax;
    }

    public static Double precioMedio(List<Funko> lista) {
        // Para conseguir dos decimales y € al final
        Locale locale = new Locale("es", "ES"); // Definir la localidad para español
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale); // Obtener un formateador de moneda para la localidad española
        formatoMoneda.setMaximumFractionDigits(2); // Establecer el número de decimales a dos

        Double mediaPrecioFunkos = lista.stream()
                .mapToDouble(Funko::getPrecio)
                .average().getAsDouble();

        String montoFormateado = formatoMoneda.format(mediaPrecioFunkos);
        System.out.println("PRECIO MEDIO: " + montoFormateado);
        return mediaPrecioFunkos;
    }

    public static void agrupadoPorModelo(List<Funko> lista, String modelo) {
        System.out.println("FUNKOS " + modelo + ":");
        lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo))
                .map(Funko::getNombre)
                .forEach(System.out::println);
    }

    public static void cantidadPorModelo(List<Funko> lista, String modelo) {
        System.out.println("NÚMERO DE FUNKOS " + modelo + ":");
        long cantidadMarvel = lista.stream()
                .filter(funko -> Objects.equals(funko.getModelo(), modelo))
                .count();

        System.out.println(cantidadMarvel);
    }

    public static void funkosPorAnyo(List<Funko> lista, int anyo) {
        System.out.println("FUNKOS DE " + anyo);
        lista.stream()
                .filter(funko -> funko.getFecha_lanzamiento().getYear() == anyo)
                .map(Funko::getNombre)
                .forEach(System.out::println);


    }

}