package com.changr.model;

import java.io.Serializable;

public class FulfillProject implements Serializable {

    public FulfillProject(String projectId, String userId)
    {
        this.projectId = projectId;
        this.userId = userId;
    }

    private String projectId;
    private String userId;

    public String getProjectId() { return projectId; }
    public void setProjectId(String p) { projectId = p; }

    public String getUserId() { return userId; }
    public void setUserId(String u) { userId = u; }
}
