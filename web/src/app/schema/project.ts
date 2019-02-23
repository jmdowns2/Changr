
export enum ProjectType {
    "url"
}

export interface ProjectConfig {
    type: ProjectType;
    url: string
}

export interface Project {
    name:string;
    id: string;
    user: string;
    config: ProjectConfig;
}

export interface CreateProject {
    name:string;
}