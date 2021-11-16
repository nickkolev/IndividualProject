import axios from "axios"
import React from "react"

const USER_URL = "http://localhost:8083/users";

export default function User() {
  const [users, setUsers] = React.useState(null);


  React.useEffect(() => {
    axios.get(USER_URL).then((response) => {
      setUsers(response.data);
    });
  }, []);

  if (!users) return null;


  return users.map((user=>
    <div>
      {user.name}
    </div>
  ))
}
