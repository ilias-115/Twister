import React, { Component } from 'react';

class Logout extends Component {
   constructor(props){
     super(props);
   }
  
   send(){
     //this.props.logout();

   }
   render(){
     return (
       <div className="Logout">
         <input type="submit" value="Logout" ref="logout" onClick={((event)=>this.send())}/>
       </div>);
   }
 }

 export default  Logout;