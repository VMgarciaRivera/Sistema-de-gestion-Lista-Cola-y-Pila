public class Lista {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    // 1. agregar(Object dato) - Agrega al final
    public void agregar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }

    // 2. agregarAlInicio(Object dato)
    public void agregarAlInicio(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    // 3. agregarEnPosicion(int indice, Object dato)
    public void agregarEnPosicion(int indice, Object dato) {
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (indice == 0) {
            agregarAlInicio(dato);
        } else if (indice == tamanio) {
            agregar(dato);
        } else {
            Nodo nuevo = new Nodo(dato);
            Nodo actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.getSiguiente();
            }
            Nodo anterior = actual.getAnterior();

            anterior.setSiguiente(nuevo);
            nuevo.setAnterior(anterior);
            nuevo.setSiguiente(actual);
            actual.setAnterior(nuevo);
            tamanio++;
        }
    }

    // 4. eliminarPrimero()
    public void eliminarPrimero() {
        if (cabeza == null) return;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        tamanio--;
    }

    // 5. eliminarUltimo()
    public void eliminarUltimo() {
        if (cola == null) return;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        tamanio--;
    }

    // 6. eliminarEnPosicion(int indice)
    public void eliminarEnPosicion(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (indice == 0) {
            eliminarPrimero();
        } else if (indice == tamanio - 1) {
            eliminarUltimo();
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.getSiguiente();
            }
            Nodo anterior = actual.getAnterior();
            Nodo siguiente = actual.getSiguiente();

            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
            tamanio--;
        }
    }

    // 7. buscarDato(int indice) - Retorna el dato por su posición
    public Object buscarDato(int indice) {
        if (indice < 0 || indice >= tamanio) return null;
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    // 8. buscarDato(Object dato) - Retorna el objeto si lo encuentra (usa equals)
    public Object buscarDato(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    // 9. contiene(Object dato)
    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    // 10. cuentaElementos()
    public int cuentaElementos() {
        return tamanio;
    }

    // 11. limpiar()
    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    // 12. mostrarAdelante()
    public void mostrarAdelante() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    // 13. mostrarAtras()
    public void mostrarAtras() {
        if (cola == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = cola;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }
}
