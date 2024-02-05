import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddUser() {

    let navigate=useNavigate()

    const [user, setUser]=useState({
        name:""
    })

    const {custName} = user;

    const onInputChange=(e)=> {
        setUser({...user,[e.target.name]:e.target.value})
    }

    const onSubmit= async (e)=>{
        e.preventDefault();
        const result = await axios.post("http://localhost:8080/register", user);
        alert(result.data.message);

        if (result.data.code === '0') {
            navigate("/");
        }
    }

  return  <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register Customer</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter Name"
                                name="custName"
                                value={custName}
                                onChange={(e)=>onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger" to="/">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
  </div>
  
}
