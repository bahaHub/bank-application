import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Home() {

    const [users, setUsers]=useState([]);

    useEffect(() => {
        loadUsers();
    },[]);

    const loadUsers= async ()=> {
        const result = await axios.get("http://localhost:8080/users")
        .catch(function (error) {
            alert(JSON.stringify(error));
        });

        if (result.data.code === '0') {
            setUsers(result.data.object);
        } else {
            alert(result.data.message);
        }
    };
  return (
    <div className='container'>
        <div className='py-4'>
            <table className="table border shadow">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Cust No</th>
                    <th scope="col">Cust Name</th>
                    <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((user,index)=>(
                            <tr>
                                <th scope="row" key={index}>{index+1}</th>
                                <td>{user.custNo}</td>
                                <td>{user.custName}</td>
                                <td>
                                    <Link 
                                        className="btn btn-primary mx-2"
                                        to={`/account/list?custNo=${user.custNo}`}
                                    >
                                        Accounts
                                    </Link>
                                </td>
                            </tr>
                        ))
                    }
                    
                </tbody>
            </table>
        </div>    
    </div>
  )
}
