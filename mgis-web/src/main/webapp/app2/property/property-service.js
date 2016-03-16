angular.module("mgis.property.service", [])
	.factory("MGISPropertyRightsService", function () {
		return {
			buildDocumentsCertifyingRights: function (rights) {
				if (rights.documentsCertifyingRights && rights.documentsCertifyingRights.length) {
					return [].concat(rights.documentsCertifyingRights);
				}
				return null;
			},
			buildRegistrationDocuments: function (rights) {
				if (rights.registrationDocuments && rights.registrationDocuments.length) {
					return [].concat(rights.registrationDocuments);
				}
				return null;
			},
			buildOtherDocuments: function (rights) {
				if (rights.otherDocuments && rights.otherDocuments.length) {
					return [].concat(rights.otherDocuments);
				}
				return null;
			},
			buildRights: function (rights) {
				var rights2 = {};
				var right2 = {};
				var rights_rights = {};
				rights2.rights = {};
				if (rights) {
					rights_rights = rights.rights;
					if(rights_rights) {
						for(var index in rights_rights) {
							var right = rights_rights[index];
							right2 = {
								ownershipForm: right.ownershipForm ? {id: right.ownershipForm.id} : null,
								rightKind: right.rightKind ? {id: right.rightKind.id} : null,
								rightOwner: right.rightOwner ? {id: right.rightOwner.id} : null,
								obligations: right.obligations,
								ownershipDate: right.ownershipDate,
								terminationDate: right.terminationDate,
								comment: right.comment,
								share: right.share,
								annualTax: right.annualTax,
								totalArea: right.totalArea,
								registrationDocuments: this.buildRegistrationDocuments(right),
								documentsCertifyingRights: this.buildDocumentsCertifyingRights(right),
								otherDocuments: this.buildOtherDocuments(right)
							}
							rights2.rights.push(right2);
						}
					}
				} else {
					rights2 = {};
				}
				return rights2;
			}
		}
	})
;
