package payments;

import machine.hardware.BillAcceptor;
import machine.hardware.Display;


public class CashPayment implements Payment{
	
	private Display display = new Display();

	private BillAcceptor billAcceptor = new BillAcceptor();

	@Override
	public boolean pay(double amount) {
		int insertedAmount = 0;
		boolean cancel = false;
		billAcceptor.reset();
		do {
			display.show(String.format("Quedan por pagar %.2f €%n", amount - insertedAmount));
			double inserted = billAcceptor.insertBill();
			insertedAmount = billAcceptor.getTotalAmount();
			if (inserted == 0)
				cancel = true;
		} while (insertedAmount < amount && !cancel);
		
		if (cancel) {
			display.show("Operación cancelada por el usuario\n");
			// si antes de cancelar había introducido dinero, se le devuelve
			if (insertedAmount > 0)
				billAcceptor.returnChange(insertedAmount);
			return false;
		}
		display.show("Pago completado\n");
		// se devuelve el cambio
		if (insertedAmount > amount) {
			billAcceptor.returnChange(insertedAmount - amount);
		} 
		return true;
	}

}
