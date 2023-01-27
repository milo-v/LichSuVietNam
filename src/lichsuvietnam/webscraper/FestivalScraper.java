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

import lichsuvietnam.models.Festival;

public class FestivalScraper {
	public void getData() {
		try {
			Document document = Jsoup.connect("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam")
					.userAgent("Jsoup client").timeout(20000).get();
			Elements lstFestivals = document.selectXpath(
					"/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[2]/tbody/tr[position()>1]");
			// System.out.println(lstFestivals);
			ArrayList<Festival> festivals = new ArrayList<>();

			for (Element element : lstFestivals) {
				// System.out.println(element);
				List<Element> characteristics = element.getElementsByTag("td");
				// System.out.println(characteristics);
				Festival festival = new Festival();
				if (characteristics.get(0) != null) {
					festival.setDate(characteristics.get(0).text());
				}
				if (characteristics.get(2) != null) {
					festival.setName(characteristics.get(2).text());
				}
				if (characteristics.get(1) != null) {
					festival.setLocation(characteristics.get(1).text());
				}
				festivals.add(festival);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
			writer.writeValue(Paths.get("data/festivals.json").toFile(), festivals);
			System.out.println("Finished writing festivals.json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
