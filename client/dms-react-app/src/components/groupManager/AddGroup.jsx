import React, {Component} from 'react';

import {FormControl, Form} from 'react-bootstrap';

const API = "https://localhost:8086/group/add"

export default class AddGroup extends Component{
    constructor(props) {
        super(props);

        this.state = {
          alert : false,
          groupName : "",
          groupId : "",
        };
    }

    addGroup = async (event) =>{
        event.preventDefault();
        const {groupName} = this.state;
        console.log(groupName);
        //only fetch if its all letters
        if(/^[A-Za-ząčęėįšųūžĄČĘĖĮŠŲŪŽ]+$/.test(groupName)){
         try{ 
            const res = await fetch(API, {
            method: 'POST',
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify({
              "groupName": groupName,
            }),
          })
          await console.log("You want to add group ",groupName);
          const statusCode = await res.status;
          await console.log(statusCode).catch((err)=>{console.log(err)});
          
         }catch(err){console.log(err)}
         
        }else{
          this.setState({ alert: true });
          setTimeout(() => this.setState({ alert: false}), 2000);
        }
        
      }

    handleChange = (event)=>{
      const {value} = event.target;
      this.setState({
        groupName: value,
      }); 
    }

    render(){
        const {alert, groupName} = this.state;
        return(
            <Form onSubmit={this.addGroup}>
            <div>
                { alert
                  ? <div className="alert alert-danger">Galima naudoti tik raides</div>
                  : null
                }
              </div>
            <div className="group-name">
            
            <FormControl 
                  type="text" 
                  value={groupName}
                  name="groupName"
                  placeholder="Grupės pavadinimas"
                  onChange={this.handleChange}
              /> 
               <input 
                    className="table-button" 
                    type="submit" 
                    className="btn btn-success" 
                    value="Pridėti grupę"/>
            </div>
            </Form>
        )
    }
}