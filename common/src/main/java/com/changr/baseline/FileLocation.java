package com.changr.baseline;

import com.changr.model.Project;
import org.springframework.stereotype.Component;

@Component
public class FileLocation {

    final String BASELINE_FILE = "out.jpg";


    public String getOutputKey(Project p) {
        return p.getUser() + "/" + p.getId() + "/baseline/" + BASELINE_FILE;
    }

    public String getBaselineKey(Project p) {
        return p.getUser() + "/" + p.getId() + "/baseline/" + BASELINE_FILE;
    }


}
