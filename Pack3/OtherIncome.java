package Pack3;

import Pack1.Income;

public class OtherIncome extends Income {
    public OtherIncome(String source, double amount, String date) {
        super(source, amount, date);
    }

    @Override
    public void display() {
        System.out.println("Other Income from: " + source);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date);
    }
}