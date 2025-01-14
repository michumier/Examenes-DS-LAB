package discounts;

public class FreeDiscount implements Discount {

	@Override
	public double applyDiscount(double price, int number) {
		return number > 0 ? price - (price/ number) : price;
		
	}

}
