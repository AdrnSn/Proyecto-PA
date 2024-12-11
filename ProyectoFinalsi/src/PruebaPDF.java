import javafx.application.Application;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PruebaPDF extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear contenido que se imprimirá en el PDF
        TextArea textArea = new TextArea();
        textArea.setText("¡Hola, este es un PDF generado con JavaFX!");
        textArea.setPrefSize(400, 300);

        StackPane root = new StackPane(textArea);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Generador de PDF");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Generar PDF
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {
            boolean printed = printerJob.printPage(root);
            if (printed) {
                printerJob.endJob();
                System.out.println("PDF generado con éxito.");
            } else {
                System.out.println("Error al generar el PDF.");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
