package com.cubisoft.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionReleaseTracker {

    private String changeRequestNo;
    private String releaseDate;
    private List<String> features;
}
