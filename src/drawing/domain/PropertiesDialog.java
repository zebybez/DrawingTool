/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package drawing.domain;




        import java.util.Enumeration;
        import java.util.Optional;
        import java.util.Properties;
        import javafx.application.Platform;
        import javafx.geometry.Insets;
        import javafx.scene.Node;
        import javafx.scene.control.ButtonBar;
        import javafx.scene.control.ButtonType;
        import javafx.scene.control.Dialog;
        import javafx.scene.control.Label;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.GridPane;
        import javafx.util.Pair;

/**
 *
 * @author Hans
 */
class PropertiesDialog {
    private String title;
    private Properties properties;

    public PropertiesDialog(String t, Properties p)
    {
        title = t;
        properties=p;
    }

    public Optional<Properties> show()
    {
        Properties result = new Properties();
        Integer nrOfProps = properties.size();

        Dialog<Properties> dialog = new Dialog<>();
        dialog.setTitle("title");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField[] propertyFields = new TextField[nrOfProps];
        String[] labelTexts = new String[nrOfProps];

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        int i=0;
        Enumeration<Object> keyEnum = properties.keys();
        while(keyEnum.hasMoreElements()) {
            String nextObject = (String)keyEnum.nextElement();
            //result.values().add((Object)nextObject);
            labelTexts[i] = nextObject;
            propertyFields[i] = new TextField();
            propertyFields[i].setText(properties.getProperty(nextObject));
            grid.add(new Label(nextObject), 0, i);
            grid.add(propertyFields[i], 1, i);
            ++i;
        }

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                for(int j=0; j<nrOfProps;++j){
                    result.setProperty(labelTexts[j], propertyFields[j].getText());
                }
            }
            return result;
        });

        Optional<Properties> dlgResult = dialog.showAndWait();

        return dlgResult;
    }
}
