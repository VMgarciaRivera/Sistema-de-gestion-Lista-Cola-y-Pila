import java.util.Scanner;

public class SistemaDelCaso {

    public static void main(String[] args) {
        Lista listaGeneral = new Lista();
        Cola colaPendientes = new Cola();
        Pila historialProcesados = new Pila();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        System.out.println("====================================================");
        System.out.println("  SISTEMA DE GESTIÓN DE CINE - COMPRA DE ENTRADAS   ");
        System.out.println("====================================================");

        do {
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Registrar elemento");
            System.out.println("2. Ver todos los elementos registrados");
            System.out.println("3. Ver elementos pendientes");
            System.out.println("4. Procesar siguiente elemento");
            System.out.println("5. Ver historial de elementos procesados");
            System.out.println("6. Buscar elemento por codigo");
            System.out.println("7. Cancelar elemento pendiente");
            System.out.println("8. Deshacer último procesamiento");
            System.out.println("9. Ver cantidad de elementos");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1: // Registrar elemento
                    System.out.print("Ingrese el código de la compra: ");
                    String codigo = teclado.nextLine();
                    System.out.print("Ingrese el nombre de la película: ");
                    String pelicula = teclado.nextLine();
                    System.out.print("Ingrese la cantidad de boletas: ");
                    int cantidad = Integer.parseInt(teclado.nextLine());
                    System.out.print("Ingrese el total pagado: ");
                    double total = Double.parseDouble(teclado.nextLine());

                    CompraEntrada nuevaCompra = new CompraEntrada(codigo, pelicula, cantidad, total);

                    // Regla obligatoria: Se agrega a la lista general y se encola a pendientes
                    listaGeneral.agregar(nuevaCompra);
                    colaPendientes.encolar(nuevaCompra);
                    System.out.println("Compra registrada con éxito en el sistema.");
                    break;

                case 2: // Ver todos los elementos registrados
                    System.out.println("\n--- LISTA GENERAL DE REGISTROS ---");
                    listaGeneral.mostrarAdelante();
                    break;

                case 3: // Ver elementos pendientes
                    System.out.println("\n--- COLA DE COMPRAS PENDIENTES POR PROCESAR ---");
                    colaPendientes.mostrar();
                    break;

                case 4: // Procesar siguiente elemento
                    if (colaPendientes.esVacia()) {
                        System.out.println("No hay compras pendientes por procesar.");
                    } else {
                        // Regla obligatoria: Desencolar de pendientes y apilar en historial
                        CompraEntrada procesado = (CompraEntrada) colaPendientes.desencolar();
                        historialProcesados.apilar(procesado);
                        System.out.println("Procesando la siguiente compra: " + procesado);
                    }
                    break;

                case 5: // Ver historial de elementos procesados
                    System.out.println("\n--- HISTORIAL DE COMPRAS PROCESADAS ---");
                    historialProcesados.mostrar();
                    break;

                case 6: // Buscar elemento por código
                    System.out.print("Ingrese el código de compra a buscar: ");
                    String codBuscar = teclado.nextLine();
                    // Creamos un molde temporal para usar la comparación equals de la lista
                    CompraEntrada moldeBusqueda = new CompraEntrada(codBuscar, "", 0, 0);
                    CompraEntrada encontrado = (CompraEntrada) listaGeneral.buscarDato(moldeBusqueda);

                    if (encontrado != null) {
                        System.out.println("Elemento encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró ninguna compra con el código: " + codBuscar);
                    }
                    break;

                case 7: // Cancelar elemento pendiente
                    System.out.print("Ingrese el código de la compra pendiente a cancelar: ");
                    String codCancelar = teclado.nextLine();
                    CompraEntrada moldeCancelar = new CompraEntrada(codCancelar, "", 0, 0);

                    // Lógica obligatoria usando cola auxiliar encapsulada
                    boolean cancelado = colaPendientes.cancelarElemento(moldeCancelar);
                    if (cancelado) {
                        System.out.println("Compra pendiente cancelada correctamente.");
                    } else {
                        System.out.println("No se encontró la compra en la cola de pendientes.");
                    }
                    break;

                case 8: // Deshacer último procesamiento
                    if (historialProcesados.esVacia()) {
                        System.out.println("No hay procesamientos en el historial para deshacer.");
                    } else {
                        // Regla obligatoria: Desapilar del historial y devolver a la cola de pendientes
                        CompraEntrada ultimo = (CompraEntrada) historialProcesados.desapilar();
                        colaPendientes.encolar(ultimo);
                        System.out.println("↩️ Se deshizo el procesamiento de: " + ultimo + ". Volvió a pendientes.");
                    }
                    break;

                case 9: // Ver cantidad de elementos
                    System.out.println("\n--- REPORTE DE CANTIDADES ---");
                    System.out.println("• Total histórico registrado (Lista): " + listaGeneral.cuentaElementos());
                    System.out.println("• En espera de atención (Cola): " + colaPendientes.tamanio());
                    System.out.println("• Ya procesados (Pila): " + historialProcesados.tamanio());
                    break;

                case 10: // Salir
                    System.out.println("Saliendo del sistema de gestión de cine. ¡Buen día!");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 10);

        teclado.close();
    }
}
