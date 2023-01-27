package lichsuvietnam.webscraper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lichsuvietnam.models.HistoricalSite;

public class HistoricalSiteScraper {
	public void getData(String url, String output) {
		try {
			Document document = Jsoup.connect(url).userAgent("Jsoup client").timeout(30000).get();
			// System.out.println(document);
			Elements lstSites = document.selectXpath(
					"/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[position()>1]/tbody/tr[position()>1]");

			// Elements lstSites =
			// document.selectXpath("/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[position()>1]/tbody/tr[position()>1]");

			// Element testElement = document.select("table.wikitable:nth-child(7) >
			// tbody:nth-child(2) > tr:nth-child(1)");

			ArrayList<HistoricalSite> historicalSites = new ArrayList<>();

			// System.out.println("+ " + lstSites.get(0));

			for (Element element : lstSites) {
				// System.out.println(element);
				HistoricalSite historicalSite = new HistoricalSite();
				historicalSite.setName(element.child(0).text());
				// historicalSite.setDesignatedDate(element.child(2).text());
				historicalSite.setLocation(element.child(1).text());
				List<Element> children = element.getElementsByTag("td");
				if (!historicalSite.getName().equals("Ninh Bình")) {
					if (children.size() > 3) {
						if (children.get(3) != null) {
							historicalSite.setDesignatedDate(children.get(3).text());
						} else {
							historicalSite.setDesignatedDate("Không rõ");
						}
					} else {
						historicalSite.setDesignatedDate(children.get(2).text());
					}
				}
				
				if (historicalSite.getDesignatedDate().equals("")) {
					historicalSite.setDesignatedDate("Không rõ");
				}
				historicalSites.add(historicalSite);
			}

			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

			writer.writeValue(Paths.get(output).toFile(), historicalSites);
			System.out.println("Finished writing historical_sites.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
