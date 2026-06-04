public class CompraEntrada {
    private String codigoCompra; // Identificador principal
    private String pelicula;
    private int cantidadBoletas;
    private double totalPagado;

    public CompraEntrada(String codigoCompra, String pelicula, int cantidadBoletas, double totalPagado) {
        this.codigoCompra = codigoCompra;
        this.pelicula = pelicula;
        this.cantidadBoletas = cantidadBoletas;
        this.totalPagado = totalPagado;
    }

    // Getters y Setters
    public String getCodigoCompra() { return codigoCompra; }
    public void setCodigoCompra(String codigoCompra) { this.codigoCompra = codigoCompra; }

    public String getPelicula() { return pelicula; }
    public void setPelicula(String pelicula) { this.pelicula = pelicula; }

    public int getCantidadBoletas() { return cantidadBoletas; }
    public void setCantidadBoletas(int cantidadBoletas) { this.cantidadBoletas = cantidadBoletas; }

    public double getTotalPagado() { return totalPagado; }
    public void setTotalPagado(double totalPagado) { this.totalPagado = totalPagado; }

    // El método equals() compara estrictamente por el identificador principal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompraEntrada other = (CompraEntrada) obj;
        return codigoCompra != null && codigoCompra.equalsIgnoreCase(other.codigoCompra);
    }

    @Override
    public String toString() {
        return "[Código: " + codigoCompra + " | Película: " + pelicula +
                " | Boletas: " + cantidadBoletas + " | Total: $" + totalPagado + "]";
    }
}
