package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.nio.file.Path;

public class Main {
    private final static String COMMA_DELIMITER = ",";

    public static void main(String[] args) throws IOException {


        try (Stream<String> contenidoFichero = Files.lines(Path.of(".", "src", "main", "resources", "funkos.csv"))) {
            List<List<String>> funkosString = contenidoFichero.map(l -> Arrays.asList(l.split(COMMA_DELIMITER))).toList();
            //funkos.forEach(System.out::println);
            List<Funko> funkosFunkos = new ArrayList<>();





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


            funkosFunkos.forEach(System.out::println);

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





        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
}