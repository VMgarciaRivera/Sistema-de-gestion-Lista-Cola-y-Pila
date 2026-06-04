public class Cola {
    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    // 1. encolar(Object dato) - Inserta al final
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            nuevo.setAnterior(fin);
            fin = nuevo;
        }
        tamanio++;
    }

    // 2. desencolar() - Remueve y retorna el del frente
    public Object desencolar() {
        if (esVacia()) return null;
        Object dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        } else {
            frente.setAnterior(null);
        }
        tamanio--;
        return dato;
    }

    // 3. peek() - Mira el elemento al frente sin sacarlo
    public Object peek() {
        if (esVacia()) return null;
        return frente.getDato();
    }

    // 4. tamanio()
    public int tamanio() {
        return tamanio;
    }

    // 5. esVacia()
    public boolean esVacia() {
        return frente == null;
    }

    // 6. contiene(Object dato) - Verifica usando equals
    public boolean contiene(Object dato) {
        Nodo actual = frente;
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
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // 8. mostrar()
    public void mostrar() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo actual = frente;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    /**
     * Lógica obligatoria para Cancelar un elemento pendiente.
     * Utiliza una cola auxiliar para no romper la estructura de la cola principal.
     */
    public boolean cancelarElemento(Object modeloBuscado) {
        if (esVacia()) return false;

        Cola colaAuxiliar = new Cola();
        boolean encontrado = false;

        // Desencolamos todo hacia la cola auxiliar, excepto el que queremos cancelar
        while (!this.esVacia()) {
            Object actual = this.desencolar();
            if (actual.equals(modeloBuscado) && !encontrado) {
                encontrado = true; // Lo encontramos, nos lo saltamos (se cancela)
            } else {
                colaAuxiliar.encolar(actual);
            }
        }

        // Devolvemos los elementos de la cola auxiliar a la cola original
        while (!colaAuxiliar.esVacia()) {
            this.encolar(colaAuxiliar.desencolar());
        }

        return encontrado;
    }
}
