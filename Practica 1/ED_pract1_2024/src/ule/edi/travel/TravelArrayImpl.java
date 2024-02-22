package ule.edi.travel;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.*;

public class TravelArrayImpl implements Travel {
	
	private static final Double DEFAULT_PRICE = 100.0;
	private static final Byte DEFAULT_DISCOUNT = 25;
	private static final Byte CHILDREN_EXMAX_AGE = 18;
	private Date travelDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
	
   public TravelArrayImpl(Date date, int nSeats){
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en esta clase
	   //debe crear el array de asientos
	   this.nSeats = nSeats;
	   seats = new Seat[this.nSeats];
	   travelDate = date;
	   this.price = DEFAULT_PRICE;
	   this.discountAdvanceSale = DEFAULT_DISCOUNT;
   }
   
   
   public TravelArrayImpl(Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear el array de asientos
	   this.nSeats = nSeats;
	   this.seats = new Seat[this.nSeats];
	   this.travelDate = date;
	   this.price = price;
	   this.discountAdvanceSale = discount;

   }






@Override
public Byte getDiscountAdvanceSale() {
	// TODO Auto-generated method stub
	return this.discountAdvanceSale;
}


@Override
public int getNumberOfSoldSeats() {
	// TODO Auto-generated method stub
	return this.getNumberOfAdvanceSaleSeats()+this.getNumberOfNormalSaleSeats();
}


@Override
public int getNumberOfNormalSaleSeats() {
	// TODO Auto-generated method stub
	int contador = 0;
	for(int i = 0;i<this.seats.length;i++){
		if (this.seats[i]!= null){
			if(this.seats[i].getAdvanceSale()!=false){
				contador++;
			}	
		}
	}
	return contador;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	// TODO Auto-generated method stub
	int contador = 0;
	for(int i = 0;i<this.seats.length;i++){
		if (this.seats[i]!= null){
			if(this.seats[i].getAdvanceSale()){
				contador++;
			}	
		}
		
	}
	return contador;
}


@Override
public int getNumberOfSeats() {
	// TODO Auto-generated method stub
	return this.seats.length;
}


@Override
public int getNumberOfAvailableSeats() {
	// TODO Auto-generated method stub
	int contador=0;
	for (int i = 0 ; i < this.getNumberOfSeats() ;i++){
		if (seats[i] == null){
			contador++;
		}
	}
	return contador;
}

@Override
public Seat getSeat(int pos) {
	// TODO Auto-generated method stub
	int poss =  pos - 1;
	return this.seats[poss];
}


@Override
public Person refundSeat(int pos) {
	// TODO Auto-generated method stub
	return (this.seats[pos].getHolder()!=null)? this.seats[pos].getHolder():null;
}



private boolean isChildren(int age) {
	// TODO Auto-generated method stub
	return age<18;
}

private boolean isAdult(int age) {
	// TODO Auto-generated method stub
	return age>=18;
}


@Override
public List<Integer> getAvailableSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	
	
	return lista;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	// TODO Auto-generated method stub
	List<Integer> lista=new ArrayList<Integer>(nSeats);
	
	
	return lista;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	// TODO Auto-generated method stub
	int contador=0;
	for (int i = 0;i<=this.getNumberOfSeats();i++){
		if(this.seats[i].getHolder()==null){
			contador++;
		}else{
			break;
		}
	}
	return contador;
}



//preguntar como hacer par resolver el problema de que tengo que poner un return false.
@Override
public boolean isAdvanceSale(Person p) {
	// TODO Auto-generated method stub
	for (int i = 0;i<=this.getNumberOfSeats();i++){
		if(this.seats[i].getHolder()==p){
			return (this.seats[i].getAdvanceSale()==true)? true:false;
		}
	}
	return false;
}


@Override
public Date getTravelDate() {
	// TODO Auto-generated method stub
	return this.travelDate;
}


@Override
public boolean sellSeatPos(int pos, String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	if(pos<=0){
		return false;
	}
	int poss = pos - 1;
	Person person = new Person(nif, name, edad);
	Seat seat = new Seat(isAdvanceSale, person);
	if(this.seats[poss]==null){
		this.seats[poss]=seat;
		return true;
	}else{
		return false;
	}
}


@Override
public int getNumberOfChildren() {
	// TODO Auto-generated method stub
	int contador = 0;
	for (int i = 0; i < this.getNumberOfSeats(); i++ ){
		if(this.seats[i]!= null){
			if (this.isChildren(this.seats[i].getHolder().getAge())){
				contador++;
			}
		}	
	}
	return contador;
}


@Override
public int getNumberOfAdults() {
	// TODO Auto-generated method stub
	int contador = 0;
	for (int i = 0; i < this.getNumberOfSeats(); i++ ){
		if(this.seats[i]!= null){
			if (this.isAdult(this.seats[i].getHolder().getAge())){
				contador++;
			}
		}
	}
	return contador;
}



@Override
public Double getCollectionTravel() {
	// TODO Auto-generated method stub
	Double recaudadoVA = (double)this.getNumberOfAdvanceSaleSeats() * (this.price-this.getDiscountAdvanceSale());
	Double recaudadoVN = (double)this.getNumberOfNormalSaleSeats() * this.price;
	Double recaudado = recaudadoVA + recaudadoVN;
	return recaudado;
}

//hechar un vistazo a ese brake para hacerlo de otra forma
@Override
public int getPosPerson(String nif) {
	// TODO Auto-generated method stub
	int contador = 1;
	for(int i = 0; i <= this.getNumberOfSeats(); i++){
		if (nif != this.seats[i].getHolder().getNif()){
			contador++;
		}else {
			break;
		}
	}
	return contador;	
}


@Override
public int sellSeatFrontPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	int contador = -1;
	for(int i = 0; i <= this.getNumberOfSeats(); i++){
		if (this.seats[i]==null){
			contador = i;
			break;
		}
	}
	if(contador==-1){
		return contador;
	}else{
		Person person = new Person(nif, name, edad);
		Seat seat = new Seat(isAdvanceSale, person);
		this.seats[contador]=seat;
	}
	return contador+1;
}


@Override
public int sellSeatRearPos(String nif, String name, int edad, boolean isAdvanceSale) {
	// TODO Auto-generated method stub
	int contador = -1;
	for(int i = this.getNumberOfSeats(); i == 0; i--){
		if (this.seats[i]==null){
			contador = i;
			break;
		}
	}
	if(contador==-1){
		return contador;
	}else{
		Person person = new Person(nif, name, edad);
		Seat seat = new Seat(isAdvanceSale, person);
		this.seats[contador]=seat;
	}
	return contador+1;
}




@Override
public Double getSeatPrice(Seat seat) {
	// TODO Auto-generated method stub
	if(seat.getAdvanceSale()){
		return this.price-this.getDiscountAdvanceSale();
	}else {
		return this.price;
	}
}


@Override
public double getPrice() {
	return this.price;
}


}	