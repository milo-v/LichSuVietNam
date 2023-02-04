package lichsuvietnam.service.scraper;

import lichsuvietnam.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NguoiKeSuHistoricalScraper implements HistoricalScraper {
    private static final String HISTORICAL_FIGURE_URL =
            "https://nguoikesu.com/nhan-vat?start=";

    @Override
    public ArrayList<HistoricalFigure> scrapeHistoricalFigures() {
        ArrayList<HistoricalFigure> historicalFigures = new ArrayList<>();
        final int PAGE_NUM = 1451;
        for (int page = 0; page <= PAGE_NUM; page++) {
            HistoricalFigure figure = new HistoricalFigure();

            try {
                Document document = Jsoup.connect(HISTORICAL_FIGURE_URL + page)
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

                    figure.setName(info.get(0).text()
                            .replaceAll(
                                    "[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
                                            "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉị" +
                                            "ọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹa-zA-Z\\s]"
                                    , "")
                            .trim());
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

        return historicalFigures;
    }

    @Override
    public ArrayList<HistoricalEvent> scrapeHistoricalEvents() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<HistoricalSite> scrapeHistoricalSites() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<HistoricalPeriod> scrapeHistoricalPeriods() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Festival> scrapeFestivals() {
        return new ArrayList<>();
    }
}
