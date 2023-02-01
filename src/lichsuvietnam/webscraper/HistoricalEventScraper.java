package lichsuvietnam.webscraper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lichsuvietnam.models.HistoricalEvent;

public class HistoricalEventScraper {
	public void getData() {
		try {
			Document document = Jsoup.connect(
					"https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam")
					.userAgent("Jsoup client").timeout(20000).get();

			document.select("table").remove();
			Elements lstEvents = document.selectXpath("/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]")
					.select("p,dl");
			// System.out.println(lstEvents);

			ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();

			for (Element element : lstEvents) {
				// System.out.println(element);
				
				if (element.tagName().equals("dl")) {
					continue;
				}
				List<Element> period = element.getElementsByTag("b");
				List<Element> name = element.getElementsByTag("a");

				HistoricalEvent historicalEvent = new HistoricalEvent();
				if (period.size() > 0) {
					historicalEvent.setDate(period.get(0).text());
				}
				if (name.size() > 0) {
					historicalEvent.setName(element.text().replace(historicalEvent.getDate() + " ", ""));
				} else {
					// System.out.println("reached");
					// System.out.println(lstEvents.get(lstEvents.indexOf(element)).tagName());
					while (lstEvents.get(lstEvents.indexOf(element) + 1).tagName().equals("dl")) {
						element = lstEvents.get(lstEvents.indexOf(element) + 1);
						List<Element> period2 = element.getElementsByTag("b");
						List<Element> name2 = element.getElementsByTag("a");
						historicalEvent.setDate(period2.get(0).text() + " năm " + period.get(0).text());
						historicalEvent.setName(element.text().replace(period2.get(0).text() + " ", ""));
					}
				}

				if (historicalEvent.getName() != null) {
					historicalEvent.setId("e" + historicalEvents.size());
					historicalEvents.add(historicalEvent);
				}
			}

			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

			writer.writeValue(Paths.get("data/historical_events.json").toFile(), historicalEvents);
			System.out.println("Finished writing historical_events.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
