package discounts;


// PATRÓN DECORATOR: COMPONENTE Y DECORADOR (NO HAY MAS COMPONENTES POR ESO SON LO MISMO)
public interface Discount {

	double applyDiscount(double price, int numberOfTickets);
}
