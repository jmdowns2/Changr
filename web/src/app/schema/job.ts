
export interface Job {
    id: string;
    user: string;
    projectId: string;
    status: string;
    createdDate: Date;
    lastUpdatedDate: Date;
}