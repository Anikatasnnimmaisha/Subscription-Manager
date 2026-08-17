
public class Report {

    private int totalSubscription;
    private int activeSubscription;
    private int cancelledSubscription;
    private double totalExpenses;

    public Report() {
    }

    public Report(int total, int active,
                  int cancel, double expense) {

        this.totalSubscription = total;
        this.activeSubscription = active;
        this.cancelledSubscription = cancel;
        this.totalExpenses = expense;
    }

    public void displayInfo() {

        System.out.println(
                "\n========== SUBSCRIPTION REPORT =========="
        );

        System.out.println(
                "Total Subscriptions    : "
                + totalSubscription
        );

        System.out.println(
                "Active Subscriptions   : "
                + activeSubscription
        );

        System.out.println(
                "Cancelled Subscriptions: "
                + cancelledSubscription
        );

        System.out.printf(
                "Total Monthly Expenses : %.2f%n",
                totalExpenses
        );

        System.out.println(
                "========================================="
        );
    }

    public int getTotalSubscription() {
        return totalSubscription;
    }

    public int getActiveSubscription() {
        return activeSubscription;
    }

    public int getCancelledSubscription() {
        return cancelledSubscription;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }
}