package ru.sovzond.mgis2.capital_constructs.rights;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.property.rights.PropertyRights;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.stream.Collectors;

@Entity
@Table(name = "oks_right")
@OnDelete(action = OnDeleteAction.CASCADE)
public class ConstructionRight extends PropertyRights {

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public ConstructionRight clone() {
		ConstructionRight rights = new ConstructionRight();
		rights.setId(getId());
		rights.setOwnershipDate(getOwnershipDate());
		rights.setTerminationDate(getTerminationDate());
		rights.setRightOwner(getRightOwner() != null ? getRightOwner().clone() : null);
		rights.setRightKind(getRightKind() != null ? getRightKind().clone() : null);
		rights.setOwnershipForm(getOwnershipForm() != null ? getOwnershipForm().clone() : null);
		rights.setShareNumerator(getShareNumerator());
		rights.setShareDenominator(getShareDenominator());
		rights.setRegistrationDocuments(getRegistrationDocuments() != null ? getRegistrationDocuments().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setDocumentsCertifyingRights(getDocumentsCertifyingRights() != null ? getDocumentsCertifyingRights().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setOtherDocuments(getOtherDocuments() != null ? getOtherDocuments().stream().map(document -> new Document(document.getId(), document.getName())).collect(Collectors.toList()) : null);
		rights.setComment(getComment());
		rights.setObligations(isObligations());
		rights.setAnnualTax(getAnnualTax());
		rights.setTotalArea(getTotalArea());
		return rights;
	}

}
