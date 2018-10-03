package sk.upjs.registracia_itat;

import java.time.LocalDateTime;
import java.util.List;

public class Participant {
	
	public static final double SINGLE_ROOM_FEE = 75.0;
	public static final double[] COMPANION_FEES = new double[]{60.0, 110.0, 165.0, 210.0};
	public static final double BANKET_FEE = 20.0;
	public static final LocalDateTime BANKET_DATETIME 
	 = LocalDateTime.of(2019, 9, 23, 19, 0);
	
	private String name;
	private String surname;
	private String email;
	private String organization;
	private String address;
	private Long ico;
	private String dic;
	private boolean early;
	private Tshirt tshirt;
	private boolean student;
	private boolean singleRoom;
	private LocalDateTime start;
	private LocalDateTime end;
	private List<Companion> companions;
	private WorkShop workshop;
	private boolean cash;
	
	public double finalPrice() {
		double price = 0.0;
		if (this.early) {
			if (this.student) {
				price = workshop.getPriceStudent();
			} else {
				price = workshop.getPriceFull();
			}
		} else {
			if (this.student) {
				price = workshop.getPriceStudentLate();
			} else {
				price = workshop.getPriceFullLate();
			}
		}
		
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getIco() {
		return ico;
	}

	public void setIco(Long ico) {
		this.ico = ico;
	}

	public String getDic() {
		return dic;
	}

	public void setDic(String dic) {
		this.dic = dic;
	}

	public boolean isEarly() {
		return early;
	}

	public void setEarly(boolean early) {
		this.early = early;
	}

	public Tshirt getTshirt() {
		return tshirt;
	}

	public void setTshirt(Tshirt tshirt) {
		this.tshirt = tshirt;
	}

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

	public boolean isSingleRoom() {
		return singleRoom;
	}

	public void setSingleRoom(boolean singleRoom) {
		this.singleRoom = singleRoom;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public List<Companion> getCompanions() {
		return companions;
	}

	public void setCompanions(List<Companion> companions) {
		this.companions = companions;
	}

	public WorkShop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(WorkShop workshop) {
		this.workshop = workshop;
	}

	public boolean isCash() {
		return cash;
	}

	public void setCash(boolean cash) {
		this.cash = cash;
	}
}
