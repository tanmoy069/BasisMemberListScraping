package alvi.tanmoy.basis.org.bd.scraper;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import alvi.tanmoy.basis.org.bd.dao.OrganizationScraper;
import alvi.tanmoy.basis.org.bd.model.Organization;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasisMemberList extends OrganizationScraper {

	private static final String URL = "https://basis.org.bd/index.php/site/memberList/";

	public void getMemberList() throws IOException {
		int count = 0;
		for (int i = 0; i < 61; i++) {
			Document doc = Jsoup.connect(URL + count).timeout(20000).get();
			System.out.println("Scrapping on page: " + (URL + count));
			Elements rowList = doc.select("div[class=col-xs-12 col-md-3]>a");
			for (Element element : rowList) {
				String orgUrl = element.attr("href");
				try {
					saveOrg(getOrgDetail(orgUrl));
				} catch (Exception e) {
					log.warn("Failed to parse org detail of " + orgUrl, e);
				}
			}
			count += 20;
		}
		System.out.println("Site Scrapping Finished");
	}

	private Organization getOrgDetail(String url) throws IOException {
		Organization org = new Organization(url);
		Document doc = Jsoup.connect(org.getOrgUrl()).get();
		Elements el = doc.select("table[style=margin-bottom:1px;]>tbody");
		for (int i = 0; i < el.size(); i++) {
			Elements orgEl = el.get(i).getElementsByTag("tr").select("td[class=bodytext]");
			{
				for (int j = 0; j < orgEl.size() - 1; j++) {
					if (orgEl.get(j).text().equals("Company Name"))
						org.setOrgName(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().equals("Address") && !orgEl.get(j).text().contains("N/A"))
						if(org.getAddress() == null) org.setAddress(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("E-mail") && !orgEl.get(j).text().contains("N/A"))
						org.setEmail(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().equalsIgnoreCase("Organization�s head in Bangladesh"))
						org.setRepresentPerson(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().equals("Name") && !orgEl.get(j).text().contains("N/A") && !orgEl.get(j).text().contains("Designation"))
						if(orgEl.get(j + 1).text() != null) org.setRepresentPerson(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Designation"))
						org.setPersonDesignation(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().equals("Mobile/Direct Phone") || orgEl.get(j).text().equals("Mobile"))
						org.setContactNo(orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Total number of HR") && !orgEl.get(j).text().contains("N/A"))
						org.setNumberOfHr(orgEl.get(j + 1).text());
				}
			}
		}
		return org;
	}
	
//	public static void main(String[] args) throws IOException {
//		BasicConfigurator.configure();
//		BasisMemberList bml = new BasisMemberList();
//		bml.getMemberList();
//	}
}
