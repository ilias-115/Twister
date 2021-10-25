class MainPage extends React.Component {
	constructor(props){
		super(props);
		this.state={connected:false,page:"Login"};
    this.getConnected = this.getConnected.bind(this);
    this.setLogout = this.setLogout.bind(this);
    this.getRegister = this.getRegister.bind(this);
	}

  getConnected(){
    this.setState({connected:true,page:"Principale"});
  }
  setLogout(){
    this.setState({connected:false,page:"Login"});
  }
  getRegister(){ 
    this.setState({connected:false,page:"Register"});
  }
  
	render(){
		return(
			<div className="MainPage">
				<NavigationPannel login={this.getConnected} logout={this.setLogout} register={this.getRegister} connected={this.state.connected} page={this.state.page}/>
		 	</div>);
	}
  
}

class NavigationPannel extends React.Component{
  constructor(props){
    super(props);
  }
  
  render(){
    var temp;
    
    if (this.props.page === "Register"){//Page d'enregistrement
      temp=<SignIn logout={this.props.logout} login={this.props.login}/>;
    }else if (this.props.connected){//page connecter
      temp=<div><Principale/><Logout logout={this.props.logout}/></div>;
    }else {//page de connexion
      temp=<div><Login login={this.props.login}/><input type="submit" value="register" ref="register" onClick={((event)=>this.props.register())}/></div>;
    }
    
    return (
      <div className="NaviagtionPannel">
        NavigationPannel
        <nav>
          {temp}
        </nav>
      </div>);
          
            
   }
}

 class Principale extends React.Component {
   constructor(props){
     super(props);
   }
   render(){
     return (<div className="Principale"> principale </div>);
   }
 }

 class Login extends React.Component {
   constructor(props){
     super(props);
   }
   
   send(){
     this.props.login();
   }
   
   render(){
     return (
       <div className="Login">
         <input type="submit" value="Login" onClick={((event)=>this.send())} />
       </div>);
   }
 }
class Logout extends React.Component {
   constructor(props){
     super(props);
   }
  
   send(){
     this.props.logout();
   }
   render(){
     return (
       <div className="Logout">
         <input type="submit" value="Logout" ref="logout" onClick={((event)=>this.send())}/>
       </div>);
   }
 }

class SignIn extends React.Component {
  constructor(props){
     super(props);
   }
  send(){
    this.props.register();
  }
  cancel(){
    this.props.logout();
  }
   render(){
    return (
      <div className="SignIn">
        enregistrement
        <input type="submit" value="cancel" onClick={((event)=>this.cancel())} />
      </div>);
   }
  
}

// ========================================

ReactDOM.render(
  <MainPage />,
  document.getElementById('root')
);
