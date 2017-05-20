package net.bulldozer.tourofall.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bulldozer.tourofall.destination.dto.BestDestination;

public interface BestDestinationRepository extends JpaRepository<BestDestination, Long> {
}
