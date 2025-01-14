package discounts;

// COMPONENTE CONCRETO
public class BaseDiscount implements Discount {
	@Override
	public double applyDiscount(double amount, int numberOfTickets) {
		return amount; // Sin descuento
	}
}