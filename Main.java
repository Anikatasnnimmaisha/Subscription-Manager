import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Subscription> subscriptions = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n====================================");
            System.out.println("       SUBSCRIPTION MANAGER         ");
            System.out.println("====================================");
            System.out.println("1. Add Subscription");
            System.out.println("2. Display All Subscriptions");
            System.out.println("3. Check Budget Progress");
            System.out.println("4. Cancel a Subscription");
            System.out.println("5. Display Summary Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> addSubscription(sc, subscriptions);
                case 2 -> displayAllSubscriptions(subscriptions);
                case 3 -> checkBudget(sc, subscriptions);
                case 4 -> cancelSubscriptionMenu(sc, subscriptions);
                case 5 -> generateReport(subscriptions);
                case 6 -> {
                    running = false;
                    System.out.println("Exiting Subscription Manager. Goodbye!");
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
        sc.close();
    }

    private static void addSubscription(Scanner sc, List<Subscription> subscriptions) {
        System.out.println("\nSelect Subscription Type:");
        System.out.println("1. Monthly Subscription");
        System.out.println("2. Annual Subscription");
        System.out.print("Type (1 or 2): ");
        int type = sc.nextInt();
        sc.nextLine();

        System.out.print("Subscription Name: ");
        String name = sc.nextLine();

        System.out.print("Subscription ID: ");
        String id = sc.nextLine();

        System.out.print("Payment Method: ");
        String method = sc.nextLine();

        System.out.print("Status: ");
        String status = sc.nextLine();

        System.out.print("Start Date: ");
        String startDate = sc.nextLine();

        System.out.print("Auto Renewal (true/false): ");
        boolean autoRenewal = sc.nextBoolean();

        System.out.print(type == 1 ? "Duration (months): " : "Duration (years): ");
        int duration = sc.nextInt();

        System.out.print("Cost: ");
        double cost = sc.nextDouble();
        sc.nextLine(); 

        if (type == 1) {
            subscriptions.add(new MonthlySubscription(name, id, method, status, startDate, autoRenewal, duration, cost));
            System.out.println("Monthly subscription added successfully!");
        } else if (type == 2) {
            subscriptions.add(new AnnualSubscription(name, id, method, status, startDate, autoRenewal, duration, cost));
            System.out.println("Annual subscription added successfully!");
        } else {
            System.out.println("Invalid type! Subscription not added.");
        }
    }

    private static void displayAllSubscriptions(List<Subscription> subscriptions) {
        if (subscriptions.isEmpty()) {
            System.out.println("\nNo subscriptions recorded yet.");
            return;
        }
        for (Subscription sub : subscriptions) {
            System.out.println();
            sub.displayInfo();
        }
    }

    private static double calculateTotalMonthlyExpenses(List<Subscription> subscriptions) {
        double total = 0;
        for (Subscription sub : subscriptions) {
            if (!sub.getStatus().equalsIgnoreCase("Cancelled")) {
                total += sub.getMonthlyCost();
            }
        }
        return total;
    }

    private static void checkBudget(Scanner sc, List<Subscription> subscriptions) {
        double totalExpense = calculateTotalMonthlyExpenses(subscriptions);
        System.out.printf("%nTotal Monthly Expense (Active Subscriptions): %.2f%n", totalExpense);
        System.out.print("Enter your monthly budget limit: ");
        double budget = sc.nextDouble();
        sc.nextLine();

        new BudgetProgressBar(budget, totalExpense);
    }

    private static void cancelSubscriptionMenu(Scanner sc, List<Subscription> subscriptions) {
        if (subscriptions.isEmpty()) {
            System.out.println("\nNo subscriptions available to cancel.");
            return;
        }

        System.out.print("\nEnter the Subscription ID to cancel: ");
        String id = sc.nextLine();

        Subscription found = null;
        for (Subscription sub : subscriptions) {
            if (sub.getSubscriptionId().equalsIgnoreCase(id)) {
                found = sub;
                break;
            }
        }

        if (found == null) {
            System.out.println("Subscription ID not found!");
            return;
        }

        if (found instanceof Cancelable cancelable) {
            System.out.println("\nCancellation Steps:");
            System.out.println(cancelable.getCancellationSteps());
            System.out.print("Confirm cancellation (yes/no): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                cancelable.cancelSubscription();
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("This subscription cannot be cancelled.");
        }
    }

    private static void generateReport(List<Subscription> subscriptions) {
        int total = subscriptions.size();
        int active = 0;
        int cancelled = 0;
        double totalExpenses = 0;

        for (Subscription sub : subscriptions) {
            if (sub.getStatus().equalsIgnoreCase("Cancelled")) {
                cancelled++;
            } else {
                active++;
                totalExpenses += sub.getMonthlyCost();
            }
        }

        Report report = new Report(total, active, cancelled, totalExpenses);
        report.displayInfo();
    }
}