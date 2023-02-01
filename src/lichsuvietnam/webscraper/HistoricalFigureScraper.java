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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lichsuvietnam.models.HistoricalFigure;
import lichsuvietnam.models.HistoricalPeriod;

public class HistoricalFigureScraper {
	public void getData() {
		ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();

		for (int page = 0; page <= 10; page++) {
			HistoricalFigure figure = new HistoricalFigure();

			try {
				Document document = Jsoup.connect("https://nguoikesu.com/nhan-vat?start=" + page)
						.userAgent("Jsoup client").timeout(30000).get();

				Elements lstFigures = document
						.selectXpath("/html/body/div[1]/div[3]/div/div/main/div[2]/div[2]/div[1]/div/div/h2/a");

				Element mainpage = lstFigures.get(0);

				System.out.println(mainpage.attr("href"));

				String fin = "https://nguoikesu.com" + mainpage.attr("href");
				// System.out.println(fin);

				Document document2 = Jsoup.connect(fin).userAgent("Jsoup client").timeout(20000).get();

				// System.out.println(document2);
				Elements dataTable = document2
						.selectXpath("/html/body/div[1]/div[3]/div/div/main/div[2]/div[3]/div[2]/table/tbody");

				// System.out.println(document2.selectXpath("/html/body/div[1]/div[3]/div/div/main/div[2]/div[3]/div[2]").get(0));
				// /html/body/div[2]/div[3]/div/div/main/div[2]/div[3]/div[4]/table/tbody/tr[1]/th
				// /html/body/div[2]/div[3]/div/div/main/div[2]/div[3]/div[2]/table/tbody/tr[1]/th

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

			ObjectMapper mapper = new ObjectMapper();

			try {
				ArrayList<HistoricalPeriod> lstPeriods = mapper.readValue(
						Paths.get("data/historical_periods.json").toFile(),
						new TypeReference<List<HistoricalPeriod>>() {
						});
				for (Iterator iterator = lstPeriods.iterator(); iterator.hasNext();) {
					HistoricalPeriod historicalPeriod = (HistoricalPeriod) iterator.next();
					System.out.println(figure.getPeriod() + " " + historicalPeriod.getName());
					if (figure.getPeriod() != null) {
						if (figure.getPeriod().equals(historicalPeriod.getName())) {
							figure.setRelatedPeriodId(historicalPeriod.getId());
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (figure.getName() != null) {
				figure.setId("fig" + historicalFigures.size());
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
