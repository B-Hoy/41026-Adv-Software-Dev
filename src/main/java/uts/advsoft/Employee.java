package uts.advsoft;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Employee{
	int id;
	String role, first_name, last_name, password;
	LocalDate hire_date;
	public Employee(int id, String role, String first_name, String last_name, String password, String cur_date){
		this.id = id;
		this.role = role;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.hire_date = LocalDate.parse(cur_date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	public int get_id(){
		return id;
	}
	public String get_role(){
		return role;
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
	public String get_hire_date(){
		return hire_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	public String toString(){
		return Integer.toString(id) + ": " + first_name + " " + last_name;
	}
}
