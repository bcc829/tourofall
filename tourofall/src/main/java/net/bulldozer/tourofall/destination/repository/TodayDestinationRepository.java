package net.bulldozer.tourofall.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.destination.dto.TodayDestination;

public interface TodayDestinationRepository extends JpaRepository<TodayDestination, Long> {
}
