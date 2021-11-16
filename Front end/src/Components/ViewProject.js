import React, { Component } from 'react'
import ProjectsServices from './ProjectsServices'

class ViewProject extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            project: {}
        }
    }

    componentDidMount(){
        ProjectsServices.getProjectById(this.state.id).then( res => {
            this.setState({project: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Project Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Project Title: </label>
                            <div> { this.state.project.title }</div>
                        </div>
                        <div className = "row">
                            <label> Project employer: </label>
                            <div> { this.state.project.employer }</div>
                        </div>
                        <div className = "row">
                            <label> Project description: </label>
                            <div> { this.state.project.description }</div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewProject
