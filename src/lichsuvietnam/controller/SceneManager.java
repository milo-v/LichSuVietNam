package lichsuvietnam.controller;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.util.HashMap;

class SceneManager {
    private static BorderPane mainBorderPane;

    private static HashMap<String, Parent> sceneHashMap = new HashMap<>();

    public static void setMainBorderPane(BorderPane borderPane) {
        if (mainBorderPane != null) {
            System.out.println("Main Border Pane has been set");
            return;
        }

        mainBorderPane = borderPane;
    }

    public static void addScene(String sceneKey, Parent scene) {
        if (sceneHashMap.containsKey(sceneKey)) {
            System.out.println(sceneKey + " is already in the map");
            return;
        }

        sceneHashMap.put(sceneKey, scene);
    }

    public static void setCurrentScene(String sceneKey) {
        if (!sceneHashMap.containsKey(sceneKey)) {
            System.out.println(sceneKey + " is not in the map");
            return;
        }

        mainBorderPane.setCenter(sceneHashMap.get(sceneKey));
    }

}
