package org.example;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funko {
    private String cod;
    private String nombre;
    private String modelo;

    private double precio;
    private LocalDate fecha_lanzamiento;

    @Override
    public String toString() {
        return  cod + "," + nombre + ", " + modelo + ", " + precio + ", " + fecha_lanzamiento + "\n";
    }
}
