package com.cubisoft.endpoints;

import com.cubisoft.dto.ProductionReleaseTracker;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Endpoint(id = "featuresRelease")
public class FeaturesReleaseEndpoints {

    private final List<ProductionReleaseTracker> featuresReleaseTrackers = new ArrayList<>();

    // Add a new release
    @WriteOperation
    public String addNewReleaseInfo(@Selector String changeRequestNo,
                                    @Selector String releaseDate,
                                    @Selector String features) {
        List<String> featureList = Arrays.stream(features.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        ProductionReleaseTracker release = ProductionReleaseTracker.builder()
                .changeRequestNo(changeRequestNo)
                .releaseDate(releaseDate)
                .features(featureList)
                .build();

        featuresReleaseTrackers.add(release);
        return "SUCCESS: Release added for CR " + changeRequestNo;
    }

    // Get all releases
    @ReadOperation
    public List<ProductionReleaseTracker> getAllRelease() {
        return featuresReleaseTrackers;
    }

    // Get release by change request number
    @ReadOperation
    public ProductionReleaseTracker getReleaseByChangeRequestNumber(@Selector String changeRequestNumber) {
        Optional<ProductionReleaseTracker> releaseOpt = featuresReleaseTrackers.stream()
                .filter(prodRelease -> prodRelease.getChangeRequestNo().equals(changeRequestNumber))
                .findFirst();

        return releaseOpt.orElseThrow(() ->
                new IllegalArgumentException("No release found for Change Request: " + changeRequestNumber));
    }

    // Delete release by change request number
    @DeleteOperation
    public String deleteRelease(@Selector String changeRequestNumber) {
        boolean removed = featuresReleaseTrackers.removeIf(prodRelease ->
                prodRelease.getChangeRequestNo().equals(changeRequestNumber));

        if (removed) {
            return "SUCCESS: Release deleted for Change Request " + changeRequestNumber;
        } else {
            return "FAILURE: No release found for Change Request " + changeRequestNumber;
        }
    }
}
