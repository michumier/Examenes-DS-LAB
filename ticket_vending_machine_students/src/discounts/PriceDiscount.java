package discounts;

public class PriceDiscount implements Discount{

	private static final double DESCUENTO = 15;
	
	
	@Override
	public double applyDiscount(double price, int number) {
		double res = price - DESCUENTO;
		return res > 0 ? res : 0;
	}

}
