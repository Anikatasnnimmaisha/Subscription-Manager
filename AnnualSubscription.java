
public class AnnualSubscription extends Subscription implements Cancelable {
	public AnnualSubscription() {
        super();
    }

    public AnnualSubscription(String name, String id, String method,String status, String startDate,
                              boolean autoRenewal, int duration, double cost) {

        super(name, id, method, status, startDate, autoRenewal, duration, cost);
    }

    @Override
    public double getMonthlyCost() {
        return getCost() / 12.0;
    }

    @Override
    public void displayInfo() {
        System.out.println("========== Annual Subscription ==========");
        super.displayInfo();
        System.out.printf("Monthly Cost  : %.2f%n", getMonthlyCost());
    }

    @Override
    public void cancelSubscription() {
        if (isCancelable()) {
            setStatus("Cancelled");
            setAutoRenewal(false);
            System.out.println("Subscription \"" + getSubscriptionName()+ "\" has been cancelled successfully.");
        } else {
            System.out.println("This subscription is already cancelled.");
        }
    }

    @Override
    public boolean isCancelable() {
        return !getStatus().equalsIgnoreCase("Cancelled");
    }
    
    @Override
    public String getCancellationSteps() {
        return "To cancel this subscription, type YES when asked for cancellation confirmation.";
    }




}
