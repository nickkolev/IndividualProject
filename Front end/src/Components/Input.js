import React, { Component } from "react";

class Input extends Component {

    constructor(props) {
        super(props)

        this.state = {
            value: ' '
        }
    }

    changeValue = (event) => {
        this.setState({
            value: event.target.value
        })
    }

    render() {
        return (
            <div>
                <input onChange={this.changeValue}/>
                <p>{this.state.value}</p>
            </div>
        )
    }
}

export default Input;