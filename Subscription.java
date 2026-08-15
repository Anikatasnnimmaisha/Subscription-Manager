
public abstract class Subscription {
	private String subscriptionName;
	private String subscriptionId;
	private String paymentMethod;
	private String status;
	private String startDate;
	private boolean autoRenewal;
	private int duration;
	private double cost;
	
	public Subscription() {
		
	}
	
    public Subscription(String name,String id,String method,String status,String startDate,boolean autoRenewal,int duration,double cost){
    
    this.subscriptionName=name;
    this.subscriptionId=id;
    this.paymentMethod=method;
    this.status=status;
    this.startDate=startDate;
    this.autoRenewal=autoRenewal;
    this.duration=duration;
    this.cost=cost;
		
	}
    
    
    public String getSubscriptionName() {
    	
    	return this.subscriptionName;
    }
    
    
    public String getSubscriptionId() {
    	
    	return this.subscriptionId;
    }
    
    
    public String getPaymentMethod() {
    	
    	return this.paymentMethod;
    }
    
    
    public String getStatus() {
    	
    	return this.status;
    }
    
    
    public String getStartDate() {
    	
    	return this.startDate;
    }
    
    
    public boolean getAutoRenewal() {
    	
    	return this.autoRenewal;
    }
    
    
    public int getDuration() {
    	
    	return this.duration;
    }
    
    
    public double getCost() {
    	
    	return this.cost;
    }

    
    
     public void setSubscriptionName(String name) {
    	
    	 this.subscriptionName=name;
    }
    
     
     public void setSubscriptionId(String id) {
     	
     	 this.subscriptionId=id;
     }
     
     
     public void setPaymentMethod(String method) {
     	
     	 this.paymentMethod=method;
     }
     
     
     public void setStatus(String status) {
     	
     	 this.status=status;
     }
     
     
     public void setStartDate(String startDate) {
     	
     	 this.startDate=startDate;
     }
     
     
     
     public void setAutoRenewal(boolean autoRenewal) {
     	
     	 this.autoRenewal=autoRenewal;
     }
     
     
     public void setDuration(int duration) {
     	
     	 this.duration=duration;
     }
     
     
     public void setCost(double cost) {
     	
     	 this.cost=cost;
     }
     
     
     public abstract double getMonthlyCost();
     
     public void displayInfo() {
    
    	 System.out.println("Service Name  : "+this.subscriptionName);
    	 System.out.println("ID            : "+this.subscriptionId);
    	 System.out.println("Payment Method: "+this.paymentMethod);
    	 System.out.println("Status        : "+this.status);
    	 System.out.println("Start Date    : "+this.startDate);
    	 System.out.println("Auto Renewal  : "+this.autoRenewal);
    	 System.out.println("Duration      : "+this.duration);
    	
     }
     


}
