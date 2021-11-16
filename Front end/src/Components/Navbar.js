import React, { useState } from "react";
import "./Navbar.css";
import ReorderIcon from "@material-ui/icons/Reorder";
import SearchIcon from "@material-ui/icons/Search";

import { Link } from 'react-router-dom';

function Navbar() {
  const [showLinks, setShowLinks] = useState(false);
  return (
    <div className="Navbar">
      <div className="leftSide">
        <div className="links" id={showLinks ? "hidden" : ""}>
          <Link to="/">Home</Link>
          <Link to="/projects">Projects</Link>
          <Link to="/aboutus">About us</Link>
          <Link to="/contacts">Contacts</Link>
        </div>
        <button onClick={() => setShowLinks(!showLinks)}>
          {" "}
          <ReorderIcon />
        </button>
      </div>
      <div className="rightSide">
        <input type="text" placeholder="Search..."></input>
        <button>
          <SearchIcon />
        </button>
      </div>
    </div>
  );
}

export default Navbar;
