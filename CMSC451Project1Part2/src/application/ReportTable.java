package application;
	
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class ReportTable extends Application {
	
	private TableView<LineSummary> table = new TableView<>();
	private Label status = new Label ("Choose a benchmark output file to generate table.");

	public static class LineSummary {
        private final int nSize;
        private final double avgCount;
        private final double coefCount;
        private final double avgTime;
        private final double coefTime;

        public LineSummary(int nSize, double avgCount, double coefCount,
                           double avgTime, double coefTime) {
            this.nSize = nSize;
            this.avgCount = avgCount;
            this.coefCount = coefCount;
            this.avgTime = avgTime;
            this.coefTime = coefTime;
        }

        public int getNSize() { return nSize; }
        public double getAvgCount() { return avgCount; }
        public double getCoefCountPercent() { return coefCount; }
        public double getAvgTime() { return avgTime; }
        public double getCoefTimePercent() { return coefTime; }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Benchmark Report");
		
		//Set Columns:
		TableColumn<LineSummary, Number> sizeCol = new TableColumn<>("N Size");
		sizeCol.setCellValueFactory(new PropertyValueFactory<>("nSize"));
		
		TableColumn<LineSummary, Double> avgCountCol = new TableColumn<>("Avg Critical Count");
		avgCountCol.setCellValueFactory(new PropertyValueFactory<>("avgCount"));

        TableColumn<LineSummary, Double> coefCountCol = new TableColumn<>("Coef Count (%)");
        coefCountCol.setCellValueFactory(new PropertyValueFactory<>("coefCountPercent"));

        TableColumn<LineSummary, Double> avgTimeCol = new TableColumn<>("Avg Time (ns)");
        avgTimeCol.setCellValueFactory(new PropertyValueFactory<>("avgTime"));

        TableColumn<LineSummary, Double> coefTimeCol = new TableColumn<>("Coef Time (%)");
        coefTimeCol.setCellValueFactory(new PropertyValueFactory<>("coefTimePercent"));
		
        table.getColumns().addAll(sizeCol, avgCountCol, coefCountCol, avgTimeCol, coefTimeCol);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        
        //Open File
        Button openButton = new Button("Open Benchmark File: ");
        openButton.setOnAction(e -> {
        	FileChooser chooser = new FileChooser();
        	chooser.setTitle("Select benchmark output file");
        	 File file = chooser.showOpenDialog(primaryStage);
             if (file != null) {
                 try {
                     List<LineSummary> rows = parseBenchmarkFile(file);
                     ObservableList<LineSummary> data = FXCollections.observableArrayList(rows);
                     table.setItems(data);
                     status.setText("Loaded: " + file.getName() + " (" + rows.size() + " dataset sizes)");
                 } catch (Exception ex) {
                     ex.printStackTrace();
                     showError("Failed to read file:\n" + ex.getMessage());
                 }
             }
        });
        
        ToolBar toolbar = new ToolBar(openButton);

        BorderPane root = new BorderPane();
        root.setTop(toolbar);
        root.setCenter(table);
        root.setBottom(status);

        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
	}
	
	private static List<LineSummary> parseBenchmarkFile(File file) throws IOException {
        List<LineSummary> rows = new ArrayList<>();
        List<String> lines = Files.readAllLines(file.toPath());

        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            int n = Integer.parseInt(tokens[0]);

            List<Double> counts = new ArrayList<>(40);
            List<Double> times = new ArrayList<>(40);

            for (int i = 1; i < tokens.length; i += 2) {
                double c = Double.parseDouble(tokens[i]);
                double t = Double.parseDouble(tokens[i + 1]);
                counts.add(c);
                times.add(t);
            }

            double avgC = calcMean(counts);
            double cvC = calcPercentage(counts, avgC);
            double avgT = calcMean(times);
            double cvT = calcPercentage(times, avgT);

            rows.add(new LineSummary(n, avgC, cvC, avgT, cvT));
        }
        rows.sort(Comparator.comparingInt(LineSummary::getNSize));
        return rows;
    } 
	
	private static double calcMean(List<Double> colVals) {
		if(colVals == null || colVals.isEmpty()) {
			return 0;
		}
		double sum = 0;
		for(double v : colVals) {
			sum += v;
		}
		return (sum / colVals.size());
	}
	
	private static double calcPercentage(List<Double> colVals, double mean) {
		if (colVals == null || colVals.isEmpty() || mean == 0.0) {
	        return 0.0;
	    }

	    double sumSq = 0.0;
	    for (double v : colVals) {
	        double diff = v - mean;
	        sumSq += diff * diff;
	    }

	    double variance = sumSq / colVals.size();
	    double stddev = Math.sqrt(variance);
	    return (stddev / mean) * 100.0;   
	}
	
	private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
	
}
