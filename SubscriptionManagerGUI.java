
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionManagerGUI extends JFrame {

    private List<Subscription> subscriptions =
            new ArrayList<>();

    private JTabbedPane tabbedPane;

    // Add Subscription fields
    private JTextField nameField;
    private JTextField idField;
    private JTextField paymentField;
    private JTextField statusField;
    private JTextField startDateField;
    private JTextField durationField;
    private JTextField costField;

    private JCheckBox autoRenewalBox;
    private JComboBox<String> typeBox;

    // Table
    private DefaultTableModel tableModel;
    private JTable subscriptionTable;

    // Budget
    private JTextField budgetField;
    private JProgressBar budgetProgress;
    private JLabel expenseLabel;

    // Report
    private JTextArea reportArea;


    public SubscriptionManagerGUI() {

        setTitle("Subscription Manager");

        setSize(900, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createTabs();

        setVisible(true);
    }


    // =========================================
    // TABS
    // =========================================

    private void createTabs() {

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab(
                "Dashboard",
                createDashboardPanel()
        );

        tabbedPane.addTab(
                "Add Subscription",
                createAddPanel()
        );

        tabbedPane.addTab(
                "My Subscriptions",
                createSubscriptionPanel()
        );

        tabbedPane.addTab(
                "Budget",
                createBudgetPanel()
        );

        tabbedPane.addTab(
                "Report",
                createReportPanel()
        );

        add(tabbedPane);
    }


    // =========================================
    // DASHBOARD
    // =========================================

    private JPanel createDashboardPanel() {

        JPanel panel =
                new JPanel(new BorderLayout());

        JLabel title =
                new JLabel(
                        "Welcome to Subscription Manager",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel information =
                new JLabel(
                        "<html><center>" +
                        "Manage your monthly and annual subscriptions"
                        + "<br><br>" +
                        "Track expenses • Monitor budget"
                        + " • Cancel subscriptions"
                        + " • View reports" +
                        "</center></html>",
                        SwingConstants.CENTER
                );

        information.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                information,
                BorderLayout.CENTER
        );

        return panel;
    }


    // =========================================
    // ADD SUBSCRIPTION
    // =========================================

    private JPanel createAddPanel() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        typeBox =
                new JComboBox<>(
                        new String[]{
                                "Monthly Subscription",
                                "Annual Subscription"
                        }
                );

        nameField =
                new JTextField(20);

        idField =
                new JTextField(20);

        paymentField =
                new JTextField(20);

        statusField =
                new JTextField("Active", 20);

        startDateField =
                new JTextField(20);

        durationField =
                new JTextField(20);

        costField =
                new JTextField(20);

        autoRenewalBox =
                new JCheckBox("Auto Renewal");


        addField(
                panel,
                gbc,
                0,
                "Subscription Type:",
                typeBox
        );

        addField(
                panel,
                gbc,
                1,
                "Subscription Name:",
                nameField
        );

        addField(
                panel,
                gbc,
                2,
                "Subscription ID:",
                idField
        );

        addField(
                panel,
                gbc,
                3,
                "Payment Method:",
                paymentField
        );

        addField(
                panel,
                gbc,
                4,
                "Status:",
                statusField
        );

        addField(
                panel,
                gbc,
                5,
                "Start Date:",
                startDateField
        );

        addField(
                panel,
                gbc,
                6,
                "Duration:",
                durationField
        );

        addField(
                panel,
                gbc,
                7,
                "Cost:",
                costField
        );


        gbc.gridx = 1;
        gbc.gridy = 8;

        panel.add(
                autoRenewalBox,
                gbc
        );


        JButton addButton =
                new JButton(
                        "Add Subscription"
                );

        gbc.gridy = 9;

        panel.add(
                addButton,
                gbc
        );


        addButton.addActionListener(
                e -> addSubscription()
        );

        return panel;
    }


    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            Component component) {

        gbc.gridx = 0;
        gbc.gridy = row;

        panel.add(
                new JLabel(label),
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                component,
                gbc
        );
    }


    // =========================================
    // ADD SUBSCRIPTION LOGIC
    // =========================================

    private void addSubscription() {

        try {

            String type =
                    (String) typeBox.getSelectedItem();

            String name =
                    nameField.getText().trim();

            String id =
                    idField.getText().trim();

            String payment =
                    paymentField.getText().trim();

            String status =
                    statusField.getText().trim();

            String startDate =
                    startDateField.getText().trim();

            boolean autoRenewal =
                    autoRenewalBox.isSelected();

            int duration =
                    Integer.parseInt(
                            durationField.getText().trim()
                    );

            double cost =
                    Double.parseDouble(
                            costField.getText().trim()
                    );


            if (name.isEmpty()
                    || id.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter subscription name and ID."
                );

                return;
            }


            Subscription subscription;


            if (type.equals(
                    "Monthly Subscription")) {

                subscription =
                        new MonthlySubscription(
                                name,
                                id,
                                payment,
                                status,
                                startDate,
                                autoRenewal,
                                duration,
                                cost
                        );

            } else {

                subscription =
                        new AnnualSubscription(
                                name,
                                id,
                                payment,
                                status,
                                startDate,
                                autoRenewal,
                                duration,
                                cost
                        );
            }


            subscriptions.add(
                    subscription
            );


            JOptionPane.showMessageDialog(
                    this,
                    "Subscription added successfully!"
            );


            clearFields();

            refreshTable();

            refreshReport();


        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Duration and cost must be valid numbers."
            );
        }
    }


    private void clearFields() {

        nameField.setText("");
        idField.setText("");
        paymentField.setText("");
        statusField.setText("Active");
        startDateField.setText("");
        durationField.setText("");
        costField.setText("");

        autoRenewalBox.setSelected(false);
    }


    // =========================================
    // MY SUBSCRIPTIONS
    // =========================================

    private JPanel createSubscriptionPanel() {

        JPanel panel =
                new JPanel(new BorderLayout());


        String[] columns = {

                "Name",
                "ID",
                "Type",
                "Payment",
                "Status",
                "Start Date",
                "Duration",
                "Monthly Cost"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                );


        subscriptionTable =
                new JTable(tableModel);


        JScrollPane scrollPane =
                new JScrollPane(
                        subscriptionTable
                );


        JButton cancelButton =
                new JButton(
                        "Cancel Selected Subscription"
                );


        cancelButton.addActionListener(
                e -> cancelSelectedSubscription()
        );


        // Separate panel for the cancel button
        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                cancelButton
        );


        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        return panel;
    }


    private void refreshTable() {

        if (tableModel == null)
            return;


        tableModel.setRowCount(0);


        for (Subscription sub :
                subscriptions) {

            String type;


            if (sub instanceof
                    MonthlySubscription) {

                type = "Monthly";

            } else {

                type = "Annual";
            }


            Object[] row = {

                    sub.getSubscriptionName(),

                    sub.getSubscriptionId(),

                    type,

                    sub.getPaymentMethod(),

                    sub.getStatus(),

                    sub.getStartDate(),

                    sub.getDuration(),

                    String.format(
                            "%.2f",
                            sub.getMonthlyCost()
                    )
            };


            tableModel.addRow(row);
        }
    }


    // =========================================
    // CANCEL
    // =========================================

    private void cancelSelectedSubscription() {

        int selectedRow =
                subscriptionTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a subscription first."
            );

            return;
        }


        Subscription selected =
                subscriptions.get(selectedRow);


        if (!(selected instanceof Cancelable)) {

            JOptionPane.showMessageDialog(
                    this,
                    "This subscription cannot be cancelled."
            );

            return;
        }


        Cancelable cancelable =
                (Cancelable) selected;


        if (!cancelable.isCancelable()) {

            JOptionPane.showMessageDialog(
                    this,
                    "This subscription is already cancelled."
            );

            return;
        }


        int confirmation =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to cancel "
                                + selected.getSubscriptionName()
                                + "?",

                        "Confirm Cancellation",

                        JOptionPane.YES_NO_OPTION
                );


        if (confirmation ==
                JOptionPane.YES_OPTION) {


            cancelable.cancelSubscription();


            refreshTable();

            refreshReport();


            JOptionPane.showMessageDialog(
                    this,
                    "Subscription cancelled successfully."
            );
        }
    }


    // =========================================
    // BUDGET
    // =========================================

    private JPanel createBudgetPanel() {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );


        JLabel title =
                new JLabel(
                        "Monthly Budget"
                );


        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );


        budgetField =
                new JTextField();


        budgetField.setMaximumSize(
                new Dimension(
                        300,
                        30
                )
        );


        JButton checkButton =
                new JButton(
                        "Check Budget"
                );


        budgetProgress =
                new JProgressBar(
                        0,
                        100
                );


        budgetProgress.setStringPainted(
                true
        );


        expenseLabel =
                new JLabel(
                        "Monthly Expense: 0.00"
                );


        panel.add(
                Box.createVerticalStrut(40)
        );


        panel.add(title);


        panel.add(
                Box.createVerticalStrut(20)
        );


        panel.add(
                new JLabel(
                        "Enter Monthly Budget:"
                )
        );


        panel.add(
                budgetField
        );


        panel.add(
                Box.createVerticalStrut(15)
        );


        panel.add(
                checkButton
        );


        panel.add(
                Box.createVerticalStrut(25)
        );


        panel.add(
                expenseLabel
        );


        panel.add(
                Box.createVerticalStrut(10)
        );


        panel.add(
                budgetProgress
        );


        checkButton.addActionListener(
                e -> calculateBudget()
        );


        return panel;
    }


    private void calculateBudget() {

        try {

            double budget =
                    Double.parseDouble(
                            budgetField.getText()
                    );


            if (budget <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Budget must be greater than zero."
                );

                return;
            }


            double expense = 0;


            for (Subscription sub :
                    subscriptions) {

                if (!sub.getStatus()
                        .equalsIgnoreCase(
                                "Cancelled"
                        )) {

                    expense +=
                            sub.getMonthlyCost();
                }
            }


            double percentage =
                    (expense / budget) * 100;


            if (percentage > 100)
                percentage = 100;


            if (percentage < 0)
                percentage = 0;


            budgetProgress.setValue(
                    (int) percentage
            );


            budgetProgress.setString(
                    String.format(
                            "%.0f%% Used",
                            percentage
                    )
            );


            expenseLabel.setText(
                    String.format(
                            "Monthly Expense: %.2f",
                            expense
                    )
            );


        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid budget."
            );
        }
    }


    // =========================================
    // REPORT
    // =========================================

    private JPanel createReportPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );


        reportArea =
                new JTextArea();


        reportArea.setEditable(
                false
        );


        reportArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        15
                )
        );


        JButton generateButton =
                new JButton(
                        "Generate Report"
                );


        generateButton.addActionListener(
                e -> generateReport()
        );


        panel.add(
                new JScrollPane(
                        reportArea
                ),
                BorderLayout.CENTER
        );


        panel.add(
                generateButton,
                BorderLayout.SOUTH
        );


        // Generate report when the tab is opened
        generateReport();


        return panel;
    }


    private void generateReport() {

        int total =
                subscriptions.size();


        int active = 0;

        int cancelled = 0;

        double expenses = 0;


        for (Subscription sub :
                subscriptions) {


            if (sub.getStatus()
                    .equalsIgnoreCase(
                            "Cancelled"
                    )) {

                cancelled++;

            } else {

                active++;

                expenses +=
                        sub.getMonthlyCost();
            }
        }


        Report report =
                new Report(
                        total,
                        active,
                        cancelled,
                        expenses
                );


        reportArea.setText(

                "========== SUBSCRIPTION REPORT ==========\n\n"

                + "Total Subscriptions     : "
                + report.getTotalSubscription()
                + "\n"

                + "Active Subscriptions    : "
                + report.getActiveSubscription()
                + "\n"

                + "Cancelled Subscriptions: "
                + report.getCancelledSubscription()
                + "\n"

                + String.format(
                        "Total Monthly Expenses  : %.2f\n",
                        report.getTotalExpenses()
                )

                + "\n=========================================="
        );
    }


    private void refreshReport() {

        if (reportArea != null) {

            generateReport();
        }
    }
}