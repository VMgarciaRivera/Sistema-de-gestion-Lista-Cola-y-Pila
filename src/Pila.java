public class Pila {
    private Nodo cima;
    private int tamanio;

    public Pila() {
        this.cima = null;
        this.tamanio = 0;
    }

    // 1. apilar(Object dato) - Inserta en la cima
    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (cima != null) {
            nuevo.setSiguiente(cima);
            cima.setAnterior(nuevo);
        }
        cima = nuevo;
        tamanio++;
    }

    // 2. desapilar() - Remueve y retorna el elemento de la cima
    public Object desapilar() {
        if (esVacia()) return null;
        Object dato = cima.getDato();
        cima = cima.getSiguiente();
        if (cima != null) {
            cima.setAnterior(null);
        }
        tamanio--;
        return dato;
    }

    // 3. peek() - Mira la cima sin removerla
    public Object peek() {
        if (esVacia()) return null;
        return cima.getDato();
    }

    // 4. tamanio()
    public int tamanio() {
        return tamanio;
    }

    // 5. esVacia()
    public boolean esVacia() {
        return cima == null;
    }

    // 6. contiene(Object dato) - Verifica si el dato existe usando equals
    public boolean contiene(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    // 7. limpiar()
    public void limpiar() {
        cima = null;
        tamanio = 0;
    }

    // 8. mostrar() - Muestra los elementos desde la cima hacia la base
    public void mostrar() {
        if (esVacia()) {
            System.out.println("La pila del historial está vacía.");
            return;
        }
        Nodo actual = cima;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    // 9. buscar(Object dato) - Retorna el objeto si coincide con el criterio de búsqueda
    public Object buscar(Object dato) {
        Nodo actual = cima;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}