package alvi.tanmoy.basis.org.bd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
	
	private String orgName;
	private String representPerson;
	private String personDesignation;
	private String email;
	private String contactNo;
	private String address;
	private String numberOfHr;
	private String orgUrl;
	
	public Organization(String url){
		this.orgUrl = url;
	}
}
