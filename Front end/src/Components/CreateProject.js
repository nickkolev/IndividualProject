import React, { Component } from "react";
import ProjectServices from "./ProjectsServices";

class CreateEmployeeComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: this.props.match.params.id,
      title: "",
      employer: "",
      description: "",
    };
    this.changeTitleHandler = this.changeTitleHandler.bind(this);
    this.changeEmployerHandler = this.changeEmployerHandler.bind(this);
    this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
    this.saveOrUpdateProject = this.saveOrUpdateProject.bind(this);
  }

  componentDidMount() {
    // step 4
    if (this.state.id === "_add") {
      return;
    } else {
      ProjectServices.getProjectById(this.state.id).then((res) => {
        let project = res.data;
        this.setState({
          title: project.title,
          employer: project.employer,
          description: project.description,
        });
      });
    }
  }

  saveOrUpdateProject = (e) => {
    e.preventDefault();
    let project = {
      title: this.state.title,
      employer: this.state.employer,
      description: this.state.description,
    };
    console.log("project => " + JSON.stringify(project));

    if (this.state.id === "_add") {
      ProjectServices.createProject(project).then((res) => {
        this.props.history.push("/projects");
      });
    } else {
      ProjectServices.updateProject(project, this.state.id).then((res) => {
        this.props.history.push("/projects");
      });
    }
  };

  changeTitleHandler = (event) => {
    this.setState({ title: event.target.value });
  };

  changeEmployerHandler = (event) => {
    this.setState({ employer: event.target.value });
  };

  changeDescriptionHandler = (event) => {
    this.setState({ description: event.target.value });
  };

  cancel() {
    this.props.history.push("/projects");
  }

  getTitle() {
    if (this.state.id === "_add") {
      return <h3 className="text-center">Add Project</h3>;
    } else {
      return <h3 className="text-center">Update Project</h3>;
    }
  }

  render() {
    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              {this.getTitle()}
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label> Title: </label>
                    <input
                      placeholder="Title"
                      name="title"
                      className="form-control"
                      value={this.state.title}
                      onChange={this.changeTitleHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Employer: </label>
                    <input
                      placeholder="Employer"
                      name="employer"
                      className="form-control"
                      value={this.state.employer}
                      onChange={this.changeEmployerHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label> Description: </label>
                    <input
                      placeholder="Description"
                      name="description"
                      className="form-control"
                      value={this.state.description}
                      onChange={this.changeDescriptionHandler}
                    />
                  </div>

                  <button
                    className="btn btn-success"
                    onClick={this.saveOrUpdateProject}
                  >
                    Save
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default CreateEmployeeComponent;
