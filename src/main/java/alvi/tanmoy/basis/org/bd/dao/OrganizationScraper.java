package alvi.tanmoy.basis.org.bd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alvi.tanmoy.basis.org.bd.DBConnection.DBConnection;
import alvi.tanmoy.basis.org.bd.model.Organization;

public class OrganizationScraper extends DBConnection {

	private int count = 0;
	
	private static Connection con;
	public void saveOrg(Organization ob) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		con = getDBConnection();
		String sql = "INSERT INTO BASIS_MEMBER_LIST "
				+ "(org_name, person_name, designation, email, phone, org_address, no_of_hr, org_url) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
		 
        try {
        	PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, ob.getOrgName());
            pstmt.setString(2, ob.getRepresentPerson());
            pstmt.setString(3, ob.getPersonDesignation());
            pstmt.setString(4, ob.getEmail());
            pstmt.setString(5, ob.getContactNo());
            pstmt.setString(6, ob.getAddress());
            pstmt.setString(7, ob.getNumberOfHr());
            pstmt.setString(8, ob.getOrgUrl());
            pstmt.executeUpdate();
    		System.out.println("Scraped["+ ++count +"]: Org added successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void findOrgById(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		con = getDBConnection();
		String query = "select * from BASIS_MEMBER_LIST where org_id = " + id;
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(toString(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String toString(ResultSet rs) throws SQLException {
		return rs.getInt("org_id") + " " + rs.getString("org_name") + " " + rs.getString("person_name") + " " + rs.getString("designation") + 
				" " + rs.getString("email") + " " + rs.getString("phone") + " " + rs.getString("org_address") +
				" " + rs.getString("no_of_hr") + " " + rs.getString("org_url");
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		OrganizationScraper org = new OrganizationScraper();
//		Organization or = new Organization("www.facebook.com");
//		or.setOrgName("Facebook");
//		or.setRepresentPerson("Alvi");
//		or.setPersonDesignation("Member");
//		org.saveOrg(or);
		org.findOrgById(1);
	}
}
