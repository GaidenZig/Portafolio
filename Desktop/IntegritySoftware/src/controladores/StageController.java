package controladores;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * Clase para "almacenar" las scene de una Stage (JavaFX)
 * representada por una instacia de esta clase.
 * @author Diego
 */
public class StageController {
    private HashMap<String, FXMLLoader> loaderMap = new HashMap<>();
    private HashMap<String, Scene> sceneMap       = new HashMap<>();
    private HashMap<String, Pane> fxmlMap         = new HashMap<>();
    private Stage mainStage;

    /**
     * Crear una instancia de la clase, para poder identificar la
     * Stage que se le dé por parámetro
     * @param main Stage que le proporcionaras a la instancia
     */
    public StageController(Stage main) {
        this.mainStage = main;
    }

    /**
     * Agregar una scene a la Instancia.
     * @param name Nombre con el que se identificará a la scene y su loader.
     * @param loader FXMLLoader en donde está "identificada" la ruta del fxml 
     * a utilizar para esta scene (importante: Debe estár ya identificada la ruta
     * antes de utilizar esta funcion).
     * @param scene Instancia de la Scene que quieres agregar(importante: debe tener un "root").
     */
    public void addScene(String name, FXMLLoader loader,Scene scene){
        this.loaderMap.put(name, loader);
        this.sceneMap.put(name,scene);       
    }
    
    public void addContent(String name, String fxmlPath) throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        this.fxmlMap.put(name,loader.load());
    }

    /**
     * Funcion para cambiar de scene identificada por un nombre.
     * @param name indentificador de la scene
     */
    public void changeScene(String name){
        this.activateScene(name);
    }
    
    public void changeContent(Pane parentNode,String content){
        parentNode.getChildren().setAll((Pane)this.fxmlMap.get(content));
    }
    
    /**
     * Mostrar una scene identificada por su nombre.
     * @param name nombre de la scene guardada en esta instancia de StageController.
     */
    public void activateScene(String name){  
        this.mainStage.setScene(this.sceneMap.get(name));
    }
    
    /**
     * Function para cerrar la Stage de la instancia actual.
     */
    public void stageOff(){
        this.mainStage.close();
    }
}
