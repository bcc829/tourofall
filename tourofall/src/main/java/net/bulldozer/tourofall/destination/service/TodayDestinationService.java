package net.bulldozer.tourofall.destination.service;

import java.util.List;

import net.bulldozer.tourofall.destination.dto.TodayDestination;
import net.bulldozer.tourofall.destination.dto.TodayDestinationRederingModel;

public interface TodayDestinationService {
	public List<TodayDestinationRederingModel> getTodayDestinationRenderingModels();
}
