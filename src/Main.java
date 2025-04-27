//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Creación de la AppStore (que ya viene con juegos predefinidos)
        AppStore tienda = new AppStore();

        // Obtener referencias a los juegos de la tienda
        Juego juego1 = tienda.darJuego1(); // Candy Crush (Rompecabezas)
        Juego juego2 = tienda.darJuego2(); // Flow (Rompecabezas)
        Juego juego3 = tienda.darJuego3(); // FIFA (Deporte)
        Juego juego4 = tienda.darJuego4(); // Clash of Clans (Acción)

        // Imprimir todos los juegos
        System.out.println("Lista de juegos disponibles inicialmente:");
        imprimirInfoJuego(juego1);
        imprimirInfoJuego(juego2);
        imprimirInfoJuego(juego3);
        imprimirInfoJuego(juego4);

        // Simulando ventas para crear diferentes niveles de stock
        System.out.println("\nSimulando ventas para crear diferentes niveles de stock...");
        tienda.venderLicenciasJuego(juego1.darNombre(), 48); // Dejamos solo 2 licencias de Candy Crush
        tienda.venderLicenciasJuego(juego2.darNombre(), 10); // Dejamos solo 5 licencias de Flow
        tienda.venderLicenciasJuego(juego3.darNombre(), 77); // Dejamos solo 3 licencias de FIFA
        tienda.venderLicenciasJuego(juego4.darNombre(), 30); // Dejamos solo 6 licencias de Clash of Clans

        System.out.println("\nStock de juegos después de las ventas:");
        System.out.println(juego1.darNombre() + ": " + juego1.darCantidadLicencias() + " licencias");
        System.out.println(juego2.darNombre() + ": " + juego2.darCantidadLicencias() + " licencias");
        System.out.println(juego3.darNombre() + ": " + juego3.darCantidadLicencias() + " licencias");
        System.out.println(juego4.darNombre() + ": " + juego4.darCantidadLicencias() + " licencias");

        // Aplicando la política de porcentaje
        System.out.println("\nAplicando política de porcentaje (25% del juego menos vendido):");
        aplicarPoliticaPorcentaje(tienda, juego1, juego2, juego3, juego4);

        // Aplicando la política de categoría
        System.out.println("\nAplicando política de categoría (mínimo 10 para deporte/aventura, 15 para acción/velocidad):");
        aplicarPoliticaCategoria(tienda, juego1, juego2, juego3, juego4);

        // Mostrar stock final
        System.out.println("\nStock final después de aplicar políticas de compra:");
        System.out.println(juego1.darNombre() + ": " + juego1.darCantidadLicencias() + " licencias");
        System.out.println(juego2.darNombre() + ": " + juego2.darCantidadLicencias() + " licencias");
        System.out.println(juego3.darNombre() + ": " + juego3.darCantidadLicencias() + " licencias");
        System.out.println(juego4.darNombre() + ": " + juego4.darCantidadLicencias() + " licencias");
    }

    /**
     * Aplica la política de porcentaje: Se deben comprar licencias de un juego cada vez que la cantidad de
     * licencias sea menor al 25% de la cantidad de licencias que tiene el juego menos vendido.
     * @param tienda La tienda de aplicaciones.
     * @param juegos Los juegos a verificar.
     */
    private static void aplicarPoliticaPorcentaje(AppStore tienda, Juego... juegos) {
        // Encontrar el juego menos vendido
        Juego juegoMenosVendido = juegos[0];
        for (Juego juego : juegos) {
            if (juego.darCantidadVendidas() < juegoMenosVendido.darCantidadVendidas()) {
                juegoMenosVendido = juego;
            }
        }

        System.out.println("El juego menos vendido es: " + juegoMenosVendido.darNombre() +
                " con " + juegoMenosVendido.darCantidadVendidas() + " licencias vendidas");

        // Calcular el umbral del 25% de las licencias del juego menos vendido
        int umbral = (int)(juegoMenosVendido.darCantidadLicencias() * 0.25);
        System.out.println("Umbral del 25%: " + umbral + " licencias");

        // Verificar cada juego y comprar licencias si es necesario
        for (Juego juego : juegos) {
            if (juego.darCantidadLicencias() < umbral) {
                int cantidadComprar = umbral - juego.darCantidadLicencias() + 5; // Compramos 5 extra para tener margen
                System.out.println("Comprando " + cantidadComprar + " licencias de " + juego.darNombre() +
                        " (tenía " + juego.darCantidadLicencias() + ", menos del umbral)");
                tienda.comprarLicenciasJuego(juego.darNombre(), cantidadComprar);
            } else {
                System.out.println("No es necesario comprar licencias de " + juego.darNombre() +
                        " (tiene " + juego.darCantidadLicencias() + ", supera el umbral)");
            }
        }
    }

    /**
     * Aplica la política de categoría: Se deben comprar licencias de un juego cada vez que la cantidad de
     * licencias sea menor a 10 para juegos de deporte y aventura y menor a 15 para juegos de acción y velocidad.
     * @param tienda La tienda de aplicaciones.
     * @param juegos Los juegos a verificar.
     */
    private static void aplicarPoliticaCategoria(AppStore tienda, Juego... juegos) {
        for (Juego juego : juegos) {
            int umbralMinimo;

            // Determinar el umbral según la categoría
            if (juego.darCategoria() == Juego.Categoria.DEPORTE) {
                umbralMinimo = 10; // Mínimo para juegos de deporte
                System.out.println("Juego de DEPORTE: " + juego.darNombre() + " (umbral: " + umbralMinimo + ")");
            } else if (juego.darCategoria() == Juego.Categoria.ACCION) {
                umbralMinimo = 15; // Mínimo para juegos de acción
                System.out.println("Juego de ACCION: " + juego.darNombre() + " (umbral: " + umbralMinimo + ")");
            } else {
                // Para juegos de rompecabezas, usaremos un umbral de 10 como valor predeterminado
                umbralMinimo = 10;
                System.out.println("Juego de ROMPECABEZAS: " + juego.darNombre() + " (umbral: " + umbralMinimo + ")");
            }

            // Verificar si se necesita comprar licencias
            if (juego.darCantidadLicencias() < umbralMinimo) {
                int cantidadComprar = umbralMinimo - juego.darCantidadLicencias() + 5; // Compramos 5 extra
                System.out.println("Comprando " + cantidadComprar + " licencias de " + juego.darNombre() +
                        " (tenía " + juego.darCantidadLicencias() + ", menos del umbral por categoría)");
                tienda.comprarLicenciasJuego(juego.darNombre(), cantidadComprar);
            } else {
                System.out.println("No es necesario comprar licencias de " + juego.darNombre() +
                        " (tiene " + juego.darCantidadLicencias() + ", supera el umbral por categoría)");
            }
        }
    }

    /**
     * Método auxiliar para imprimir la información de un juego.
     * @param juego El juego cuya información se desea imprimir.
     */
    private static void imprimirInfoJuego(Juego juego) {
        System.out.println("Nombre: " + juego.darNombre() +
                ", Categoría: " + juego.darCategoria() +
                ", Precio: $" + juego.darPrecio() +
                ", Tamaño: " + juego.darTamanio() + "KB" +
                ", Licencias disponibles: " + juego.darCantidadLicencias());
    }
}