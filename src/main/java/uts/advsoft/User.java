package uts.advsoft;
public class User{
	int id, card_cvc, address_postcode;
	String email, first_name, last_name, password, phone_num, register_date, card_num, card_expiry_date, address_street_num, address_street, address_city;

	public User(int id, String email, String first_name, String last_name, String password, String phone_num, String register_date, String card_num, String card_expiry_date, int card_cvc, String address_street_num, String address_street, String address_city, int address_postcode){
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.phone_num = phone_num;
		this.register_date = register_date;
		this.card_num = card_num;
		this.card_expiry_date = card_expiry_date;
		this.card_cvc = card_cvc;
		this.address_street_num = address_street_num;
		this.address_street = address_street;
		this.address_city = address_city;
		this.address_postcode = address_postcode;
	}
	public int get_id(){
		return id;
	}
	public String get_email(){
		return email;
	}
	public String get_first_name(){
		return first_name;
	}
	public String get_last_name(){
		return last_name;
	}
	public String get_password(){
		return password;
	}
	public String get_phone_num(){
		return phone_num;
	}
	public String get_register_date(){
		return register_date;
	}
	public String get_card_num(){
		return card_num;
	}
	public String get_card_expiry_date(){
		return card_expiry_date;
	}
	public int get_card_cvc(){
		return card_cvc;
	}
	public String get_address_street_num(){
		return address_street_num;
	}
	public String get_address_street(){
		return address_street;
	}
	public String get_address_city(){
		return address_city;
	}
	public int get_address_postcode(){
		return address_postcode;
	}
}
