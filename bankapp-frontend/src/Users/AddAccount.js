import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddAccount() {

    function getCustNo() {
        const queryParams = new URLSearchParams(window.location.search)
        const value = queryParams.get('custNo');
      
        return value;
    }
    
    let navigate=useNavigate()

    const [account, setAccount]=useState({
        name:""
    })

    const {accType} = account;

    const onInputChange=(e)=> {
        setAccount({...account,[e.target.name]:e.target.value})
    }

    const onSubmit= async (e)=>{
        e.preventDefault();
        const result = await axios.post("http://localhost:8080/account/create/" + getCustNo(), account);
        alert(result.data.message);

        if (result.data.code === '0') {
            navigate("/account/list?custNo=" + getCustNo());
        }
    }

  return  <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Register Account</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label>
                                Account type :
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter 1 or 2 [1=Saving, 2=Current]"
                                name="accType"
                                value={accType}
                                onChange={(e)=>onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger" to={"/account/list?custNo=" + getCustNo()}>
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
  </div>
  
}
