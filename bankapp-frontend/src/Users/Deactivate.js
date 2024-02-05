import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Deactivate() {

    function getCustNo() {
        const queryParams = new URLSearchParams(window.location.search)
        const value = queryParams.get('custNo');
      
        return value;
    }

    function getAccNo() {
        const queryParams = new URLSearchParams(window.location.search)
        const value = queryParams.get('accNo');
      
        return value;
    }
    
    let navigate=useNavigate()

    const [account, setAccount]=useState({
        name:""
    })

    const {deactivate} = account;

    const onInputChange=(e)=> {
        setAccount({...account,[e.target.name]:e.target.value})
    }

    const onSubmit= async (e)=>{
        var accNoTemp = getAccNo();
        var custNoTemp = getCustNo();

        e.preventDefault();
        const result = await axios.delete("http://localhost:8080/account/deactivate/" + accNoTemp, account)
        .catch(function (error) {
            alert(JSON.stringify(error));
        });
        
        if (result.data.code === '0') {
            navigate("/account/list?custNo=" + custNoTemp);
        } else {
            alert(result.data.message);
        }
        
    }

  return  <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Deactivate Account?</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label>
                                Are you sure?
                            </label>
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
