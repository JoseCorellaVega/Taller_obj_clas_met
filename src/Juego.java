/**
 * Juego de la AppStore.
 */
public class Juego
{

    // -----------------------------------------------------------------
    // Enumeraciones
    // -----------------------------------------------------------------

    /**
     * Enumeradores para la categor a del juego.
     */
    public enum Categoria
    {
        /**
         * Representa la categor a rompecabezas.
         */
        ROMPECABEZAS,

        /**
         * Representa la categor a deportes.
         */
        DEPORTE,

        /**
         * Representa la categor a acci n.
         */
        ACCION
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre del juego.
     */
    private String nombre;

    /**
     * Categor a del juego.
     */
    private Categoria categoria;

    /**
     * Precio del juego.
     */
    private int precio;

    /**
     * Tama o en KB del juego.
     */
    private int tamanio;

    /**
     * Cantidad de licencias del juego que se tienen.
     */
    private int cantidadLicencias;

    /**
     * Cantidad de licencias que se han vendido.
     */
    private int cantidadVendidas;



    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo juego con los valores dados por par metro. <br>
     * <b>post: </b> El nombre, la categor a, el precio, el tama o, la cantidad de licencias y la ruta de la imagen fueron inicializados <br>
     * con los valores dados por par metro. La cantidad de licencias vendidas fue inicializada en 0.
     * @param pNombre Nombre del juego. pNombre != null && pNombre != "".
     * @param pCategoria Categor a del juego. pCategoria == ROMPECABEZAS || pCategoria == DEPORTE || pCategoria == ACCION.
     * @param pPrecio Precio que tiene el juego. pPrecio > 0.
     * @param pTamanio Tama o en KB del juego. pTamanio > 0.
     * @param pCantidadLicencias Cantidad de licencias de juego disponibles. pCantidadLicencias >= 0.

     */
    public Juego( String pNombre, Categoria pCategoria, int pPrecio, int pTamanio, int pCantidadLicencias)
    {
        nombre = pNombre;
        categoria = pCategoria;
        precio = pPrecio;
        tamanio = pTamanio;
        cantidadLicencias = pCantidadLicencias;
        cantidadVendidas = 0;
    }

    // -----------------------------------------------------------------
    // M todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del juego.
     * @return Nombre del juego
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna la categor a del juego.
     * @return Categor a del juego.
     */
    public Categoria darCategoria( )
    {
        return categoria;
    }

    /**
     * Retorna el precio del juego.
     * @return Precio del juego.
     */
    public int darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna el tama o en KB del Juego.
     * @return Tama o en KB del Juego.
     */
    public int darTamanio( )
    {
        return tamanio;
    }

    /**
     * Retorna la cantidad de licencias del juego.
     * @return Cantidad de licencias del juego.
     */
    public int darCantidadLicencias( )
    {
        return cantidadLicencias;
    }

    /**
     * Retorna la cantidad de licencias vendidas.
     * @return Cantidad de licencias vendidas.
     */
    public int darCantidadVendidas( )
    {
        return cantidadVendidas;
    }



    /**
     * Compra una cantidad dada de licencias. <br>
     * <b>post: </b> Se aument  la cantidad actual de licencias del juego en la cantidad dada.
     * @param pCantidad Cantidad de licencias que se quieren comprar. pCantidad > 0.
     */
    public void comprarLicencias( int pCantidad )
    {
        cantidadLicencias += pCantidad;
    }

    /**
     * Vende una cantidad dada de licencias, si cuenta con la cantidad suficiente. <br>
     * <b>post: </b> Disminuy  la cantidad actual de licencias del juego. <br>
     * Aument  la cantidad de licencias vendidas.
     * @param pCantidad Cantidad de licencias que se quieren vender. pCantidad > 0.
     * @return Retorna true si logra realizar la venta, false de lo contrario.
     */
    public boolean venderLicencias( int pCantidad )
    {
        boolean respuesta = false;
        if( cantidadLicencias >= pCantidad )
        {
            cantidadLicencias -= pCantidad;
            cantidadVendidas += pCantidad;
            respuesta = true;
        }
        return respuesta;
    }
}