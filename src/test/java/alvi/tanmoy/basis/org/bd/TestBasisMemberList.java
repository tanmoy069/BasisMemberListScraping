package alvi.tanmoy.basis.org.bd;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBasisMemberList {

	private static final String URL = "https://basis.org.bd/index.php/site/memberList/";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IOException {
		Document doc = Jsoup.connect(URL).get();
		Elements rowList = doc.select("div[class=col-xs-12 col-md-3]>a");
		System.out.println(rowList.size());
		for (Element element : rowList) {
			System.out.println(element.attr("href"));
		}
	}

	@Test
	public void testDetail() throws IOException {
		Document doc = Jsoup.connect("https://basis.org.bd/index.php/site/memberProfile/1667").get();
		Elements el = doc.select("table[style=margin-bottom:1px;]>tbody");
		System.out.println(el.size());
//		Elements orgEl = el.get(0).getElementsByTag("tr").select("td[class=bodytext]");
//		System.out.println(orgEl.size());
		for (int i = 0; i < el.size(); i++) {
			Elements orgEl = el.get(i).getElementsByTag("tr").select("td[class=bodytext]");
			{
				for (int j = 1; j < orgEl.size() - 1; j++) {
					if (orgEl.get(j).text().equals("Company Name"))
						System.out.println("Company Name: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Address"))
						System.out.println("Address: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("E-mail"))
						System.out.println("E-mail: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().equalsIgnoreCase("Organization�s head in Bangladesh"))
						System.out.println("Head in Bangladesh: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Designation"))
						System.out.println("Designation: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Mobile/Direct Phone"))
						System.out.println("Mobile/Direct Phone: " + orgEl.get(j + 1).text());
					if (orgEl.get(j).text().contains("Total number of HR"))
						System.out.println("Total number of HR: " + orgEl.get(j + 1).text());
				}
			}
		}
	}

}
