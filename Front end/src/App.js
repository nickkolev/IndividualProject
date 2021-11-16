import React from "react";
import "./App.css";
import SearchBar from "./Components/SearchBar";
import Input from "./Components/Input";
import Navbar from "./Components/Navbar";

import Home from "./Components/Home";

import CreateProject from "./Components/CreateProject";
import ListProjects from "./Components/ListProjects";
import ViewProject from './Components/ViewProject';

import Contacts from "./Components/Contacts";

import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar />
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route path="/projects" 
            component = {ListProjects}>
          </Route>
          <Route path="/add-project/:id" 
            component={CreateProject}>
          </Route>
          <Route path = "/view-project/:id" 
            component = {ViewProject}>
          </Route>
          <Route path="/contacts">
            <Contacts />
          </Route>
        </Switch>
        <p>Users List:</p>
        <SearchBar placeholder="Enter a Name..." />
        <Input />
      </div>
    </Router>
  );
}

export default App;
