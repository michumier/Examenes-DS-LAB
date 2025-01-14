package payments;

import machine.hardware.CardReader;
import machine.hardware.Display;
import ruralvia.BankGateway;

// ESTRATEGIAS CONCRETAS
public class CardPayment implements Payment{
	
	private Display display = new Display();
	private CardReader cardReader = new CardReader();
	private BankGateway bankGateway = new BankGateway();


	@Override
	public boolean pay(double amount) {
		String cardNumber = cardReader.readCardNumber();
		boolean isValid = bankGateway.pay(cardNumber, amount);
		if (!isValid) {
			display.show("Tarjeta rechazada\n");
			return false;
		}
		// pago correcto
		display.show("Tarjeta aceptada: pago realizado con éxito\n");
		return true;
	}

}
