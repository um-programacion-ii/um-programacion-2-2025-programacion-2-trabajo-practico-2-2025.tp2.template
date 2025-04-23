package Gestores;
import Interfaces.*;
import Recursos.*;
import Prestamos.*;
import Usuarios.*;
import Servicios.*;
import Interaccion.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Estadisticas {
    private final SistemaPrestamos sistemaPrestamos;

    public Estadisticas(SistemaPrestamos sistemaPrestamos) {
        this.sistemaPrestamos = sistemaPrestamos;
    }

    public void generarReporteRecursosMasPrestados() {
        System.out.println("\n📘 Recursos más prestados:");
        Map<String, Long> conteo = sistemaPrestamos.getPrestamosActivos().stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getTitulo(), Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println(" - " + e.getKey() + ": " + e.getValue() + " préstamos"));
    }

    public void generarReporteUsuariosMasActivos() {
        System.out.println("\n👤 Usuarios más activos:");
        Map<String, Long> conteo = sistemaPrestamos.getPrestamosActivos().stream()
                .collect(Collectors.groupingBy(p -> p.getUsuario().getEmail(), Collectors.counting()));

        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println(" - " + e.getKey() + ": " + e.getValue() + " préstamos"));
    }

    public void generarEstadisticasPorCategoria() {
        System.out.println("\n📊 Estadísticas por categoría:");
        Map<CategoriaRecurso, Long> conteo = sistemaPrestamos.getPrestamosActivos().stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getCategoria(), Collectors.counting()));

        conteo.forEach((categoria, cantidad) ->
                System.out.println(" - " + categoria + ": " + cantidad + " préstamos"));
    }

    public void generarEstadisticasdePrestamos() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("🟡 Generando reporte de préstamos...");
            try {
                Thread.sleep(1000);
                for (Prestamo p : sistemaPrestamos.getPrestamosActivos()) {
                    System.out.println("📄 " + p);
                    Thread.sleep(300);
                }
                System.out.println("✅ Reporte generado con éxito.");
            } catch (InterruptedException e) {
                System.out.println("❌ Error al generar el reporte.");
            }
        });

        executor.shutdown();
    }
}
