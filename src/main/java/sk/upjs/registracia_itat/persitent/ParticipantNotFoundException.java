package sk.upjs.registracia_itat.persitent;

public class ParticipantNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4188159083083625503L;

	private long participantId;

	public ParticipantNotFoundException(long participantId) {
		this.participantId = participantId;
	}

	public long getParticipantId() {
		return participantId;
	}
}
