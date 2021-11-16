import axios from 'axios';

const PROJECTS_URL = "http://localhost:8083/projects";

class ProjectsService {

    getProjects(){
        return axios.get(PROJECTS_URL);
    }

    createProject(project){
        return axios.post(PROJECTS_URL, project);
    }

    getProjectById(projectId){
        return axios.get(PROJECTS_URL + '/' + projectId);
    }

    updateProject(project, projectId){
        return axios.put(PROJECTS_URL + '/' + projectId, project);
    }

    deleteProject(projectId){
        return axios.delete(PROJECTS_URL + '/' + projectId);
    }
}

export default new ProjectsService();