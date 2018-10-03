package sk.upjs.registracia_itat;

import java.time.LocalDateTime;

public class Companion {
	private CompanionCategory category;
	private LocalDateTime start;
	private LocalDateTime end;
	private Tshirt tshirt;

	public CompanionCategory getCategory() {
		return category;
	}
	public void setCategory(CompanionCategory category) {
		this.category = category;
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
	public Tshirt getTshirt() {
		return tshirt;
	}
	public void setTshirt(Tshirt tshirt) {
		this.tshirt = tshirt;
	}
}
