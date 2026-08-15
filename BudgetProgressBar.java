import javax.swing.*;
import java.awt.FlowLayout;

public class BudgetProgressBar {

    private JFrame frame;
    private JProgressBar progressBar;

    public BudgetProgressBar(double monthlyBudget, double monthlyExpense) {
        frame = new JFrame("Budget Progress");
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        showProgress(monthlyBudget, monthlyExpense);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        frame.add(progressBar);
        frame.setSize(350, 120);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showProgress(double monthlyBudget, double monthlyExpense) {
        if (monthlyBudget <= 0) return;
        double percentage = (monthlyExpense / monthlyBudget) * 100;
        if (percentage > 100) {
            percentage = 100;
        }
        progressBar.setValue((int) percentage);
        progressBar.setString(String.format("%.0f%% Used", percentage));
    }
}
