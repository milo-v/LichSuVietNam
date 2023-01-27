package lichsuvietnam.webscraper;

import java.io.IOException;
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

import lichsuvietnam.models.HistoricalFigure;

public class HistoricalFigureScraper {
	public void getData() {
		ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();

		for (int page = 0; page <= 1451; page++) {
			HistoricalFigure figure = new HistoricalFigure();

			try {
				Document document = Jsoup.connect("https://nguoikesu.com/nhan-vat?start=" + page).userAgent("Jsoup client").timeout(30000).get();

				Elements lstFigures = document
						.selectXpath("/html/body/div[1]/div[3]/div/div/main/div[2]/div[2]/div[1]/div/div/h2/a");

				Element mainpage = lstFigures.get(0);

				System.out.println(mainpage.attr("href"));

				String fin = "https://nguoikesu.com" + mainpage.attr("href");
				// System.out.println(fin);

				Document document2 = Jsoup.connect(fin).userAgent("Jsoup client").timeout(20000).get();

				Elements dataTable = document2
						.selectXpath("/html/body/div[1]/div[3]/div/div/main/div[2]/div[3]/div[4]/table/tbody");

				// /html/body/div[2]/div[3]/div/div/main/div[2]/div[3]/div[4]/table/tbody/tr[1]/th

				if (dataTable.size() > 0) {
					List<Element> info = dataTable.get(0).getElementsByTag("tr");

					figure.setName(info.get(0).text());
					if (info.get(1).text().startsWith("Vua")) {
						figure.setOccupation("Vua");
					} else if (info.get(1).text().startsWith("Hoàng hậu")) {
						figure.setOccupation("Hoàng hậu");
					}
					System.out.println(figure.getName());
					for (Element i : info) {
						if (i.child(0).text().equals("Triều đại")) {
							figure.setPeriod(i.child(1).text());
						} else if (i.child(0).text().equals("Sinh")) {
							figure.setBirth(i.child(1).text());
						} else if (i.child(0).text().equals("Mất")) {
							figure.setDeath(i.child(1).text());
						}
						
						if (i.text().equals("Chức vụ")) {
							figure.setOccupation(info.get(info.indexOf(i) + 1).text());
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (figure.getName() != null) {
				historicalFigures.add(figure);
			}
		}

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

		try {
			writer.writeValue(Paths.get("data/historical_figures.json").toFile(), historicalFigures);
			System.out.println("Finished writing historical_figures.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
