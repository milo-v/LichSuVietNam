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
	public void getData() {
		try {
			Document document = Jsoup.connect("https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam").userAgent("Jsoup client").timeout(30000).get();
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
				historicalSite.setId("s" + historicalSites.size());
				historicalSites.add(historicalSite);
			}

			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

			writer.writeValue(Paths.get("data/historical_sites.json").toFile(), historicalSites);
			System.out.println("Finished writing historical_sites.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
