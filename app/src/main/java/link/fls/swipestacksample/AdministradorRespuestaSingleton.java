package link.fls.swipestacksample;

/**
 * Created by u197618 on 1/12/17.
 */

public class AdministradorRespuestaSingleton {
    private static AdministradorRespuestaSingleton INSTANCE = null;
    private int aciertos = 0;
    // Private constructor suppresses
    private AdministradorRespuestaSingleton(){}

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdministradorRespuestaSingleton();
        }
    }

    public static AdministradorRespuestaSingleton getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    public void sumarRsta(){
        this.aciertos = aciertos + 1;
    }

    public void inicializarAciertos(){
        this.aciertos = 0;
    }


    public int getAciertos(){
        return this.aciertos;
    }

}
