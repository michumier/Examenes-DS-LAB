package discounts;

// DECORATOR CONCRETO
public class PercentageDiscount implements Discount {

	private static final double DESCUENTO = 20;

	@Override
	public double applyDiscount(double price, int number) {
		return price - (price * DESCUENTO);
	}

}
