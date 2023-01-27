package lichsuvietnam.controllers;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lichsuvietnam.models.HistoricalPeriod;
import lichsuvietnam.services.DataUpdate;

public class TestScrapers {
	public static void main(String[] args) {
		new DataUpdate().update();
		try {
			ObjectMapper mapper = new ObjectMapper();

			ArrayList<HistoricalPeriod> historicalPeriods = mapper.readValue(
					Paths.get("data/historical_periods.json").toFile(), new TypeReference<List<HistoricalPeriod>>() {
					});
			
//			for (Iterator iterator = historicalPeriods.iterator(); iterator.hasNext();) {
//				HistoricalPeriod historicalPeriod = (HistoricalPeriod) iterator.next();
//				System.out.println(historicalPeriod.getName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// table.wikitable:nth-child(55)