package lichsuvietnam.test.service.dao;

import lichsuvietnam.service.dao.JsonHistoricalDao;
import lichsuvietnam.service.scraper.AllHistoricalScraper;

public class JsonHistoricalDaoTest {
    public static void main(String[] args) {
        JsonHistoricalDao jsonDao = new JsonHistoricalDao();

        jsonDao.updateAll();
    }
}
