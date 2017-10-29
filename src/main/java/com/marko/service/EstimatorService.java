package com.marko.service;

import com.marko.model.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by msav on 10/29/2017.
 */
@Service
public class EstimatorService {
    public int estimate(Project project, EstimatorConfiguration config) {
        return 5;
    }
}
