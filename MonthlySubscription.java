
public class MonthlySubscription extends Subscription implements Cancelable {
	public MonthlySubscription() {
        super();
    }

    public MonthlySubscription(String name, String id, String method,String status, String startDate,
                                boolean autoRenewal,int duration, double cost) 
    {

        super(name, id, method, status, startDate,
              autoRenewal, duration, cost);
    }

    @Override
    public double getMonthlyCost() {
        return getCost();
    }

    @Override
    public void cancelSubscription() {

        if (!isCancelable()) {
            System.out.println("Subscription is already cancelled.");
            return;
        }

        setStatus("Cancelled");
        setAutoRenewal(false);

        System.out.println("Subscription \"" + getSubscriptionName()+ "\" has been cancelled.");
    }

    
    @Override
    public boolean isCancelable() {
        return !getStatus().equalsIgnoreCase("Cancelled");
    }

    @Override
    public void displayInfo() {
        System.out.println("========== MONTHLY SUBSCRIPTION ==========");
        super.displayInfo();
        System.out.printf("Monthly Cost : %.2f%n", getMonthlyCost());
    }
    
    
    @Override
    public String getCancellationSteps() {
        return "To cancel this subscription, type YES when asked for cancellation confirmation.";
    }



}