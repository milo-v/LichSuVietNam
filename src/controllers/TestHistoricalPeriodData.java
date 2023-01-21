package controllers;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.HistoricalPeriod;
import services.DataUpdate;

public class TestHistoricalPeriodData {
	public static void main(String[] args) {
		new DataUpdate().update();
		try {
			ObjectMapper mapper = new ObjectMapper();

			ArrayList<HistoricalPeriod> historicalPeriods = mapper.readValue(
					Paths.get("data/historical_period.json").toFile(), new TypeReference<List<HistoricalPeriod>>() {
					});
			
			for (Iterator iterator = historicalPeriods.iterator(); iterator.hasNext();) {
				HistoricalPeriod historicalPeriod = (HistoricalPeriod) iterator.next();
				System.out.println(historicalPeriod.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
