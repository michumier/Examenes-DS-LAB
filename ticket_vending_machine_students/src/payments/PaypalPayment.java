package payments;

import machine.hardware.Display;
import machine.hardware.Keyboard;
import paypal.PayPalService;


// PATRÓN ADAPTER: CLIENTE
public class PaypalPayment implements Payment {
    private Display display = new Display();
    private Keyboard keyboard = new Keyboard();
    private PayPalService paypalService = new PayPalService();

    @Override
    public boolean pay(double amount) {
        display.show("Iniciando sesión en PayPal...");
        display.show("Por favor, introduzca su nombre de usuario: ");
        String user = keyboard.readString();
        display.show("Por favor, introduzca su contraseña: ");
        String password = keyboard.readString();

        // Intentar iniciar sesión
        String token = paypalService.logIn(user, password);
        if (!paypalService.isLogged(token)) {
            display.show("Nombre de usuario o contraseña incorrectas");
            return false;
        }

        // Intentar realizar el pago
        boolean paymentSuccess = paypalService.checkout(token, amount);
        if (!paymentSuccess) {
            display.show("No se pudo completar el pago con PayPal.");
            return false;
        }

        display.show("Pago realizado con éxito.");
        return true;
    }
}
