import React, { Component } from "react";
import ProjectsServices from "./ProjectsServices";

class ListProjects extends Component {
  constructor(props) {
    super(props);

    this.state = {
      projects: [],
    };

    this.addProject = this.addProject.bind(this);
    this.editProject = this.editProject.bind(this);
    this.deleteProject = this.deleteProject.bind(this);
  }

  deleteProject(id) {
    ProjectsServices.deleteProject(id).then((res) => {
      this.setState({
        projects: this.state.projects.filter((project) => project.id !== id),
      });
    });
  }
  viewProject(id) {
    this.props.history.push(`/view-project/${id}`);
  }
  editProject(id) {
    this.props.history.push(`/add-project/${id}`);
  }

  componentDidMount() {
    ProjectsServices.getProjects().then((response) => {
      this.setState({ projects: response.data });
    });
  }

  addProject() {
    this.props.history.push("/add-project/_add");
  }

  render() {
    return (
      <div className="container">
        <h2 className="text-center"> Projects List </h2>
        <div className="row">
          <button className="btn btn-primary" onClick={this.addProject}>
            Add project
          </button>
        </div>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Project title</th>
                <th>Project employer</th>
                <th>Project description</th>
                <th>Actions</th>
              </tr>
            </thead>

            <tbody>
              {this.state.projects.map((project) => (
                <tr key={project.id}>
                  <td> {project.title}</td>
                  <td> {project.employer}</td>
                  <td> {project.description}</td>
                  <td>
                    <button
                      onClick={() => this.editProject(project.id)}
                      className="btn btn-info"
                    >
                      Update{" "}
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.deleteProject(project.id)}
                      className="btn btn-danger"
                    >
                      Delete{" "}
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.viewProject(project.id)}
                      className="btn btn-info"
                    >
                      View{" "}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListProjects;
