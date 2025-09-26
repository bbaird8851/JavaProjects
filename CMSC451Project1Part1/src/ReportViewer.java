/**
 * CMSC451 Project 1
 * @author Brandon Baird
 * ReportViewer: The table interface used to view the results of the benchmark tests 
 */
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReportViewer {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ReportViewer::createAndDisplayUI);
	}

	private static void createAndDisplayUI() {
		JFrame frame = new JFrame("Benchmark Report");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		JButton openButton = new JButton("Open Benchmark File");
		JTable table = new JTable();
		table.setAutoCreateRowSorter(true);

		openButton.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Select a file");
			int rv = chooser.showOpenDialog(frame);
			if (rv == JFileChooser.APPROVE_OPTION) {
				//try reading file
				File file = chooser.getSelectedFile();
				try {
					List<RowResult> rows = parseBenchmarkFile(file);
					table.setModel(new ReportTableModel(rows));
				} catch (Exception g) {
					g.printStackTrace();
					JOptionPane.showMessageDialog(frame,
							"Failed to read file: \n" + g.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel top = new JPanel(new BorderLayout());
		top.add(openButton, BorderLayout.WEST);

		frame.add(top, BorderLayout.NORTH);
		frame.add(new JScrollPane(table), BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	//This static class will represent 1 result for the selected benchmark
	static class RowResult {
		final int size;
		final double avgCount;
		final double coefCount;
		final double avgTimeNs;
		final double coefTime;

		RowResult(int size, double avgCount, double coefCount, double avgTimeNs, double coefTime) {
			this.size = size;
			this.avgCount = avgCount;
			this.coefCount = coefCount;
			this.avgTimeNs = avgTimeNs;
			this.coefTime = coefTime;
		}
	}

	//Copy the table model from the project description.
	//Use abstract table model 
	static class ReportTableModel extends AbstractTableModel {
		private final String[] cols = {
				"Size",
				"Avg Count",
				"Coef Count",
				"Avg Time",
				"Coef Time"
		};
		private final List<RowResult> data;

		ReportTableModel(List<RowResult> data) {
			this.data = data;
		}

		@Override 
		public int getRowCount() { return data.size(); }

		@Override 
		public int getColumnCount() { return cols.length; }
		@Override 
		public String getColumnName(int c) { return cols[c]; }

		@Override
		public Object getValueAt(int row, int col) {
			RowResult s = data.get(row);
			switch (col) {
			case 0: return s.size;
			case 1: return String.format("%.2f", s.avgCount);
			case 2: return String.format("%.2f", s.coefCount);
			case 3: return String.format("%.2f", s.avgTimeNs);
			case 4: return String.format("%.2f", s.coefTime);
			default: return "";
			}
		}

		//Hard-code the columns to be strings so we can do the format thing.
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}
	}

	/**
	 * Format is as follows:
	 * Size col1 | time1 | col2 | time2 . . . colN | timeN
	 */

	private static List<RowResult> parseBenchmarkFile(File file) throws IOException {
		List<RowResult> rows = new ArrayList<>();
		List<String> lines = Files.readAllLines(file.toPath());

		//iterate over raw data and trim to isolate value.
		for (String l : lines) {
			String line = l.trim();
			if (line.isEmpty()) continue;

			StringTokenizer tokenizer = new StringTokenizer(line);
			if (!tokenizer.hasMoreTokens()) continue;

			int size = Integer.parseInt(tokenizer.nextToken());

			//An ArrayList to house all our values.
			List<Double> countList = new ArrayList<>();
			List<Double> timeList = new ArrayList<>();

			//Loop will run for N amount of tokens.
			while (tokenizer.hasMoreTokens()) {
				String countToken = tokenizer.nextToken();
				String timeToken = tokenizer.nextToken();

				countList.add(Double.parseDouble(countToken));
				timeList.add(Double.parseDouble(timeToken));
			}

			double avgCount = calcMean(countList);
			double coefCount = calcPercent(countList, avgCount);
			double avgTime = calcMean(timeList);
			double coefTime = calcPercent(timeList, avgTime);

			rows.add(new RowResult(size, avgCount, coefCount, avgTime, coefTime));
		}

		return rows;
	}

	private static double calcMean(List<Double> dataSet) {
		double sum = 0.0;
		for (double d : dataSet) {
			sum += d;
		} 
		return sum / dataSet.size();
	}
	
	private static double calcPercent(List<Double> dataSet, double mean) {
		if (mean == 0.0) {
			return 0.0;
		}
		double sumOfSqares = 0.0;
		
		for (double d : dataSet) {
			double doub = d - mean;
			sumOfSqares += doub * doub;
		}
		
		double stddev = Math.sqrt(sumOfSqares / dataSet.size());
		return (stddev / mean) * 100.0;
	}
}
