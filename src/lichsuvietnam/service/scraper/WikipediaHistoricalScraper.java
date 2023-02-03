package lichsuvietnam.service.scraper;

import lichsuvietnam.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WikipediaHistoricalScraper implements HistoricalScraper {
    private final static String HISTORICAL_PERIOD_URL =
            "https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
    private final static String HISTORICAL_EVENT_URL =
            "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
    private final static String FESTIVAL_URL =
            "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
    private final static String HISTORICAL_SITE_URL =
            "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
    @Override
    public ArrayList<HistoricalFigure> scrapeHistoricalFigures() {
        return new ArrayList<>();
    }
    @Override
    public ArrayList<HistoricalEvent> scrapeHistoricalEvents() {
        ArrayList<HistoricalEvent> historicalEvents = new ArrayList<>();
        try {
            Document document = Jsoup.connect(HISTORICAL_EVENT_URL)
                    .userAgent("Jsoup client").timeout(20000).get();

            document.select("table").remove();
            Elements lstEvents = document.selectXpath("/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]")
                    .select("p,dl");
            // System.out.println(lstEvents);

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
                    historicalEvents.add(historicalEvent);
                }
            }

            /*
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            writer.writeValue(Paths.get("data/historical_events.json").toFile(), historicalEvents);
            System.out.println("Finished writing historical_events.json");
             */
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return historicalEvents;
    }
    @Override
    public ArrayList<HistoricalSite> scrapeHistoricalSites() {
        ArrayList<HistoricalSite> historicalSites = new ArrayList<>();
        try {
            Document document = Jsoup.connect(HISTORICAL_SITE_URL).userAgent("Jsoup client").timeout(30000).get();
            // System.out.println(document);
            Elements lstSites = document.selectXpath(
                    "/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[position()>1]/tbody/tr[position()>1]");

            // Elements lstSites =
            // document.selectXpath("/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[position()>1]/tbody/tr[position()>1]");

            // Element testElement = document.select("table.wikitable:nth-child(7) >
            // tbody:nth-child(2) > tr:nth-child(1)");

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

            /*
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            writer.writeValue(Paths.get(output).toFile(), historicalSites);
            System.out.println("Finished writing historical_sites.json");
             */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return historicalSites;
    }
    @Override
    public ArrayList<HistoricalPeriod> scrapeHistoricalPeriods() {
        ArrayList<HistoricalPeriod> historicalPeriods = new ArrayList<>();

        try {
            Document document = Jsoup.connect(HISTORICAL_PERIOD_URL)
                    .userAgent("Jsoup client").timeout(20000).get();

            Elements lstPeriods = document.select(
                    ".table > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr > td:nth-child(1)");

            for (Element element : lstPeriods) {
                HistoricalPeriod historicalPeriod = new HistoricalPeriod();
                historicalPeriod.setName(element.child(0).text());
                List<Element> elements = element.getElementsByTag("font");
                if (elements.size() > 0) {
                    String timespan = elements.get(0).text();
                    timespan = timespan.substring(1, timespan.length() - 1);
                    historicalPeriod.setTimeSpan(timespan);
                } else {
                    historicalPeriod.setTimeSpan("");
                }
                historicalPeriods.add(historicalPeriod);
            }

            historicalPeriods.remove(historicalPeriods.size() - 1);
            historicalPeriods.remove(historicalPeriods.size() - 1);

            /*
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

            writer.writeValue(Paths.get(output).toFile(), historicalPeriods);
            System.out.println("Finished writing historical_periods.json");
             */
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historicalPeriods;
    }
    @Override
    public ArrayList<Festival> scrapeFestivals() {
        ArrayList<Festival> festivals = new ArrayList<>();

        try {
            Document document = Jsoup.connect(FESTIVAL_URL)
                    .userAgent("Jsoup client").timeout(20000).get();
            Elements lstFestivals = document.selectXpath(
                    "/html/body/div[1]/div/div[3]/main/div[3]/div[3]/div[1]/table[2]/tbody/tr[position()>1]");
            // System.out.println(lstFestivals);

            for (Element element : lstFestivals) {
                // System.out.println(element);
                List<Element> characteristics = element.getElementsByTag("td");
                System.out.println(characteristics);
                System.out.println("--------------------------------");
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
                if (characteristics.get(4) != null) {
                    ArrayList<String> relatedFigures = new ArrayList<>(
                            Arrays.asList(characteristics.get(4).text().split(","))
                    );
                    relatedFigures.replaceAll(String::trim);

                    // to be removed
                    System.out.println(relatedFigures);

                	festival.setRelatedHistoricalFigureNames(relatedFigures);
                }
                festivals.add(festival);
            }

            /*
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get("data/festivals.json").toFile(), festivals);
            System.out.println("Finished writing festivals.json");
             */
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return festivals;
    }
}
